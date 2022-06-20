package work.gaigeshen.springcloud.demo.eureka.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import work.gaigeshen.springcloud.demo.eureka.api.commons.PageParameters;
import work.gaigeshen.springcloud.demo.eureka.api.commons.PageResponse;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductCreateBatchParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductCreateBatchResponse;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductCreateParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductCreateResponse;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductQueryParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductQueryResponse;

/**
 *
 * @author gaigeshen
 */
@FeignClient(name = "spring-cloud-eureka-provider", path = "/products")
public interface ProductApiService {

  @PostMapping("/batch")
  ProductCreateBatchResponse createBatchProducts(@RequestBody ProductCreateBatchParameters createBatchParameters);

  @PostMapping
  ProductCreateResponse createProduct(@RequestBody ProductCreateParameters createParameters);

  @GetMapping
  PageResponse<ProductQueryResponse> queryProducts(ProductQueryParameters queryParameters, PageParameters pageParameters);

}
