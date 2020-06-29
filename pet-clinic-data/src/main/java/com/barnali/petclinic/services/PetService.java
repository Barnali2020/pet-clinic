package com.barnali.petclinic.services;

import com.barnali.petclinic.model.Pet;
import java.util.Set;

public interface PetService {

    Pet findByLastName(String lastName);

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
