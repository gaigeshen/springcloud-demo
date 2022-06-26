package work.gaigeshen.springcloud.demo.eureka.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import work.gaigeshen.springcloud.demo.commons.PageResponse;
import work.gaigeshen.springcloud.demo.commons.web.Result;
import work.gaigeshen.springcloud.demo.eureka.api.ProductApiService;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductCreateParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductDeleteParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductQueryParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductQueryResponse;

import java.util.List;

/**
 *
 * @author gaigeshen
 */
@Slf4j
@RequestMapping("/products")
@RestController
public class ProductController {

  private final ProductApiService productApiService;

  public ProductController(ProductApiService productApiService) {
    this.productApiService = productApiService;
  }

  @PostMapping("/batch")
  public Result<?> createBatchProducts(@RequestBody List<ProductCreateParameters> batchCreateParameters) {
    log.debug("batch create products: {}", batchCreateParameters);
    return productApiService.createBatchProducts(batchCreateParameters);
  }

  @PostMapping
  public Result<?> createProduct(@RequestBody ProductCreateParameters createParameters) {
    log.debug("create product: {}", createParameters);
    return productApiService.createProduct(createParameters);
  }

  @PostMapping("/delete")
  public Result<?> deleteProduct(@RequestParam long id) {
    log.debug("delete product: {}", id);
    ProductDeleteParameters deleteParameters = new ProductDeleteParameters();
    deleteParameters.setId(id);
    return productApiService.deleteProduct(deleteParameters);
  }

  @GetMapping
  public Result<PageResponse<ProductQueryResponse>> queryProducts(ProductQueryParameters queryParameters) {
    log.debug("query products: {}", queryParameters);
    return productApiService.queryProducts(queryParameters);
  }
}
