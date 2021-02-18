package com.evael.community.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

/**
 * @Author jiyou
 * @Date  2016/3/25.
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

        @Value("${spring.application.name:}")
        private String applicationName;

        @Bean
        public Docket apiDocket() {
                return new Docket(DocumentationType.OAS_30)
                        .apiInfo(apiInfo())
                        .select()
                                .apis(RequestHandlerSelectors.withClassAnnotation(FrameworkEndpoint.class))
                                .paths(PathSelectors.any())
                                .build().pathMapping(StringUtils.isEmpty(applicationName)?"":"/" + applicationName)

                        .globalRequestParameters(Arrays.asList(new RequestParameterBuilder()
                                .in(ParameterType.QUERY)
                                .name("access_token")
                                .description("oauth token")
                                .required(false)
                                .build()));
        }

        private ApiInfo apiInfo() {
                return new ApiInfoBuilder()
                        .title(applicationName)
                        //.description("Account management service")
                        .build();
        }
}
