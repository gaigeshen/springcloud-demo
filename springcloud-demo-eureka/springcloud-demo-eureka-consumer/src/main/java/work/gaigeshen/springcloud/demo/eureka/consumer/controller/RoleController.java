package work.gaigeshen.springcloud.demo.eureka.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import work.gaigeshen.springcloud.demo.commons.PageResponse;
import work.gaigeshen.springcloud.demo.commons.web.Result;
import work.gaigeshen.springcloud.demo.eureka.api.RoleApiService;
import work.gaigeshen.springcloud.demo.eureka.api.dto.role.RoleCreateParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.role.RoleDeleteParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.role.RoleQueryParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.role.RoleQueryResponse;

import java.util.List;

/**
 *
 * @author gaigeshen
 */
@Slf4j
@RequestMapping("/roles")
@RestController
public class RoleController {

  private final RoleApiService roleApiService;

  public RoleController(RoleApiService roleApiService) {
    this.roleApiService = roleApiService;
  }

  @PostMapping("/batch")
  public Result<?> createBatchRoles(@RequestBody List<RoleCreateParameters> batchCreateParameters) {
    log.debug("batch create roles: {}", batchCreateParameters);
    return roleApiService.createBatchRoles(batchCreateParameters);
  }

  @PostMapping
  public Result<?> createRole(@RequestBody RoleCreateParameters createParameters) {
    log.debug("create role: {}", createParameters);
    return roleApiService.createRole(createParameters);
  }

  @PostMapping("/delete")
  public Result<?> deleteRole(@RequestParam long id) {
    log.debug("delete role: {}", id);
    RoleDeleteParameters deleteParameters = new RoleDeleteParameters();
    deleteParameters.setId(id);
    return roleApiService.deleteRole(deleteParameters);
  }

  @GetMapping
  public Result<PageResponse<RoleQueryResponse>> queryRoles(RoleQueryParameters queryParameters) {
    log.debug("query roles: {}", queryParameters);
    return roleApiService.queryRoles(queryParameters);
  }
}
