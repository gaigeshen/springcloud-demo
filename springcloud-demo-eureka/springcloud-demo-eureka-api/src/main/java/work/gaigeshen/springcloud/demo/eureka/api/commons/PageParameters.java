package work.gaigeshen.springcloud.demo.eureka.api.commons;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 *
 * @author gaigeshen
 */
@Data
public class PageParameters {

  @Min(1)
  private int page = 1;

  @Min(1)
  private int size = 10;
}
