package com.mengyunzhi.waterpurifierfilter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
 /**
 * Created by chuhang on 17-7-3.
 */
@EnableRedisHttpSession
public class SessionConfig {
    @Bean
    public JedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory();
    }
}
