package com.barnali.petclinic.services.map;

import com.barnali.petclinic.model.Owner;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)           //enforce execution of test methods according to order number
class OwnerServiceMapTest {

    @Mock
    OwnerServiceMap ownerServiceMap;

    private Long ownerId = 1L;
    private String ownerLastName = "Bhowmik";

    @BeforeEach
    void setUp() {
        //Initialize Owner service
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());

        //save single Owner object
        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(ownerLastName).build());
    }

    //executes 1st
    @Order(1)
    @Test
    void saveWithoutOwnerId() {
        //save a new Owner with no id
        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());

        assertNotNull(savedOwner);                                                      //check if saved owner object is not null
        assertNotNull(savedOwner.getId());                                              //check if saved owner object's Id is not null
        assertEquals(2L, ownerServiceMap.findAll().size());                    //check list size of owner object

    }

    //executes 2nd
    @Order(2)
    @Test
    void saveWithOwnerId() {
        Long expectedOwnerId = 2L;

        //save a new Owner with id = 2L
        Owner savedOwner = ownerServiceMap.save(Owner.builder().id(expectedOwnerId).build());

        //check if saved owner object is not null
        assertNotNull(savedOwner);
        //check if saved owner object's Id is not null
        assertNotNull(savedOwner.getId());

    }

    //executes 3rd
    @Order(3)
    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();

        //check if saved owners list is not null
        assertNotNull(owners);
        //check size of saved owners list
        assertEquals(1L, owners.size());
    }

    @Test
    void findByLastName() {

        //save a new Owner with lastName = "Bhowmik"
        //Owner savedOwner = ownerServiceMap.save(Owner.builder().lastName("Bhowmik").build());

        //check if owner's last-name is not null
        assertNotNull(ownerServiceMap.findByLastName(ownerLastName));

        //check if owner's last-name is equal to 'ownerLastName'
        assertEquals(ownerLastName, ownerServiceMap.findByLastName(ownerLastName).getLastName());

    }



    @Test
    void findById() {

        //expected value
        Long expectedOwnerId = 1L;

        Owner owner = ownerServiceMap.findById(ownerId);

        assertEquals(expectedOwnerId, owner.getId());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
        //check if deleted owner is really deleted or not
        assertEquals(null, ownerServiceMap.findById(ownerId));
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        //check if deleted owner is really deleted or not
        assertEquals(null, ownerServiceMap.findById(ownerId));
    }
}