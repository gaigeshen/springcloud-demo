package work.gaigeshen.springcloud.demo.eureka.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import work.gaigeshen.springcloud.demo.commons.PageResponse;
import work.gaigeshen.springcloud.demo.commons.web.Result;
import work.gaigeshen.springcloud.demo.eureka.api.dto.product.ProductCreateParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.product.ProductDeleteParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.product.ProductQueryParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.product.ProductQueryResponse;

import java.util.List;

/**
 *
 * @author gaigeshen
 */
@FeignClient(name = "spring-cloud-eureka-provider", path = "/products", contextId = "productApiService")
public interface ProductApiService {

  @PostMapping("/batch-create")
  Result<?> createBatchProducts(@RequestBody List<ProductCreateParameters> batchCreateParameters);

  @PostMapping("/create")
  Result<?> createProduct(@RequestBody ProductCreateParameters createParameters);

  @PostMapping("/delete")
  Result<?> deleteProduct(@RequestBody ProductDeleteParameters deleteParameters);

  @PostMapping("/query")
  Result<PageResponse<ProductQueryResponse>> queryProducts(@RequestBody ProductQueryParameters queryParameters);

}
