package com.todcode.restmongodb.api.repository;

import com.todcode.restmongodb.api.dto.Pets;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PetsRepository extends MongoRepository<Pets, String> {
    Pets findBy_id(ObjectId _id);
}