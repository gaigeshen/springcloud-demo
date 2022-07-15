package work.gaigeshen.springcloud.demo.eureka.provider.config;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author gaigeshen
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {
                Connection.class, Integer.class}
        ),
        @Signature(type = Executor.class, method = "query", args = {
                MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class,
                CacheKey.class, BoundSql.class}
        )
})
public class PagingInterceptor implements Interceptor {

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    if (invocation.getTarget() instanceof StatementHandler) {
      return interceptStatementHandler(invocation);
    }
    if (invocation.getTarget() instanceof Executor) {
      return interceptExecutor(invocation);
    }
    return invocation.proceed();
  }

  @Override
  public Object plugin(Object target) {
    if (target instanceof StatementHandler) {
      return Plugin.wrap(target, this);
    }
    if (target instanceof Executor) {
      return Plugin.wrap(target, this);
    }
    return target;
  }

  private Object interceptStatementHandler(Invocation invocation) throws Throwable {

    MetaObject metaObjectHandler = SystemMetaObject.forObject(getStatementHandler(invocation));

    MappedStatement mStatement = (MappedStatement) metaObjectHandler.getValue("delegate.mappedStatement");
    if (!SqlCommandType.SELECT.equals(mStatement.getSqlCommandType())) {
      return invocation.proceed();
    }
    BoundSql boundSql = (BoundSql) metaObjectHandler.getValue("delegate.boundSql");
    Page<?> pageParameter = getPageParameter(boundSql.getParameterObject());
    if (Objects.isNull(pageParameter)) {
      return invocation.proceed();
    }
    Connection conn = (Connection) invocation.getArgs()[0];
    pageParameter.setTotal(getCount(boundSql, mStatement, conn));
    if (pageParameter.getTotal() == 0) {
      return invocation.proceed();
    }
    int limit = pageParameter.getSize();
    int skip = (pageParameter.getIndex() - 1) * limit;

    String sql = "select * from (" + boundSql.getSql() + ") temp limit " + skip + ", " + limit;

    metaObjectHandler.setValue("delegate.boundSql.sql", sql);
    return invocation.proceed();
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  private Object interceptExecutor(Invocation invocation) throws Throwable {
    Executor executor = (Executor) invocation.getTarget();

    MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
    if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
      return invocation.proceed();
    }
    Object paramObject = invocation.getArgs()[1];
    Page<?> pageParameter = getPageParameter(paramObject);
    if (Objects.isNull(pageParameter)) {
      return invocation.proceed();
    }
    pageParameter.clear();
    if (pageParameter.getTotal() == 0) {
      return Collections.emptyList();
    }

    List queryResult = executor.query(mappedStatement, paramObject,
            (RowBounds) invocation.getArgs()[2], (ResultHandler<?>) invocation.getArgs()[3],
            (CacheKey) invocation.getArgs()[4], (BoundSql) invocation.getArgs()[5]);

    pageParameter.addAll(queryResult);

    return pageParameter;
  }

  private StatementHandler getStatementHandler(Invocation invocation) {
    StatementHandler handler = (StatementHandler) invocation.getTarget();
    MetaObject metaObject = SystemMetaObject.forObject(handler);
    Object actualHandler = null;
    while (metaObject.hasGetter("h")) {
      actualHandler = metaObject.getValue("h");
    }
    if (Objects.isNull(actualHandler)) {
      return handler;
    }
    return (StatementHandler) actualHandler;
  }

  private Page<?> getPageParameter(Object params) {
    if (Objects.isNull(params)) {
      return null;
    }
    if (params instanceof Page) {
      return (Page<?>) params;
    }
    if (params instanceof Map) {
      for (Map.Entry<?, ?> param : ((Map<?, ?>) params).entrySet()) {
        Object paramValue = param.getValue();
        if (paramValue instanceof Page) {
          return (Page<?>) paramValue;
        }
      }
    }
    return null;
  }

  private int getCount(BoundSql boundSql, MappedStatement mStatement, Connection conn) throws SQLException {
    Configuration configuration = mStatement.getConfiguration();
    Object paramObject = boundSql.getParameterObject();
    List<ParameterMapping> paramMappings = boundSql.getParameterMappings();

    String countSql = "select count(*) from (" + boundSql.getSql() + ") temp";

    BoundSql countBoundSql = new BoundSql(configuration, countSql, paramMappings, paramObject);

    DefaultParameterHandler paramHandler = new DefaultParameterHandler(mStatement, paramObject, countBoundSql);

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      preparedStatement = conn.prepareStatement(countBoundSql.getSql());
      paramHandler.setParameters(preparedStatement);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return resultSet.getInt(1);
      }
    } finally {
      if (Objects.nonNull(resultSet)) {
        resultSet.close();
      }
      if (Objects.nonNull(preparedStatement)) {
        preparedStatement.close();
      }
    }
    return 0;
  }

}
