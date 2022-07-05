package work.gaigeshen.springcloud.demo.eureka.api.dto.role;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.springcloud.demo.commons.PageParameters;

/**
 *
 * @author gaigeshen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleQueryParameters extends PageParameters {

  private String name;

}
