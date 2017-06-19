package com.mengyunzhi.waterpurifierfilter;

import com.mengyunzhi.waterpurifierfilter.pre.IdentityFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  @Bean
  public IdentityFilter identityFilter() {
    return new IdentityFilter();
  }

}
