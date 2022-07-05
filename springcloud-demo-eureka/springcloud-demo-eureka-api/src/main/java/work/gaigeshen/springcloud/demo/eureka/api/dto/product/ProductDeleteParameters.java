package work.gaigeshen.springcloud.demo.eureka.api.dto.product;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *
 * @author gaigeshen
 */
@Data
public class ProductDeleteParameters {

  @NotNull
  private Long id;

}
