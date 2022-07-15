package work.gaigeshen.springcloud.demo.eureka.provider.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.SqlSessionFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author gaigeshen
 */
@Configuration
public class MyBatisConfig {

  @Bean
  public ConfigurationCustomizer configurationCustomizer() {
    return configuration -> {
      //
      configuration.addInterceptor(new PagingInterceptor());
    };
  }

  @Bean
  public SqlSessionFactoryBeanCustomizer sqlSessionFactoryBeanCustomizer() {
    return factoryBean -> {

    };
  }
}
