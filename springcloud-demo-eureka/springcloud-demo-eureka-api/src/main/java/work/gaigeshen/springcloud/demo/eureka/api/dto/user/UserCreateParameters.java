package work.gaigeshen.springcloud.demo.eureka.api.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author gaigeshen
 */
@Data
public class UserCreateParameters {

  @NotBlank
  private String username;

  @NotBlank
  private String password;

}
