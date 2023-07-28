package com.kcxuao.serverUI.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@EnableOpenApi
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                // swagger 文档扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.kcxuao.serverUI.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口列表")
                .description("Swagger 接口文档")
                .version("v1.0.0")
                .license("Apache License, Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }
}
