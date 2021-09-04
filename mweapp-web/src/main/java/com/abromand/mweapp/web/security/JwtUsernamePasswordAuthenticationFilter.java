package com.abromand.mweapp.web.security;

import com.abromand.mweapp.data.model.MweUser;
import com.abromand.mweapp.web.dto.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if (!HttpMethod.POST.matches(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }


        LoginRequest loginRequest;
        try {
            loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
        } catch (IOException e) {
            throw new AuthenticationServiceException(e.getMessage());
        }

        return getAuthenticationManager().authenticate(
            getToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
    }

    UsernamePasswordAuthenticationToken getToken(String username, String password) {
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) {
        String token = Jwts.builder()
            .setSubject(((UserDetails) auth.getPrincipal()).getUsername())
            .setExpiration(new Date(System.currentTimeMillis() + 6000000))
            .signWith(SignatureAlgorithm.HS512, JwtBasicAuthenticationFilter.JWT_SIGNATURE_KEY)
            .compact();
        response.addHeader(HttpHeaders.AUTHORIZATION, JwtBasicAuthenticationFilter.JWT_AUTH_HEADER_PREFIX + token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        logger.warn("in JWTAuthenticationFilter's unsuccessfulAuthentication handler: " + e.getMessage());

        // TODO: enrich/improve error messages
        response.setStatus(response.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.getWriter().write("{\"error\": \"authentication error?\"}");

    }

    @Override
    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

}
