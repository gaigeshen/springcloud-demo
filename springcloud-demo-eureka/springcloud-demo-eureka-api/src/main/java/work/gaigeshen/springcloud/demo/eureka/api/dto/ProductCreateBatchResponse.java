package work.gaigeshen.springcloud.demo.eureka.api.dto;

import lombok.Data;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
@Data
public class ProductCreateBatchResponse {

  private Collection<ProductCreateResponse> batchCreateResponses;
}
