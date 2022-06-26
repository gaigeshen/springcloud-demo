package work.gaigeshen.springcloud.demo.eureka.provider.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.gaigeshen.springcloud.demo.commons.PageResponse;
import work.gaigeshen.springcloud.demo.commons.PageResponseCreator;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductCreateParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductDeleteParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductQueryParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductQueryResponse;
import work.gaigeshen.springcloud.demo.eureka.provider.entity.Product;
import work.gaigeshen.springcloud.demo.eureka.provider.repository.ProductRepository;
import work.gaigeshen.springcloud.demo.eureka.provider.service.ProductService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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
    }
    productRepository.saveAll(products);
  }

  @Transactional
  @Override
  public void createProduct(ProductCreateParameters createParameters) {
    Product product = new Product();
    BeanUtils.copyProperties(createParameters, product);
    productRepository.save(product);
  }

  @Transactional
  @Override
  public void deleteProduct(ProductDeleteParameters deleteParameters) {
    productRepository.findById(deleteParameters.getId()).ifPresent(productRepository::delete);
  }

  @Override
  public PageResponse<ProductQueryResponse> queryProducts(ProductQueryParameters queryParameters) {
    // 页码从零开始的
    Pageable pageRequest = PageRequest.of(queryParameters.getPage() - 1, queryParameters.getSize());
    // 分页查询
    Page<Product> pageResult = productRepository.findAll(new QuerySpecification(queryParameters), pageRequest);

    if (pageResult.isEmpty()) {
      return PageResponseCreator.create(queryParameters);
    }
    Page<ProductQueryResponse> responsePageResult = pageResult.map(product -> {
      ProductQueryResponse queryResponse = new ProductQueryResponse();
      BeanUtils.copyProperties(product, queryResponse);
      return queryResponse;
    });

    return PageResponseCreator.create(queryParameters, responsePageResult.getContent(), (int) pageResult.getTotalElements());
  }

  private static class QuerySpecification implements Specification<Product> {

    private final ProductQueryParameters queryParameters;

    private QuerySpecification(ProductQueryParameters queryParameters) {
      this.queryParameters = queryParameters;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
      List<Predicate> predicates = new ArrayList<>();
      if (Objects.nonNull(queryParameters.getName())) {
        predicates.add(criteriaBuilder.like(root.get("name"), "%" + queryParameters.getName() + "%"));
      }
      if (Objects.nonNull(queryParameters.getCategory())) {
        predicates.add(criteriaBuilder.like(root.get("category"), "%" + queryParameters.getCategory() + "%"));
      }
      if (Objects.nonNull(queryParameters.getLowPrice())) {
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), queryParameters.getLowPrice()));
      }
      if (Objects.nonNull(queryParameters.getHighPricie())) {
        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), queryParameters.getHighPricie()));
      }
      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
  }
}
