package work.gaigeshen.springcloud.demo.eureka.api.commons.web;

import java.util.Objects;

/**
 * 响应结果工具类
 *
 * @author gaigeshen
 */
public abstract class Results {

  /**
   * 无需调用此方法
   */
  private Results() {  }

  /**
   * 校验响应结果是否是成功的
   *
   * @param result 响应结果
   * @return 是否是成功的
   * @param <D> 响应结果中包含的数据的类型
   */
  public static <D> boolean isSuccess(Result<D> result) {
    return Objects.equals(Result.DEFAULT_CODE, result.getCode());
  }

  /**
   * 检查响应结果然后获取其中的数据
   *
   * @param result 响应结果
   * @param provider 如果响应结果不是成功的则会调用此异常提供器获取异常然后将其抛出
   * @return 响应结果中的数据
   * @param <D> 响应结果中包含的数据的类型
   */
  public static <D> D checkAndGetData(Result<D> result, ExceptionProviderr<D> provider) {
    if (isSuccess(result)) {
      return result.getData();
    }
    throw provider.provide(result);
  }

  /**
   * 检查响应结果然后获取其中的数据
   *
   * @param result 响应结果
   * @return 响应结果中的数据
   * @param <D> 响应结果中包含的数据的类型
   */
  public static <D> D checkAndGetData(Result<D> result) {
    return checkAndGetData(result, r -> new ResultException(r, r.getMessage()));
  }

  /**
   * 异常提供器
   *
   * @author gaigeshen
   */
  @FunctionalInterface
  public interface ExceptionProviderr<D> {

    /**
     * 调用此方法将产生运行时异常对象
     *
     * @param result 响应结果
     * @return 运行时异常对象
     */
    RuntimeException provide(Result<D> result);
  }
}
