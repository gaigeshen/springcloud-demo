package work.gaigeshen.springcloud.demo.eureka.provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import work.gaigeshen.springcloud.demo.eureka.provider.entity.User;

/**
 *
 * @author gaigeshen
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

}
