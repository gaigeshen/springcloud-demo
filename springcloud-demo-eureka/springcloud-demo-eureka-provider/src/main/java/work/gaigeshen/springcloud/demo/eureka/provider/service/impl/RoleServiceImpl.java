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
import work.gaigeshen.springcloud.demo.eureka.api.dto.role.RoleCreateParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.role.RoleDeleteParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.role.RoleQueryParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.role.RoleQueryResponse;
import work.gaigeshen.springcloud.demo.eureka.provider.entity.Role;
import work.gaigeshen.springcloud.demo.eureka.provider.repository.RoleRepository;
import work.gaigeshen.springcloud.demo.eureka.provider.service.RoleService;

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
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  public RoleServiceImpl(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Transactional
  @Override
  public void createBatchRoles(List<RoleCreateParameters> batchCreateParameters) {
    Collection<Role> roles = new ArrayList<>();
    for (RoleCreateParameters createParameters : batchCreateParameters) {
      Role role = new Role();
      roles.add(role);
      BeanUtils.copyProperties(createParameters, role);
      role.setCreateTime(new Date());
      role.setUpdateTime(role.getCreateTime());
    }
    roleRepository.saveAll(roles);
  }

  @Transactional
  @Override
  public void createRole(RoleCreateParameters createParameters) {
    Role role = new Role();
    BeanUtils.copyProperties(createParameters, role);
    role.setCreateTime(new Date());
    role.setUpdateTime(role.getCreateTime());
    roleRepository.save(role);
  }

  @Transactional
  @Override
  public void deleteRole(RoleDeleteParameters deleteParameters) {
    roleRepository.findById(deleteParameters.getId()).ifPresent(roleRepository::delete);
  }

  @Override
  public PageResponse<RoleQueryResponse> queryRoles(RoleQueryParameters queryParameters) {
    // 页码从零开始的
    Pageable pageRequest = PageRequest.of(queryParameters.getPage() - 1, queryParameters.getSize());
    // 分页查询
    Page<Role> pageResult = roleRepository.findAll(new QuerySpecification(queryParameters), pageRequest);

    if (pageResult.isEmpty()) {
      return PageResponseCreator.create(queryParameters);
    }
    Page<RoleQueryResponse> responsePageResult = pageResult.map(role -> {
      RoleQueryResponse queryResponse = new RoleQueryResponse();
      BeanUtils.copyProperties(role, queryResponse);
      return queryResponse;
    });

    return PageResponseCreator.create(queryParameters, responsePageResult.getContent(), (int) pageResult.getTotalElements());
  }

  private static class QuerySpecification implements Specification<Role> {

    private final RoleQueryParameters queryParameters;

    private QuerySpecification(RoleQueryParameters queryParameters) {
      this.queryParameters = queryParameters;
    }

    @Override
    public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
      List<Predicate> predicates = new ArrayList<>();
      if (Objects.nonNull(queryParameters.getName())) {
        predicates.add(criteriaBuilder.like(root.get("name"), "%" + queryParameters.getName() + "%"));
      }
      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
  }
}
