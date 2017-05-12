package com.mengyunzhi.waterPurifierApi;

import com.google.common.base.Predicates;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.ant;

// Swagger2 自动化文档配置
@EnableSwagger2
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Bean
    public Docket restApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host("http://www.mengyunzhi.cn:8000")
                .apiInfo(apiInfo())
                .select()
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("纯净水直供API接口文档")
                .description("本页面由swagger + swagger2markup + springfox + spring-restdocs-mockmvc 自动生成。相关注解文档地址：http://docs.swagger.io/swagger-core/v1.5.0/apidocs/allclasses-noframe.html")
                .contact(new Contact("河北工业大学 梦云智软件开发团队", "http://www.mengyunzhi.com", "panjie@yunzhiclub.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("0.0.1")
                .build();
    }
}