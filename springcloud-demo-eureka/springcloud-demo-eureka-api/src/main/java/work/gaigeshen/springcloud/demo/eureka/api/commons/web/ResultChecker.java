package work.gaigeshen.springcloud.demo.eureka.api.commons.web;

import java.util.Objects;
import java.util.function.Supplier;

/**
 *
 * @author gaigeshen
 */
public abstract class ResultChecker {

  private ResultChecker() {  }

  public static boolean isSuccess(Result<?> result) {
    return Objects.equals(Result.DEFAULT_CODE, result.getCode());
  }

  public static void check(Result<?> result, Supplier<RuntimeException> exception) {
    if (!isSuccess(result)) {
      throw exception.get();
    }
  }
}
