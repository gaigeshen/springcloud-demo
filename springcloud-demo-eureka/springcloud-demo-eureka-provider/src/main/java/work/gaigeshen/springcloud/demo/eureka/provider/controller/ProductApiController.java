package work.gaigeshen.springcloud.demo.eureka.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.gaigeshen.springcloud.demo.eureka.api.ProductApiService;
import work.gaigeshen.springcloud.demo.eureka.api.commons.PageResponse;
import work.gaigeshen.springcloud.demo.eureka.api.commons.web.Result;
import work.gaigeshen.springcloud.demo.eureka.api.commons.web.ResultCreator;
import work.gaigeshen.springcloud.demo.eureka.api.dto.*;
import work.gaigeshen.springcloud.demo.eureka.provider.service.ProductService;

import java.util.List;

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
  public Result<?> createBatchProducts(List<ProductCreateParameters> batchCreateParameters) {
    productService.createBatchProducts(batchCreateParameters);
    return ResultCreator.create();
  }

  @Override
  public Result<?> createProduct(ProductCreateParameters createParameters) {
    productService.createProduct(createParameters);
    return ResultCreator.create();
  }

  @Override
  public Result<?> deleteProduct(ProductDeleteParameters deleteParameters) {
    productService.deleteProduct(deleteParameters);
    return ResultCreator.create();
  }

  @Override
  public Result<PageResponse<ProductQueryResponse>> queryProducts(ProductQueryParameters queryParameters) {
    return ResultCreator.create(productService.queryProducts(queryParameters));
  }
}
