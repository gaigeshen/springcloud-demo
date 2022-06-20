package work.gaigeshen.springcloud.demo.eureka.provider.service;

import org.springframework.validation.annotation.Validated;
import work.gaigeshen.springcloud.demo.eureka.api.commons.PageParameters;
import work.gaigeshen.springcloud.demo.eureka.api.commons.PageResponse;
import work.gaigeshen.springcloud.demo.eureka.api.dto.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author gaigeshen
 */
@Validated
public interface ProductService {

  ProductCreateBatchResponse createBatchProducts(@NotNull @Valid ProductCreateBatchParameters createBatchParameters);

  ProductCreateResponse createProduct(@NotNull @Valid ProductCreateParameters createParameters);

  PageResponse<ProductQueryResponse> queryProducts(@NotNull @Valid ProductQueryParameters queryParameters, @NotNull @Valid PageParameters pageParameters);
}
