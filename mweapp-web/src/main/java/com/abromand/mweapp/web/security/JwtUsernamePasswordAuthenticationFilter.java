package com.abromand.mweapp.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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


        LoginRequest creds;
        try {
            creds = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
        } catch (IOException e) {
            throw new AuthenticationServiceException(e.getMessage());
        }

        return getAuthenticationManager().authenticate(
            getToken(creds.getUsername(), creds.getPassword())
        );
    }

    UsernamePasswordAuthenticationToken getToken(String username, String password) {
        return new UsernamePasswordAuthenticationToken(
            username,
            password,
            new ArrayList<>()
        );
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) {
        String token = Jwts.builder()
            .setSubject(((UserDetails) auth.getPrincipal()).getUsername())
            .setExpiration(new Date(System.currentTimeMillis() + 6000000))
            .signWith(SignatureAlgorithm.HS512, JwtAuthenticationFilter.JWT_SIGNATURE_KEY)
            .compact();
        response.addHeader(HttpHeaders.AUTHORIZATION, JwtAuthenticationFilter.JWT_AUTH_HEADER_PREFIX + token);
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

    public static class LoginRequest {
        private String username;
        private String password;
        private boolean returnSecureToken;

        public LoginRequest() {
        }

        public LoginRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public boolean isReturnSecureToken() {
            return returnSecureToken;
        }

        public void setReturnSecureToken(boolean returnSecureToken) {
            this.returnSecureToken = returnSecureToken;
        }
    }
}
