package com.todcode.restmongodb.api.service;

import com.todcode.restmongodb.api.dto.User;
import com.todcode.restmongodb.api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    public User findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));
    }
    public User findByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));
    }
    public User save(User user) {
        return repository.save(user);
    }
    public Boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }
}
