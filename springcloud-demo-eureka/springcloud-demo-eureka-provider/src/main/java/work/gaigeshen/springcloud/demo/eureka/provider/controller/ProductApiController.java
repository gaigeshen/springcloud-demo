package work.gaigeshen.springcloud.demo.eureka.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.gaigeshen.springcloud.demo.commons.PageResponse;
import work.gaigeshen.springcloud.demo.commons.web.Result;
import work.gaigeshen.springcloud.demo.commons.web.Results;
import work.gaigeshen.springcloud.demo.eureka.api.ProductApiService;
import work.gaigeshen.springcloud.demo.eureka.api.dto.product.ProductCreateParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.product.ProductDeleteParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.product.ProductQueryParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.product.ProductQueryResponse;
import work.gaigeshen.springcloud.demo.eureka.provider.service.ProductService;

import java.util.List;

/**
 *
 * @author gaigeshen
 */
@Slf4j
@RequestMapping("/products")
@RestController
public class ProductApiController implements ProductApiService {

  private final ProductService productService;

  public ProductApiController(ProductService productService) {
    this.productService = productService;
  }

  @Override
  public Result<?> createBatchProducts(List<ProductCreateParameters> batchCreateParameters) {
    log.debug("batch create products: {}", batchCreateParameters);
    productService.createBatchProducts(batchCreateParameters);
    return Results.create();
  }

  @Override
  public Result<?> createProduct(ProductCreateParameters createParameters) {
    log.debug("create product: {}", createParameters);
    productService.createProduct(createParameters);
    return Results.create();
  }

  @Override
  public Result<?> deleteProduct(ProductDeleteParameters deleteParameters) {
    log.debug("delete product: {}", deleteParameters);
    productService.deleteProduct(deleteParameters);
    return Results.create();
  }

  @Override
  public Result<PageResponse<ProductQueryResponse>> queryProducts(ProductQueryParameters queryParameters) {
    log.debug("query products: {}", queryParameters);
    return Results.create(productService.queryProducts(queryParameters));
  }
}
