package work.gaigeshen.springcloud.demo.gateway.security;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author gaigeshen
 */
@Configuration
public class ShiroSecurityConfiguration {

  @Bean
  public CacheManager cacheManager() {
    return new MemoryConstrainedCacheManager();
  }

  @Bean
  public ShiroFilterChainDefinition shiroFilterChainDefinition() {
    DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
    definition.addPathDefinition("/**", "anon");
    return definition;
  }
}
