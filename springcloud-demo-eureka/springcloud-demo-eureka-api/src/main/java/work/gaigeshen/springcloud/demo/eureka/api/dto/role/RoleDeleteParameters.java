package work.gaigeshen.springcloud.demo.eureka.api.dto.role;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *
 * @author gaigeshen
 */
@Data
public class RoleDeleteParameters {

  @NotNull
  private Long id;

}
