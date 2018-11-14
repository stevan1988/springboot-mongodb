package com.todcode.restmongodb.web.security;

import com.todcode.restmongodb.api.dto.User;
import com.todcode.restmongodb.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        return UserDetailsImpl.create(user);
    }

    @Transactional
    public UserDetails loadUserById(String id) {
        User user = userService.findById(id);
        return UserDetailsImpl.create(user);
    }
}
