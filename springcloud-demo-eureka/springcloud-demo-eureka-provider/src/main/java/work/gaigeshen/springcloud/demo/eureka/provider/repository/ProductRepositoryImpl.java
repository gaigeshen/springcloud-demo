package work.gaigeshen.springcloud.demo.eureka.provider.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.AnnotationMapper;
import org.springframework.stereotype.Repository;
import work.gaigeshen.springcloud.demo.commons.PageResponse;
import work.gaigeshen.springcloud.demo.commons.PageResponseCreator;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductQueryParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.ProductQueryResponse;
import work.gaigeshen.springcloud.demo.eureka.provider.entity.Product;
import work.gaigeshen.springcloud.demo.eureka.provider.entity.QProduct;

import java.util.*;

/**
 * @author gaigeshen
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository {

  private final SQLQueryFactory queryFactory;

  public ProductRepositoryImpl(SQLQueryFactory queryFactory) {
    this.queryFactory = queryFactory;
  }

  @Override
  public void deleteOne(Product product) {
    QProduct qProduct = QProduct.product;
    queryFactory.delete(qProduct).where(qProduct.id.eq(product.getId()));
  }

  @Override
  public void saveOne(Product product) {
    QProduct qProduct = QProduct.product;

    Long id = queryFactory.insert(qProduct)
            .populate(product, new AnnotationMapper()).executeWithKey(Long.class);
    product.setId(id);
  }

  @Override
  public void save(Collection<Product> products) {
    for (Product product : products) {
      saveOne(product);
    }
  }

  @Override
  public Optional<Product> findById(long id) {

    Tuple tuple = queryFactory.select(QProduct.product.all()).from(QProduct.product)
            .where(QProduct.product.id.eq(id)).fetchFirst();

    if (Objects.isNull(tuple)) {
      return Optional.empty();
    }

    Product product = new Product();

    product.setId(tuple.get(QProduct.product.id));
    product.setName(tuple.get(QProduct.product.name));
    product.setPrice(tuple.get(QProduct.product.price));
    product.setQuantity(tuple.get(QProduct.product.quantity));
    product.setCategory(tuple.get(QProduct.product.category));
    product.setDescription(tuple.get(QProduct.product.description));
    product.setManufacturer(tuple.get(QProduct.product.manufacturer));
    product.setCreateTime(tuple.get(QProduct.product.createTime));
    product.setUpdateTime(tuple.get(QProduct.product.updateTime));

    return Optional.of(product);
  }

  @Override
  public PageResponse<ProductQueryResponse> findAll(ProductQueryParameters queryParameters) {

    QProduct product = QProduct.product;

    long offset = (long) (queryParameters.getPage() - 1) * queryParameters.getSize();

    SQLQuery<Tuple> query = queryFactory.select(product.all()).from(product).offset(offset).limit(queryParameters.getSize());

    query.where(product.name.eq(queryParameters.getName()));
    query.where(product.category.eq(queryParameters.getCategory()));

    query.where(product.price.between(queryParameters.getLowPrice(), queryParameters.getHighPricie()));

    QueryResults<Tuple> fetchResults = query.fetchResults();

    List<ProductQueryResponse> products = new ArrayList<>();

    for (Tuple tuple : fetchResults.getResults()) {
      ProductQueryResponse result = new ProductQueryResponse();
      products.add(result);

      result.setId(tuple.get(QProduct.product.id));
      result.setName(tuple.get(QProduct.product.name));
      result.setPrice(tuple.get(QProduct.product.price));
      result.setQuantity(tuple.get(QProduct.product.quantity));
      result.setCategory(tuple.get(QProduct.product.category));
      result.setDescription(tuple.get(QProduct.product.description));
      result.setManufacturer(tuple.get(QProduct.product.manufacturer));
    }

    return PageResponseCreator.create(queryParameters, products, (int) fetchResults.getTotal());
  }
}
