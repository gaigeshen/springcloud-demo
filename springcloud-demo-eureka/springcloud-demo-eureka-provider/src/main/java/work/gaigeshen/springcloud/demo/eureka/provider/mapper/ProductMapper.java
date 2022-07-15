package work.gaigeshen.springcloud.demo.eureka.provider.mapper;

import org.apache.ibatis.annotations.Mapper;
import work.gaigeshen.springcloud.demo.eureka.provider.config.Page;
import work.gaigeshen.springcloud.demo.eureka.provider.entity.Product;

import java.util.List;

/**
 * @author gaigeshen
 */
@Mapper
public interface ProductMapper {

  void insert(Product product);

  void insertBatch(List<Product> products);

  void insertSelective(Product product);

  void deleteById(long id);

  void updateById(Product product);

  void updateByIdSelective(Product product);

  Product selectById(long id);

  Page<Product> selectPage(Page<?> page);

}
