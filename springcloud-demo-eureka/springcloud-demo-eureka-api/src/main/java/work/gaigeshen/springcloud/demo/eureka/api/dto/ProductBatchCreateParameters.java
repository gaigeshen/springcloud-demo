package work.gaigeshen.springcloud.demo.eureka.api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
@Data
public class ProductBatchCreateParameters {

  @NotEmpty
  private Collection<ProductCreateParameters> batchCreateParameters;
}
