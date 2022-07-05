package work.gaigeshen.springcloud.demo.eureka.api.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author gaigeshen
 */
@Data
public class UserRoleAssignParameters {

  @NotNull
  private Long id;

  @NotNull
  private Set<Long> roleIds;
}
