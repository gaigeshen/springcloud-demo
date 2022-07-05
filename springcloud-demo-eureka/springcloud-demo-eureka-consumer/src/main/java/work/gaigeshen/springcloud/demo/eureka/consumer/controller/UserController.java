package work.gaigeshen.springcloud.demo.eureka.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import work.gaigeshen.springcloud.demo.commons.PageResponse;
import work.gaigeshen.springcloud.demo.commons.web.Result;
import work.gaigeshen.springcloud.demo.eureka.api.UserApiService;
import work.gaigeshen.springcloud.demo.eureka.api.dto.user.*;

import java.util.List;

/**
 *
 * @author gaigeshen
 */
@Slf4j
@RequestMapping("/users")
@RestController
public class UserController {

  private final UserApiService userApiService;

  public UserController(UserApiService userApiService) {
    this.userApiService = userApiService;
  }

  @PostMapping("/batch")
  public Result<?> createBatchUsers(@RequestBody List<UserCreateParameters> batchCreateParameters) {
    log.debug("batch create users: {}", batchCreateParameters);
    return userApiService.createBatchUsers(batchCreateParameters);
  }

  @PostMapping
  public Result<?> createUser(@RequestBody UserCreateParameters createParameters) {
    log.debug("create user: {}", createParameters);
    return userApiService.createUser(createParameters);
  }

  @PostMapping("/delete")
  public Result<?> deleteUser(@RequestParam long id) {
    log.debug("delete user: {}", id);
    UserDeleteParameters deleteParameters = new UserDeleteParameters();
    deleteParameters.setId(id);
    return userApiService.deleteUser(deleteParameters);
  }

  @GetMapping
  public Result<PageResponse<UserQueryResponse>> queryUsers(UserQueryParameters queryParameters) {
    log.debug("query users: {}", queryParameters);
    return userApiService.queryUsers(queryParameters);
  }

  @PostMapping("/assign-roles")
  public Result<?> assignUserRoles(@RequestBody UserRoleAssignParameters roleAssignParameters) {
    log.debug("assign user roles: {}", roleAssignParameters);
    return userApiService.assignUserRoles(roleAssignParameters);
  }
}
