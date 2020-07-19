package com.barnali.petclinic.services.springdatajpa;

import com.barnali.petclinic.model.Vet;
import com.barnali.petclinic.repositories.VetRepository;
import com.barnali.petclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("spring-data-jpa")
public class VetServiceSpringDataJpa implements VetService {

    private VetRepository vetRepository;

    @Autowired
    public VetServiceSpringDataJpa(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Vet findByLastName(String lastName) {
        return vetRepository.findByLastName(lastName);
    }

    @Override
    public Vet findById(Long id) {
        Optional<Vet> vetOptional = vetRepository.findById(id);
        if(!vetOptional.isPresent())
            return null;
        return vetOptional.get();
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vetSet = new HashSet<>();
        vetRepository.findAll().forEach(vetSet::add);
        return vetSet;
    }

    @Override
    public Vet save(Vet object) {
        return vetRepository.save(object);
    }

    @Override
    public void delete(Vet object) {
        vetRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        vetRepository.deleteById(id);
    }
}
