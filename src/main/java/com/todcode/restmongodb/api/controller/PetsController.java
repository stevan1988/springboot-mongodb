package com.todcode.restmongodb.api.controller;

import com.todcode.restmongodb.api.dto.Pets;
import com.todcode.restmongodb.api.service.PetsService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/pets")
public class PetsController {

    @Autowired
    private PetsService petsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Pets> getAllPets() {
        return petsService.getAllPets();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Pets getPetById(@PathVariable("id") ObjectId id) {
        return petsService.getPetById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyPetById(@PathVariable("id") ObjectId id, @Valid @RequestBody Pets pets) {
        pets.set_id(id);
        petsService.save(pets);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Pets createPet(@Valid @RequestBody Pets pets) {
        return petsService.create(pets);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePet(@PathVariable ObjectId id) {
        petsService.deletePet(id);
    }
}
