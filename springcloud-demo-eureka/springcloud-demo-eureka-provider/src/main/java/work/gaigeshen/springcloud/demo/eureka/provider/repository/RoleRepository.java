package work.gaigeshen.springcloud.demo.eureka.provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import work.gaigeshen.springcloud.demo.eureka.provider.entity.Role;

/**
 *
 * @author gaigeshen
 */
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

}
