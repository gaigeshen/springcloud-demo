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
import work.gaigeshen.springcloud.demo.eureka.api.dto.user.*;
import work.gaigeshen.springcloud.demo.eureka.provider.entity.Role;
import work.gaigeshen.springcloud.demo.eureka.provider.entity.User;
import work.gaigeshen.springcloud.demo.eureka.provider.repository.RoleRepository;
import work.gaigeshen.springcloud.demo.eureka.provider.repository.UserRepository;
import work.gaigeshen.springcloud.demo.eureka.provider.service.UserService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 *
 * @author gaigeshen
 */
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  @Transactional
  @Override
  public void createBatchUsers(List<UserCreateParameters> batchCreateParameters) {
    Collection<User> users = new ArrayList<>();
    for (UserCreateParameters createParameters : batchCreateParameters) {
      User user = new User();
      users.add(user);
      BeanUtils.copyProperties(createParameters, user);
      user.setCreateTime(new Date());
      user.setUpdateTime(user.getCreateTime());
    }
    userRepository.saveAll(users);
  }

  @Transactional
  @Override
  public void createUser(UserCreateParameters createParameters) {
    User user = new User();
    BeanUtils.copyProperties(createParameters, user);
    user.setCreateTime(new Date());
    user.setUpdateTime(user.getCreateTime());
    userRepository.save(user);
  }

  @Transactional
  @Override
  public void deleteUser(UserDeleteParameters deleteParameters) {
    userRepository.findById(deleteParameters.getId()).ifPresent(userRepository::delete);
  }

  @Override
  public PageResponse<UserQueryResponse> queryUsers(UserQueryParameters queryParameters) {
    // 页码从零开始的
    Pageable pageRequest = PageRequest.of(queryParameters.getPage() - 1, queryParameters.getSize());
    // 分页查询
    Page<User> pageResult = userRepository.findAll(new QuerySpecification(queryParameters), pageRequest);

    if (pageResult.isEmpty()) {
      return PageResponseCreator.create(queryParameters);
    }
    Page<UserQueryResponse> responsePageResult = pageResult.map(user -> {
      UserQueryResponse queryResponse = new UserQueryResponse();
      BeanUtils.copyProperties(user, queryResponse);
      return queryResponse;
    });

    return PageResponseCreator.create(queryParameters, responsePageResult.getContent(), (int) pageResult.getTotalElements());
  }

  @Transactional
  @Override
  public void assignUserRoles(UserRoleAssignParameters roleAssignParameters) {
    Optional<User> userOptional = userRepository.findById(roleAssignParameters.getId());
    userOptional.ifPresent(user -> {
      Set<Role> roles = user.getRoles();
      roles.clear();
      if (roleAssignParameters.getRoleIds().isEmpty()) {
        return;
      }
      List<Role> foundRoles = roleRepository.findAllById(roleAssignParameters.getRoleIds());
      if (!foundRoles.isEmpty()) {
        roles.addAll(foundRoles);
      }
    });
  }

  private static class QuerySpecification implements Specification<User> {

    private final UserQueryParameters queryParameters;

    private QuerySpecification(UserQueryParameters queryParameters) {
      this.queryParameters = queryParameters;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
      List<Predicate> predicates = new ArrayList<>();
      if (Objects.nonNull(queryParameters.getUsername())) {
        predicates.add(criteriaBuilder.like(root.get("username"), "%" + queryParameters.getUsername() + "%"));
      }
      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
  }
}
