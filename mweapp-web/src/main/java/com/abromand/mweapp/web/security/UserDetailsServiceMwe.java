package com.abromand.mweapp.web.security;

import com.abromand.mweapp.data.model.MweUser;
import com.abromand.mweapp.data.repository.MweUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier("userdetailsservicemwe")
public class UserDetailsServiceMwe implements UserDetailsService {

    @Autowired
    private MweUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        MweUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MweUserPrincipal(user);
    }
}
