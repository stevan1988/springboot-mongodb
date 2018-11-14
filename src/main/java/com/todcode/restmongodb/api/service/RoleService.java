package com.todcode.restmongodb.api.service;

import com.todcode.restmongodb.api.dto.Role;
import com.todcode.restmongodb.api.dto.type.RoleType;
import com.todcode.restmongodb.api.repository.RoleRepository;
import com.todcode.restmongodb.web.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public Role findByType(RoleType type) {
        log.info("Find Role by RoleName " + type.toString());
        return repository.findByType(type).orElseThrow(() -> new ApplicationException("User Role not set."));
    }

    public Role addRole(RoleType type) {
        return repository.save(Role.builder().type(type).build());
    }
}
