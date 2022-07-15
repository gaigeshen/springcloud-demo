package work.gaigeshen.springcloud.demo.eureka.provider.mapper;

import org.apache.ibatis.annotations.Mapper;
import work.gaigeshen.springcloud.demo.eureka.provider.entity.Role;

import java.util.List;

/**
 * @author gaigeshen
 */
@Mapper
public interface RoleMapper {

  void insert(Role role);

  void insertBatch(List<Role> roles);

  void insertSelective(Role role);

  void deleteById(long id);

  void updateById(Role role);

  void updateByIdSelective(Role role);

  Role selectById(long id);

}
