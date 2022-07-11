package work.gaigeshen.springcloud.demo.eureka.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.gaigeshen.springcloud.demo.commons.PageResponse;
import work.gaigeshen.springcloud.demo.commons.web.Result;
import work.gaigeshen.springcloud.demo.commons.web.Results;
import work.gaigeshen.springcloud.demo.eureka.api.RoleApiService;
import work.gaigeshen.springcloud.demo.eureka.api.dto.role.RoleCreateParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.role.RoleDeleteParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.role.RoleQueryParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.role.RoleQueryResponse;
import work.gaigeshen.springcloud.demo.eureka.provider.service.RoleService;

import java.util.List;

/**
 *
 * @author gaigeshen
 */
@Slf4j
@RequestMapping("/roles")
@RestController
public class RoleApiController implements RoleApiService {

  private final RoleService roleService;

  public RoleApiController(RoleService roleService) {
    this.roleService = roleService;
  }

  @Override
  public Result<?> createBatchRoles(List<RoleCreateParameters> batchCreateParameters) {
    log.debug("batch create roles: {}", batchCreateParameters);
    roleService.createBatchRoles(batchCreateParameters);
    return Results.create();
  }

  @Override
  public Result<?> createRole(RoleCreateParameters createParameters) {
    log.debug("create role: {}", createParameters);
    roleService.createRole(createParameters);
    return Results.create();
  }

  @Override
  public Result<?> deleteRole(RoleDeleteParameters deleteParameters) {
    log.debug("delete role: {}", deleteParameters);
    roleService.deleteRole(deleteParameters);
    return Results.create();
  }

  @Override
  public Result<PageResponse<RoleQueryResponse>> queryRoles(RoleQueryParameters queryParameters) {
    log.debug("query roles: {}", queryParameters);
    return Results.create(roleService.queryRoles(queryParameters));
  }
}
