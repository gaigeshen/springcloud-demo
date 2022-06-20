package work.gaigeshen.springcloud.demo.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *
 * @author gaigeshen
 */
@EnableEurekaServer
@SpringBootApplication
public class SpringCloudEurekaServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringCloudEurekaServerApplication.class, args);
  }
}
