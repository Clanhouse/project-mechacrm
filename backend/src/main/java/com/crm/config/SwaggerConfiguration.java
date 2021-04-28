package com.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket swaggerConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.crm"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "MechaCRM API",
                "Sample API for MechaCRM App.",
                "1.0",
                "Terms of service",
                new Contact("Github",
                        "https://github.com/Clanhouse/mecha-crm-app", "myaddress@company.com"),
                "API license",
                "API license URL",
                Collections.emptyList());
    }
}
