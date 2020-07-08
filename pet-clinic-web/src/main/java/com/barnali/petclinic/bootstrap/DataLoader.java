package com.barnali.petclinic.bootstrap;

import com.barnali.petclinic.model.Owner;
import com.barnali.petclinic.model.Pet;
import com.barnali.petclinic.model.PetType;
import com.barnali.petclinic.model.Vet;
import com.barnali.petclinic.services.OwnerService;
import com.barnali.petclinic.services.PetService;
import com.barnali.petclinic.services.PetTypeService;
import com.barnali.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private OwnerService ownerService;
    private VetService vetService;
    private PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService){
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
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

        //======================== Vets ====================
        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessei");
        vet2.setLastName("Porter");

        vetService.save(vet2);
    }
}
