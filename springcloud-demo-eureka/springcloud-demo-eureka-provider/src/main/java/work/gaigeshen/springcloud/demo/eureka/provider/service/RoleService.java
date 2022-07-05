package work.gaigeshen.springcloud.demo.eureka.provider.service;

import org.springframework.validation.annotation.Validated;
import work.gaigeshen.springcloud.demo.commons.PageResponse;
import work.gaigeshen.springcloud.demo.eureka.api.dto.role.RoleCreateParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.role.RoleDeleteParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.role.RoleQueryParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.role.RoleQueryResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author gaigeshen
 */
@Validated
public interface RoleService {

  void createBatchRoles(@NotEmpty @Valid List<RoleCreateParameters> batchCreateParameters);

  void createRole(@NotNull @Valid RoleCreateParameters createParameters);

  void deleteRole(@NotNull @Valid RoleDeleteParameters deleteParameters);

  PageResponse<RoleQueryResponse> queryRoles(@NotNull @Valid RoleQueryParameters queryParameters);
}
