package com.yang.jk.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @auther yhjStart
 * @create 2022-03-27 21:37
 */
@Configuration
public class ValidatorConfig {
    @Bean
    public Validator validator() {
        return Validation
                .byProvider(HibernateValidator.class)
                .configure()
                .failFast(true) //fast fail ,No goto All valid
                .buildValidatorFactory()
                .getValidator();
    }
}
