package work.gaigeshen.springcloud.demo.eureka.api.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *
 * @author gaigeshen
 */
@Data
public class UserDeleteParameters {

  @NotNull
  private Long id;

}
