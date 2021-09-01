package com.abromand.mweapp.web.security;

import com.abromand.mweapp.data.model.MweUser;
import com.abromand.mweapp.data.repository.MweUserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtBasicAuthenticationFilter extends BasicAuthenticationFilter {

    static final String JWT_AUTH_HEADER_PREFIX = "Bearer ";
    static final String JWT_SIGNATURE_KEY = "SecretKeyForJWTs";

    private final MweUserRepository userRepository;

    public JwtBasicAuthenticationFilter(AuthenticationManager authenticationManager, MweUserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
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

        return new UsernamePasswordAuthenticationToken(getOrCreateMweUser(username), null, new ArrayList<>());
    }

    MweUser getOrCreateMweUser(String username) {
        MweUser caminoUser = userRepository.findByUsername(username);
        if (caminoUser != null) return caminoUser;
        caminoUser = new MweUser();
        caminoUser.setUsername(username);
        return userRepository.save(caminoUser);
    }
}
