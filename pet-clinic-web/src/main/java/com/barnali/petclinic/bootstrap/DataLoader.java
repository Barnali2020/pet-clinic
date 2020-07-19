package com.barnali.petclinic.bootstrap;

import com.barnali.petclinic.model.*;
import com.barnali.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private OwnerService ownerService;
    private VetService vetService;
    private PetTypeService petTypeService;
    private SpecialityService specialityService;
    private VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService, VisitService visitService){
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        //======================= Pet Types====================
        PetType dogPetType = new PetType();
        dogPetType.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dogPetType);

        PetType catPetType = new PetType();
        catPetType.setName("Cat");
        PetType savedCatPetType = petTypeService.save(catPetType);

        //========================== Owners and Pets====================
        Owner owner1 = new Owner();
        owner1.setFirstName("Michel");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("123456123");

        Pet mikePet = new Pet();
        mikePet.setName("Mike");
        mikePet.setPetType(savedDogPetType);
        mikePet.setOwner(owner1);
        owner1.getPets().add(mikePet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glananne");
        owner2.setAddress("22 T Nagar");
        owner2.setCity("Delhi");
        owner2.setTelephone("3446787879");

        Pet roscoPet = new Pet();
        roscoPet.setName("Rosco");
        roscoPet.setPetType(savedDogPetType);
        roscoPet.setBirthDate(LocalDate.now());
        roscoPet.setOwner(owner2);
        owner2.getPets().add(roscoPet);

        Pet juliPet = new Pet();
        juliPet.setName("Juli");
        juliPet.setPetType(savedCatPetType);
        juliPet.setBirthDate(LocalDate.now());
        juliPet.setOwner(owner2);
        owner2.getPets().add(juliPet);

        ownerService.save(owner2);

        //======================== Visit ====================

        Visit catVisit = new Visit();
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");
        catVisit.setPet(juliPet);

        visitService.save(catVisit);


        //======================== Vets and Speciality ====================

        Speciality radiologySpeciality = new Speciality();
        radiologySpeciality.setDescrition("Radiology");

        Speciality surgerySpeciality = new Speciality();
        surgerySpeciality.setDescrition("Surgery");

        Speciality dentistrySpeciality = new Speciality();
        dentistrySpeciality.setDescrition("Dentistry");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(radiologySpeciality);
        vet1.getSpecialities().add(surgerySpeciality);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessei");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(dentistrySpeciality);

        vetService.save(vet2);
    }
}
