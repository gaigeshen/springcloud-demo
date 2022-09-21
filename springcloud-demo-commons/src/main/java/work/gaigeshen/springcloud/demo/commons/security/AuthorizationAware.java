package work.gaigeshen.springcloud.demo.commons.security;

import work.gaigeshen.springcloud.demo.commons.security.userdetails.UserDetails;

/**
 *
 * @author gaigeshen
 */
public interface AuthorizationAware {

    void setAuthorization(Authorization authorization);

    Authorization getAuthorization();

    default UserDetails getUserDetails() {
        Authorization authorization = getAuthorization();
        if (authorization instanceof UserDetails) {
            return (UserDetails) authorization;
        }
        return null;
    }
}
