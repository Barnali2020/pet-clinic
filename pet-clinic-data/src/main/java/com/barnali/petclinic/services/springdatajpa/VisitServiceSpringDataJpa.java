package com.barnali.petclinic.services.springdatajpa;

import com.barnali.petclinic.model.Owner;
import com.barnali.petclinic.model.Visit;
import com.barnali.petclinic.repositories.VisitRepository;
import com.barnali.petclinic.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("spring-data-jpa")
public class VisitServiceSpringDataJpa implements VisitService {

    private final VisitRepository visitRepository;

    @Autowired
    public VisitServiceSpringDataJpa(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Visit findById(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();
        visitRepository.findAll().iterator().forEachRemaining(visits::add);
        return visits;
    }

    @Override
    public Visit save(Visit object) {
        return visitRepository.save(object);
    }

    @Override
    public void delete(Visit object) {
        visitRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        visitRepository.deleteById(id);
    }
}
