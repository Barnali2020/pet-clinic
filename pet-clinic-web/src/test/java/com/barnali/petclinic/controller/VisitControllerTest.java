package com.barnali.petclinic.controller;

import com.barnali.petclinic.model.Owner;
import com.barnali.petclinic.model.Pet;
import com.barnali.petclinic.model.Visit;
import com.barnali.petclinic.services.PetService;
import com.barnali.petclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Created by barnali on Aug, 2020
 */
@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void loadPetWithVisit() {
    }

    @Test
    void initOwnerBinder() {
    }

    @Test
    void getVisitCreationForm() {
    }

    @Test
    void submitVisitCreationForm() throws Exception{
        Pet pet = new Pet();
        pet.setId(1L);

        Visit visit = new Visit();
        visit.setId(1L);
        //visit.setPet(pet);

        when(visitService.save(any())).thenReturn(visit);


        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/pets/1/visits/new")
                .param("date", "2020-08-12")
                .param("description", "some description"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
                //.andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"));

    }
}