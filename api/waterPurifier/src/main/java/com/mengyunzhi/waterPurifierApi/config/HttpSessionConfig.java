package com.mengyunzhi.waterPurifierApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by chuhang on 2017/6/28.
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {
}
