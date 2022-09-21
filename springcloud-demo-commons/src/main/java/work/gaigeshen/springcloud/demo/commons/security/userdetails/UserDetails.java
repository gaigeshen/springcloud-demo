package work.gaigeshen.springcloud.demo.commons.security.userdetails;

import work.gaigeshen.springcloud.demo.commons.security.Authorization;

import java.util.Map;

/**
 *
 * @author gaigeshen
 */
public interface UserDetails extends Authorization {

    Map<String, Object> getProperties();

    boolean isDisabled();

    boolean isLocked();
}
