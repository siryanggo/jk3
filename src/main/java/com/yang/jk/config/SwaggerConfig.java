package com.yang.jk.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auther yhjStart
 * @create 2022-03-27 20:55
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements InitializingBean {
    @Autowired
    private Environment environment;
    private boolean enable;

    @Bean
    public Docket yangDocket() {
            return groupDocket("yangDocket",
                    "com.yang.jk.controller",
                    "api-document","1.0.0",
                     "The is One document"
                    );
    }
    @Bean
    public Docket JDocket() {
        return groupDocket("JDocket",
                "com.yang.jk.controller",
                "api-document","1.0.0",
                "The is One document"
        );
    }

    private Docket groupDocket(String groupName,String basePackage,String title,String version,String description) {

            return basicDocket()
                    .groupName(groupName)
                    .apiInfo(apiInfo(title,version,description))
                    .select()
                    .apis(RequestHandlerSelectors.basePackage(basePackage))
                    .build();
    }

    private Docket basicDocket() {
        List<RequestParameter> list = new ArrayList<>();
        RequestParameter token = new RequestParameterBuilder()
        .name("token")
                .description("用户登录令牌")
                .in(ParameterType.HEADER)
                .build();
        list.add(token);
        return new Docket(DocumentationType.SWAGGER_2)
                .globalRequestParameters(list)
                .ignoredParameterTypes(
                        HttpSession.class,
                        HttpServletRequest.class,
                        HttpServletResponse.class
                )
                .enable(true);
    }



        private ApiInfo apiInfo(String title,String version,String description) {
           return new ApiInfoBuilder()
                    .title(title)
                    .version(version)
                    .description(description)
                   .build();
        }

    @Override
    public void afterPropertiesSet() throws Exception {
        enable = environment.acceptsProfiles(Profiles.of("dev"));
    }
}
