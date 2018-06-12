package com.device.tracker.security;

import com.device.tracker.domain.ApplicationUser;
import com.device.tracker.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        ApplicationUser applicationUser = applicationUserRepository.findByUsername(userName);
        if(applicationUser == null){
            throw new UsernameNotFoundException(userName);
        }

        return new User(applicationUser.getUsername(),applicationUser.getPassword(),Collections.emptyList());
    }
}
