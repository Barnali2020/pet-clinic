package com.barnali.petclinic.services.springdatajpa;

import com.barnali.petclinic.model.Owner;
import com.barnali.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerServiceSpringDataJpaTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerServiceSpringDataJpa ownerServiceSpringDataJpa;

    @Test
    void findByLastName() {

        String expectedLastName = "Bhowmik";

        Owner owner = Owner.builder().id(1L).lastName(expectedLastName).build();

        when(ownerRepository.findByLastName(any())).thenReturn(owner);

        Owner returnedOwner = ownerServiceSpringDataJpa.findByLastName(expectedLastName);

        assertEquals(expectedLastName, returnedOwner.getLastName());
    }

    @Test
    void findById() {
        Long expectedId = 1L;

        //create owner object using expected id value
        Owner owner = Owner.builder().id(expectedId).build();

        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));

        assertNotNull(owner);
        assertEquals(expectedId, ownerServiceSpringDataJpa.findById(expectedId).getId());

    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = new HashSet<>();
        ownerSet.add(Owner.builder().id(1L).build());
        ownerSet.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(ownerSet);

        Set<Owner> listOwners = ownerServiceSpringDataJpa.findAll();

        assertNotNull(listOwners);
        assertEquals(2, listOwners.size());
    }

    @Test
    void save() {
        Owner ownerToPersist = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(ownerToPersist);

        Owner savedOwner = ownerServiceSpringDataJpa.save(ownerToPersist);

        assertNotNull(savedOwner);

    }

    @Test
    void delete() {
        Owner ownerToDelete = Owner.builder().id(1L).build();

        ownerServiceSpringDataJpa.delete(ownerToDelete);

        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {

        Long expectedId = 1L;

        ownerServiceSpringDataJpa.deleteById(expectedId);

        verify(ownerRepository, times(1)).deleteById(anyLong());
    }
}