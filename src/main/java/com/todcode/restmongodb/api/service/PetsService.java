package com.todcode.restmongodb.api.service;

import com.todcode.restmongodb.api.dto.Pets;
import com.todcode.restmongodb.api.repository.PetsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetsService {

    @Autowired
    private PetsRepository repository;

    public List<Pets> getAllPets() {
        return repository.findAll();
    }

    public Pets getPetById(ObjectId id) {
        return repository.findBy_id(id);
    }

    public Pets save(Pets pets) {
        return repository.save(pets);
    }

    public Pets create(Pets pets) {
        pets.set_id(ObjectId.get());
        return repository.save(pets);
    }

    public void deletePet(ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }

}
