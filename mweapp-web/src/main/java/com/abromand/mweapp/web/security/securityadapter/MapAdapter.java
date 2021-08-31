package com.abromand.mweapp.web.security.securityadapter;

import com.abromand.mweapp.data.repository.MweUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;


public class MapAdapter extends AbstractAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(MapAdapter.class);

    UserDetailsService userDetailsService;

    public MapAdapter(MweUserRepository userRepository, UserDetailsService userDetailsService) {
        super(userRepository);
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {

        // @formatter:off
        builder
            .userDetailsService(userDetailsService);
        // @formatter:on

    }

}
