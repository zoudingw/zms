package com.zdw.zms.web.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Author:zoudw
 * Since:JDK 8
 * Date:2019/5/24
 * Description:
 *
 * @Copyright:2019, zoudw@szinfinova.com All Rights Reserved
 */
@EnableSwagger2
@Configuration
@ConditionalOnExpression("${swagger.enable:true}")
public class SwaggerConfig {

    @Bean
    public Docket createAPI(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zdw.zms"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("zms-API")
                .contact(new Contact("admin","zdw.com.cn",""))
                .version("1.0")
                .description("API描述")
                .build();

    }
}
