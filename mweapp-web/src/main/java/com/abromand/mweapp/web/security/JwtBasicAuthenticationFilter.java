package com.abromand.mweapp.web.security;

import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtBasicAuthenticationFilter extends BasicAuthenticationFilter {

    static final String JWT_AUTH_HEADER_PREFIX = "Bearer ";
    static final String JWT_SIGNATURE_KEY = "SecretKeyForJWTs";

    private final MweUserDetailsService userDetailsService;

    public JwtBasicAuthenticationFilter(AuthenticationManager authenticationManager, MweUserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith(JWT_AUTH_HEADER_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        String token = authorizationHeader.replace(JWT_AUTH_HEADER_PREFIX, "");

        UsernamePasswordAuthenticationToken authentication = getAuthenticationToken(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        String username = null;
        try {
            username = Jwts.parser()
                .setSigningKey(JWT_SIGNATURE_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        } catch (Exception e) {
            //username remains null
        }

        if (username == null) return null;

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
