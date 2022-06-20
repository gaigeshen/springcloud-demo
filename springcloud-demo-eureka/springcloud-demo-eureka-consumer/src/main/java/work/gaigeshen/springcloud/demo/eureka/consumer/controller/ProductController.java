package work.gaigeshen.springcloud.demo.eureka.consumer.controller;

import org.springframework.web.bind.annotation.*;
import work.gaigeshen.springcloud.demo.eureka.api.ProductApiService;
import work.gaigeshen.springcloud.demo.eureka.api.commons.PageParameters;
import work.gaigeshen.springcloud.demo.eureka.api.commons.PageResponse;
import work.gaigeshen.springcloud.demo.eureka.api.dto.*;

/**
 *
 * @author gaigeshen
 */
@RequestMapping("/products")
@RestController
public class ProductController {

  private final ProductApiService productApiService;

  public ProductController(ProductApiService productApiService) {
    this.productApiService = productApiService;
  }

  @PostMapping("/batch")
  public ProductCreateBatchResponse createBatchProducts(@RequestBody ProductCreateBatchParameters createBatchParameters) {
    return productApiService.createBatchProducts(createBatchParameters);
  }

  @PostMapping
  public ProductCreateResponse createProduct(@RequestBody ProductCreateParameters createParameters) {
    return productApiService.createProduct(createParameters);
  }

  @GetMapping
  public PageResponse<ProductQueryResponse> queryProducts(ProductQueryParameters queryParameters, PageParameters pageParameters) {
    return productApiService.queryProducts(queryParameters, pageParameters);
  }
}
