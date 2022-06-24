package work.gaigeshen.springcloud.demo.eureka.consumer.controller;

import org.springframework.web.bind.annotation.*;
import work.gaigeshen.springcloud.demo.eureka.api.ProductApiService;
import work.gaigeshen.springcloud.demo.eureka.api.commons.PageResponse;
import work.gaigeshen.springcloud.demo.eureka.api.commons.web.Result;
import work.gaigeshen.springcloud.demo.eureka.api.dto.*;

import java.util.List;

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
  public Result<?> createBatchProducts(@RequestBody List<ProductCreateParameters> batchCreateParameters) {
    return productApiService.createBatchProducts(batchCreateParameters);
  }

  @PostMapping
  public Result<?> createProduct(@RequestBody ProductCreateParameters createParameters) {
    return productApiService.createProduct(createParameters);
  }

  @PostMapping("/delete")
  public Result<?> deleteProduct(@RequestParam long id) {
    ProductDeleteParameters deleteParameters = new ProductDeleteParameters();
    deleteParameters.setId(id);
    return productApiService.deleteProduct(deleteParameters);
  }

  @GetMapping
  public Result<PageResponse<ProductQueryResponse>> queryProducts(ProductQueryParameters queryParameters) {
    return productApiService.queryProducts(queryParameters);
  }
}
