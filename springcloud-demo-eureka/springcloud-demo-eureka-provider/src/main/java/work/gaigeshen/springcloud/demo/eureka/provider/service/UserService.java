package work.gaigeshen.springcloud.demo.eureka.provider.service;

import org.springframework.validation.annotation.Validated;
import work.gaigeshen.springcloud.demo.commons.PageResponse;
import work.gaigeshen.springcloud.demo.eureka.api.dto.user.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author gaigeshen
 */
@Validated
public interface UserService {

  void createBatchUsers(@NotEmpty @Valid List<UserCreateParameters> batchCreateParameters);

  void createUser(@NotNull @Valid UserCreateParameters createParameters);

  void deleteUser(@NotNull @Valid UserDeleteParameters deleteParameters);

  PageResponse<UserQueryResponse> queryUsers(@NotNull @Valid UserQueryParameters queryParameters);

  void assignUserRoles(@NotNull @Valid UserRoleAssignParameters roleAssignParameters);
}
