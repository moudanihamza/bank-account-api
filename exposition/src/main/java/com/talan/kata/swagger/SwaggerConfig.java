package com.talan.kata.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apiOperation(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .groupName("InternalOperations")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.talan.kata"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    @Bean
    public Docket apiHealth(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .groupName("Health")
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.springframework.boot.actuate"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Bank Account Kata",
                "This API allowed to you to manage a specific account and transaction histories .",
                "SNAPSHOT VERSION 1",
                "Terms of service",
                new Contact("Hamza MOUDANI", "www.hamzamoudani.com", "moudanihamza@company.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
