package work.gaigeshen.springcloud.demo.eureka.provider.mapper;

import org.apache.ibatis.annotations.Mapper;
import work.gaigeshen.springcloud.demo.eureka.provider.entity.User;

import java.util.List;

/**
 * @author gaigeshen
 */
@Mapper
public interface UserMapper {

  void insert(User user);

  void insertBatch(List<User> users);

  void insertSelective(User user);

  void deleteById(long id);

  void updateById(User user);

  void updateByIdSelective(User user);

  User selectById(long id);

}
