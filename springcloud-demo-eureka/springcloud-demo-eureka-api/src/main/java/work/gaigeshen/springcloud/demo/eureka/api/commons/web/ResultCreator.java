package work.gaigeshen.springcloud.demo.eureka.api.commons.web;

/**
 *
 * @author gaigeshen
 */
public abstract class ResultCreator {

  private ResultCreator() { }

  public static <D> Result<D> create(ResultCode resultCode) {
    Result<D> result = new Result<>();
    result.setCode(resultCode.getCode());
    result.setMessage(resultCode.getMessage());
    return result;
  }

  public static <D> Result<D> create(D data) {
    Result<D> result = new Result<>();
    result.setData(data);
    return result;
  }

  public static <D> Result<D> create() {
    return new Result<>();
  }
}
