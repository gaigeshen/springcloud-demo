package work.gaigeshen.springcloud.demo.eureka.provider.service;

import org.springframework.validation.annotation.Validated;
import work.gaigeshen.springcloud.demo.commons.PageResponse;
import work.gaigeshen.springcloud.demo.eureka.api.dto.product.ProductCreateParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.product.ProductDeleteParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.product.ProductQueryParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.product.ProductQueryResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author gaigeshen
 */
@Validated
public interface ProductService {

  void createBatchProducts(@NotEmpty @Valid List<ProductCreateParameters> batchCreateParameters);

  void createProduct(@NotNull @Valid ProductCreateParameters createParameters);

  void deleteProduct(@NotNull @Valid ProductDeleteParameters deleteParameters);

  PageResponse<ProductQueryResponse> queryProducts(@NotNull @Valid ProductQueryParameters queryParameters);
}
