package work.gaigeshen.springcloud.demo.eureka.provider.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.gaigeshen.springcloud.demo.commons.PageResponse;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductCreateParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductDeleteParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductQueryParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductQueryResponse;
import work.gaigeshen.springcloud.demo.eureka.provider.entity.Product;
import work.gaigeshen.springcloud.demo.eureka.provider.repository.ProductRepository;
import work.gaigeshen.springcloud.demo.eureka.provider.service.ProductService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gaigeshen
 */
@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Transactional
  @Override
  public void createBatchProducts(List<ProductCreateParameters> batchCreateParameters) {
    Collection<Product> products = new ArrayList<>();
    for (ProductCreateParameters createParameters : batchCreateParameters) {
      Product product = new Product();
      products.add(product);
      BeanUtils.copyProperties(createParameters, product);
      product.setCreateTime(new Date());
      product.setUpdateTime(product.getCreateTime());
    }
    productRepository.save(products);
  }

  @Transactional
  @Override
  public void createProduct(ProductCreateParameters createParameters) {
    Product product = new Product();
    BeanUtils.copyProperties(createParameters, product);
    product.setCreateTime(new Date());
    product.setUpdateTime(product.getCreateTime());
    productRepository.saveOne(product);
  }

  @Transactional
  @Override
  public void deleteProduct(ProductDeleteParameters deleteParameters) {
    productRepository.findById(deleteParameters.getId()).ifPresent(productRepository::deleteOne);
  }

  @Transactional(readOnly = true)
  @Override
  public PageResponse<ProductQueryResponse> queryProducts(ProductQueryParameters queryParameters) {
    return productRepository.findAll(queryParameters);
  }

}
