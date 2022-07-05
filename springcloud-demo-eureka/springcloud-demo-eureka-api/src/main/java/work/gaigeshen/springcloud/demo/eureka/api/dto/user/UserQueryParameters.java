package work.gaigeshen.springcloud.demo.eureka.api.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.springcloud.demo.commons.PageParameters;

/**
 *
 * @author gaigeshen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryParameters extends PageParameters {

  private String username;

}
