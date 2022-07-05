package work.gaigeshen.springcloud.demo.eureka.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import work.gaigeshen.springcloud.demo.commons.PageResponse;
import work.gaigeshen.springcloud.demo.commons.web.Result;
import work.gaigeshen.springcloud.demo.eureka.api.dto.role.RoleCreateParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.role.RoleDeleteParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.role.RoleQueryParameters;
import work.gaigeshen.springcloud.demo.eureka.api.dto.role.RoleQueryResponse;

import java.util.List;

/**
 *
 * @author gaigeshen
 */
@FeignClient(name = "spring-cloud-eureka-provider", path = "/roles", contextId = "roleApiService")
public interface RoleApiService {

  @PostMapping("/batch-create")
  Result<?> createBatchRoles(@RequestBody List<RoleCreateParameters> batchCreateParameters);

  @PostMapping("/create")
  Result<?> createRole(@RequestBody RoleCreateParameters createParameters);

  @PostMapping("/delete")
  Result<?> deleteRole(@RequestBody RoleDeleteParameters deleteParameters);

  @PostMapping("/query")
  Result<PageResponse<RoleQueryResponse>> queryRoles(@RequestBody RoleQueryParameters queryParameters);

}
