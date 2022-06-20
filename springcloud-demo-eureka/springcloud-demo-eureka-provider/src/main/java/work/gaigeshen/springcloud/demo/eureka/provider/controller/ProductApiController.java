package work.gaigeshen.springcloud.demo.eureka.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.gaigeshen.springcloud.demo.eureka.api.ProductApiService;
import work.gaigeshen.springcloud.demo.eureka.api.commons.PageParameters;
import work.gaigeshen.springcloud.demo.eureka.api.commons.PageResponse;
import work.gaigeshen.springcloud.demo.eureka.api.dto.*;
import work.gaigeshen.springcloud.demo.eureka.provider.service.ProductService;

/**
 *
 * @author gaigeshen
 */
@RequestMapping("/products")
@RestController
public class ProductApiController implements ProductApiService {

  private final ProductService productService;

  public ProductApiController(ProductService productService) {
    this.productService = productService;
  }

  @Override
  public ProductCreateBatchResponse createBatchProducts(ProductCreateBatchParameters createBatchParameters) {
    return productService.createBatchProducts(createBatchParameters);
  }

  @Override
  public ProductCreateResponse createProduct(ProductCreateParameters createParameters) {
    return productService.createProduct(createParameters);
  }

  @Override
  public PageResponse<ProductQueryResponse> queryProducts(ProductQueryParameters queryParameters, PageParameters pageParameters) {
    return productService.queryProducts(queryParameters, pageParameters);
  }
}
