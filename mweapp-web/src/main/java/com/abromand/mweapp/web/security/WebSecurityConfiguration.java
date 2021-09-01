package com.abromand.mweapp.web.security;

import com.abromand.mweapp.data.repository.MweUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(WebSecurityConfiguration.class);

    @Autowired
    private MweUserRepository userRepository;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private MweUserDetailsService userDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        super.configure(builder);
        // @formatter:off
        //builder.userDetailsService(userDetailsService);
        // @formatter:on

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().mvcMatchers(
            "/favicon.ico",
            "/",
            "/index.html",
            "/css/**",
            "/fonts/**",
            "/img/**",
            "/js/**"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        JwtUsernamePasswordAuthenticationFilter jwtUsernamePasswordAuthenticationFilter =
            new JwtUsernamePasswordAuthenticationFilter(authenticationManager());
        jwtUsernamePasswordAuthenticationFilter.setFilterProcessesUrl("/login"); // this endpoint will receive JWT sign-in requests (should be POST only)

        // @formatter:off
        http
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no session cookie for API endpoints
            .and()
                .authorizeRequests()
                    .anyRequest().authenticated() // all endpoints require JWT token (except /api/login defined above)
//            .anyRequest().permitAll() // all endpoints require JWT token (except /api/login defined above)
            .and()
                .exceptionHandling()
                    .authenticationEntryPoint(restAuthenticationEntryPoint) // handles unauthorized attempts to access protected URLS (except /api/login)
            .and()
                .addFilter(new JwtBasicAuthenticationFilter(authenticationManager(), userRepository)) // a filter to validate JWTs with each request
                .addFilter(jwtUsernamePasswordAuthenticationFilter); // a filter to process sign-in requests at /api/login
        // @formatter:on

        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
