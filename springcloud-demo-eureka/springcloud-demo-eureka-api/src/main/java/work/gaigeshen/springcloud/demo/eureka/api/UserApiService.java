package work.gaigeshen.springcloud.demo.eureka.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import work.gaigeshen.springcloud.demo.commons.PageResponse;
import work.gaigeshen.springcloud.demo.commons.web.Result;
import work.gaigeshen.springcloud.demo.eureka.api.dto.user.*;

import java.util.List;

/**
 *
 * @author gaigeshen
 */
@FeignClient(name = "spring-cloud-eureka-provider", path = "/users", contextId = "userApiService")
public interface UserApiService {

  @PostMapping("/batch-create")
  Result<?> createBatchUsers(@RequestBody List<UserCreateParameters> batchCreateParameters);

  @PostMapping("/create")
  Result<?> createUser(@RequestBody UserCreateParameters createParameters);

  @PostMapping("/delete")
  Result<?> deleteUser(@RequestBody UserDeleteParameters deleteParameters);

  @PostMapping("/query")
  Result<PageResponse<UserQueryResponse>> queryUsers(@RequestBody UserQueryParameters queryParameters);

  @PostMapping("/assign-roles")
  Result<?> assignUserRoles(@RequestBody UserRoleAssignParameters roleAssignParameters);
}
