package com.abromand.mweapp.web.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Profile("dev")
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
@Order(66)
public class DevWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // allow access to h2-console
        http.antMatcher("/h2-console/**");
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
