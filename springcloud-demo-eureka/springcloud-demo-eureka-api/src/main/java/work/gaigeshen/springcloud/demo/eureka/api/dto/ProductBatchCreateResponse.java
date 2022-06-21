package work.gaigeshen.springcloud.demo.eureka.api.dto;

import lombok.Data;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
@Data
public class ProductBatchCreateResponse {

  private Collection<ProductCreateResponse> batchCreateResponses;
}
