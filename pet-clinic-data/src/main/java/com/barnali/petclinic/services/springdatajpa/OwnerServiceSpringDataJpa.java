package com.barnali.petclinic.services.springdatajpa;

import com.barnali.petclinic.model.Owner;
import com.barnali.petclinic.repositories.OwnerRepository;
import com.barnali.petclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("spring-data-jpa")           //We have two implementation for OwnerService (i.e OwnerServiceSpringDataJpa and OwnerServiceMap)
public class OwnerServiceSpringDataJpa implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired                      //this annotation is not needed but still adding it for remembrance
    public OwnerServiceSpringDataJpa(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }

    @Override
    public Owner findById(Long id) {
        /*Optional<Owner> ownerOptional = ownerRepository.findById(id);
        if(!ownerOptional.isPresent())
            return null;
        return ownerOptional.get();*/

        //Above code can be written as
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> ownerSet = new HashSet<>();
        ownerRepository.findAll().iterator().forEachRemaining(ownerSet::add);
        return ownerSet;
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }
}
