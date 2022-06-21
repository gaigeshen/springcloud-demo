package work.gaigeshen.springcloud.demo.eureka.api.commons.web;

import lombok.Data;

/**
 *
 * @author gaigeshen
 */
@Data
public class Result<D> {

  public static final int DEFAULT_CODE = 0;

  public static final String DEFAULT_MESSAGE = "ok";

  private int code = DEFAULT_CODE;

  private String message = DEFAULT_MESSAGE;

  private D data;

}
