package com.todcode.restmongodb.api.repository;

import com.todcode.restmongodb.api.dto.Role;
import com.todcode.restmongodb.api.dto.type.RoleType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByType(RoleType roleType);
}
