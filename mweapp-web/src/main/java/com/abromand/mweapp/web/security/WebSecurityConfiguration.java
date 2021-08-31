package com.abromand.mweapp.web.security;

import com.abromand.mweapp.data.repository.MweUserRepository;
import com.abromand.mweapp.data.repository.impl.MweUserRepositoryInMem;
import com.abromand.mweapp.web.security.securityadapter.MapAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableWebSecurity
@Configuration(value = "security-base-configuration")
public class WebSecurityConfiguration {

    @Bean
    public MweUserRepository userRepository() {
        return new MweUserRepositoryInMem(passwordEncoder());
    }

    @Bean
    public WebSecurityConfigurerAdapter defaultAdapter(@Qualifier("userdetailsservicemwe") UserDetailsService userDetailsService, MweUserRepository userRepository) {
        return new MapAdapter(userRepository, userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
