package work.gaigeshen.springcloud.demo.eureka.provider.mapper;

import org.apache.ibatis.annotations.Mapper;
import work.gaigeshen.springcloud.demo.eureka.provider.entity.UserRole;

import java.util.List;

/**
 * @author gaigeshen
 */
@Mapper
public interface UserRoleMapper {

  void insert(UserRole userRole);

  void insertBatch(List<UserRole> userRoles);

  void insertSelective(UserRole userRole);

  void deleteById(long id);

  void deleteByUserId(long userId);

  void deleteByRoleId(long roleId);

  void updateById(UserRole userRole);

  void updateByIdSelective(UserRole userRole);

  UserRole selectById(long id);

  List<UserRole> selectByUserId(long userId);

  List<UserRole> selectByRoleId(long roleId);
}
