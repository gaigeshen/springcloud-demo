package work.gaigeshen.springcloud.demo.eureka.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.gaigeshen.springcloud.demo.commons.PageResponse;
import work.gaigeshen.springcloud.demo.commons.web.Result;
import work.gaigeshen.springcloud.demo.commons.web.ResultCreator;
import work.gaigeshen.springcloud.demo.eureka.api.UserApiService;
import work.gaigeshen.springcloud.demo.eureka.api.dto.user.*;
import work.gaigeshen.springcloud.demo.eureka.provider.service.UserService;

import java.util.List;

/**
 *
 * @author gaigeshen
 */
@Slf4j
@RequestMapping("/users")
@RestController
public class UserApiController implements UserApiService {

  private final UserService userService;

  public UserApiController(UserService userService) {
    this.userService = userService;
  }

  @Override
  public Result<?> createBatchUsers(List<UserCreateParameters> batchCreateParameters) {
    log.debug("batch create users: {}", batchCreateParameters);
    userService.createBatchUsers(batchCreateParameters);
    return ResultCreator.create();
  }

  @Override
  public Result<?> createUser(UserCreateParameters createParameters) {
    log.debug("create user: {}", createParameters);
    userService.createUser(createParameters);
    return ResultCreator.create();
  }

  @Override
  public Result<?> deleteUser(UserDeleteParameters deleteParameters) {
    log.debug("delete user: {}", deleteParameters);
    userService.deleteUser(deleteParameters);
    return ResultCreator.create();
  }

  @Override
  public Result<PageResponse<UserQueryResponse>> queryUsers(UserQueryParameters queryParameters) {
    log.debug("query users: {}", queryParameters);
    return ResultCreator.create(userService.queryUsers(queryParameters));
  }

  @Override
  public Result<?> assignUserRoles(UserRoleAssignParameters roleAssignParameters) {
    log.debug("assign user roles: {}", roleAssignParameters);
    userService.assignUserRoles(roleAssignParameters);
    return ResultCreator.create();
  }
}
