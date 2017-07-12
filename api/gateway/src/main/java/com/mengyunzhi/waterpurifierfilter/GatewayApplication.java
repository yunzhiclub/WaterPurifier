package com.mengyunzhi.waterpurifierfilter;

import com.mengyunzhi.waterpurifierfilter.pre.IdentityFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;

@EnableZuulProxy
@EnableRedisHttpSession
@SpringBootApplication
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  @Bean
  public IdentityFilter identityFilter() {
    return new IdentityFilter();
  }

  // 设置HttpSession策略。默认读取header中的X-Auth-Token,作为sessionId。
  @Bean
  HeaderHttpSessionStrategy sessionStrategy()	 {
    return new HeaderHttpSessionStrategy();
  }
}
