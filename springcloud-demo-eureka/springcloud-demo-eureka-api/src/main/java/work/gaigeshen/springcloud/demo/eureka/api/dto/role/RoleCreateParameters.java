package work.gaigeshen.springcloud.demo.eureka.api.dto.role;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author gaigeshen
 */
@Data
public class RoleCreateParameters {

  @NotBlank
  private String name;

}
