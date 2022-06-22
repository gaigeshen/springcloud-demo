package work.gaigeshen.springcloud.demo.eureka.api.commons.web;

/**
 *
 * @author gaigeshen
 */
public abstract class Results {

  private Results() {  }

  public static <D> D getData(Result<D> result) {
    return getData(result, new ResultValidator<>() { }, new ResultExceptionProducer<>() { });
  }

  public static <D> D getData(Result<D> result, ResultValidator<D> validator) {
    return getData(result, validator, new ResultExceptionProducer<>() { });
  }

  public static <D> D getData(Result<D> result, ResultExceptionProducer<D> producer) {
    return getData(result, new ResultValidator<>() { }, producer);
  }

  public static <D> D getData(Result<D> result, ResultValidator<D> validator, ResultExceptionProducer<D> producer) {
    if (validator.validate(result)) {
      throw producer.produce(result);
    }
    return result.getData();
  }

  /**
   *
   * @author gaigeshen
   */
  public interface ResultValidator<D> {

    default boolean validate(Result<D> result) {
      return Result.DEFAULT_CODE == result.getCode();
    }
  }

  /**
   *
   * @author gaigeshen
   */
  public interface ResultExceptionProducer<D> {

    default ResultException produce(Result<D> result) {
      return new ResultException(result, result.getMessage());
    }
  }
}
