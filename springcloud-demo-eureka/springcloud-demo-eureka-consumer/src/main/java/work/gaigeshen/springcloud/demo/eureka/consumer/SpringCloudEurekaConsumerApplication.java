package work.gaigeshen.springcloud.demo.eureka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import work.gaigeshen.springcloud.demo.eureka.api.ProductApiService;

@EnableFeignClients(clients = { ProductApiService.class })
@SpringBootApplication
public class SpringCloudEurekaConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringCloudEurekaConsumerApplication.class, args);
  }

}
