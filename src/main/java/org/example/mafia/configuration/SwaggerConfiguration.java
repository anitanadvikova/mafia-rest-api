package org.example.mafia.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        ApiInfo metricsApiInfo = new ApiInfoBuilder()
                .version("1.0.0")
                .title("Mafia")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Mafia API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.example.mafia.controller"))
                .build()
                .apiInfo(metricsApiInfo);
    }
}
