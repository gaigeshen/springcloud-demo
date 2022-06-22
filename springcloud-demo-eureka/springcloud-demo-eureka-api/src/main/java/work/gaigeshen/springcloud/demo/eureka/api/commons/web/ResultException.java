package work.gaigeshen.springcloud.demo.eureka.api.commons.web;

/**
 *
 * @author gaigeshen
 */
public class ResultException extends RuntimeException {

  private final Result<?> result;

  public ResultException(Result<?> result, String message) {
    super(message);
    this.result = result;
  }

  public Result<?> getResult() {
    return result;
  }
}
