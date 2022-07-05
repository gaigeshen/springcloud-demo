package work.gaigeshen.springcloud.demo.eureka.provider.repository;

import work.gaigeshen.springcloud.demo.commons.PageResponse;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductQueryParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductQueryResponse;
import work.gaigeshen.springcloud.demo.eureka.provider.entity.Product;

import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author gaigeshen
 */
public interface ProductRepository {

  void deleteOne(Product product);

  void saveOne(Product product);

  void save(Collection<Product> products);

  Optional<Product> findById(long id);

  PageResponse<ProductQueryResponse> findAll(ProductQueryParameters queryParameters);
}
