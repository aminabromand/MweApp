package com.abromand.mweapp.web.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String API_BASE_MAPPING = "/api";

    private static final Logger LOG = LoggerFactory.getLogger(WebSecurityConfiguration.class);

    @Value("${mweapp.base-url.gui:http://localhost:8081}")
    private String allowedOrigin;

    private String allowedOrigin2 = "https://afternoon-mesa-12438.herokuapp.com/";

    private String allowedOriginAll = "*";

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private MweUserDetailsService userDetailsService;

//    @Override
//    public void configure(AuthenticationManagerBuilder builder) throws Exception {
//        super.configure(builder);
//        // @formatter:off
//        builder.userDetailsService(userDetailsService);
//        // @formatter:on
//
//    }

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
            "/js/**",
            "/gui/**"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        JwtUsernamePasswordAuthenticationFilter jwtUsernamePasswordAuthenticationFilter =
            new JwtUsernamePasswordAuthenticationFilter(authenticationManager());
        jwtUsernamePasswordAuthenticationFilter.setFilterProcessesUrl(API_BASE_MAPPING + "/login"); // this endpoint will receive JWT sign-in requests (should be POST only)

        // @formatter:off
        http
                .cors()
            .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no session cookie for API endpoints
            .and()
                .authorizeRequests()
                    .antMatchers("/api/user/requestpasswordreset").permitAll()
                    .anyRequest().authenticated() // all endpoints require JWT token (except /api/login defined above)
//            .anyRequest().permitAll() // all endpoints require JWT token (except /api/login defined above)
            .and()
                .exceptionHandling()
                    .authenticationEntryPoint(restAuthenticationEntryPoint) // handles unauthorized attempts to access protected URLS (except /api/login)
            .and()
                .addFilter(new JwtBasicAuthenticationFilter(authenticationManager(), userDetailsService)) // a filter to validate JWTs with each request
                .addFilter(jwtUsernamePasswordAuthenticationFilter); // a filter to process sign-in requests at /api/login
        // @formatter:on

        http.csrf().disable();
//
//        // TODO: security setup
//        http.antMatcher("/**").cors();
//
//        //TODO: re-enable (currently only configured to access h2-console)
//        http.headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        var source = new UrlBasedCorsConfigurationSource();

        // order is important, most specific first!

        LOG.info("Applying CORS config: allowed origin: '{}'", allowedOrigin);

        var loginConfig = new CorsConfiguration();
        loginConfig.setAllowCredentials(true);
//        loginConfig.addAllowedOrigin(allowedOriginAll);
        loginConfig.addAllowedOrigin(allowedOrigin);
//        loginConfig.addAllowedOrigin(allowedOrigin2);
        loginConfig.addAllowedHeader("*");
        loginConfig.addAllowedMethod("POST");
        loginConfig.addExposedHeader("Authorization");
        source.registerCorsConfiguration(API_BASE_MAPPING + "/login", loginConfig);

        var apiConfig = new CorsConfiguration();
        apiConfig.setAllowCredentials(true);
//        loginConfig.addAllowedOrigin(allowedOriginAll);
        apiConfig.addAllowedOrigin(allowedOrigin);
//        apiConfig.addAllowedOrigin(allowedOrigin2);
        apiConfig.addAllowedHeader("*");
        apiConfig.addAllowedMethod("*");
        source.registerCorsConfiguration(API_BASE_MAPPING + "/**", apiConfig);

        return new CorsFilter(source);
    }

    @Primary
    @Bean
    public ObjectMapper jacksonObjectMapper() {
        return new Jackson2ObjectMapperBuilder()
            .serializationInclusion(JsonInclude.Include.NON_EMPTY)
            .serializationInclusion(JsonInclude.Include.NON_NULL).build();
    }
}
