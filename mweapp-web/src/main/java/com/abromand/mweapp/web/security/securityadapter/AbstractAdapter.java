package com.abromand.mweapp.web.security.securityadapter;

import com.abromand.mweapp.data.repository.MweUserRepository;
import com.abromand.mweapp.web.security.JwtAuthenticationFilter;
import com.abromand.mweapp.web.security.JwtUsernamePasswordAuthenticationFilter;
import com.abromand.mweapp.web.security.RestAuthenticationEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

public abstract class AbstractAdapter extends WebSecurityConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractAdapter.class);

    private final MweUserRepository userRepository;

    AbstractAdapter(MweUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        LOG.info("Configuring {}", getClass());
        super.configure(web);
        web.ignoring().mvcMatchers(
            "/favicon.ico",
            "/index.html",
            "/css/**",
            "/fonts/**",
            "/img/**",
            "/js/**"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        JwtUsernamePasswordAuthenticationFilter jwtUsernamePasswordAuthenticationFilter = new JwtUsernamePasswordAuthenticationFilter(
            authenticationManager());
        jwtUsernamePasswordAuthenticationFilter.setFilterProcessesUrl("/api/login"); // this endpoint will receive JWT sign-in requests (should be POST only)

        // @formatter:off
        http.antMatcher("/api/**")
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no session cookie for API endpoints
            .and()
                .authorizeRequests()
                    //.anyRequest().authenticated() // all endpoints require JWT token (except /api/login defined above)
                    .anyRequest().permitAll() // all endpoints require JWT token (except /api/login defined above)
            .and()
                .exceptionHandling()
                    .authenticationEntryPoint(new RestAuthenticationEntryPoint()) // handles unauthorized attempts to access protected URLS (except /api/login)
            .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), userRepository)) // a filter to validate JWTs with each request
                .addFilter(jwtUsernamePasswordAuthenticationFilter); // a filter to process sign-in requests at /api/login
        // @formatter:on

        http.antMatcher("/api/**").csrf().disable();
    }
}
