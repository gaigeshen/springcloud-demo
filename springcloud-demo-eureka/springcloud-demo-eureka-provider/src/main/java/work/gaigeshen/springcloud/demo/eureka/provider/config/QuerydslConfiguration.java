package work.gaigeshen.springcloud.demo.eureka.provider.config;

import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.spring.SpringConnectionProvider;
import com.querydsl.sql.spring.SpringExceptionTranslator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 *
 * @author gaigeshen
 */
@Configuration
public class QuerydslConfiguration {

  @Bean
  public com.querydsl.sql.Configuration querydslConfiguration() {
    SQLTemplates templates = MySQLTemplates.builder().build();
    com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(templates);
    configuration.setExceptionTranslator(new SpringExceptionTranslator());
    return configuration;
  }

  @Bean
  public SQLQueryFactory queryFactory(DataSource dataSource) {
    SpringConnectionProvider provider = new SpringConnectionProvider(dataSource);
    return new SQLQueryFactory(querydslConfiguration(), provider);
  }
}
