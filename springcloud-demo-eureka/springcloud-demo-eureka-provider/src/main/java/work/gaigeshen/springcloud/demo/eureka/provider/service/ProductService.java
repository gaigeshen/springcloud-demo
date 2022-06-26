package work.gaigeshen.springcloud.demo.eureka.provider.service;

import org.springframework.validation.annotation.Validated;
import work.gaigeshen.springcloud.demo.commons.PageResponse;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductCreateParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductDeleteParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductQueryParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductQueryResponse;

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
