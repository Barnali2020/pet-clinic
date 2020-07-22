package com.barnali.petclinic.services.map;

import com.barnali.petclinic.model.Owner;
import com.barnali.petclinic.model.Pet;
import com.barnali.petclinic.model.PetType;
import com.barnali.petclinic.services.OwnerService;
import com.barnali.petclinic.services.PetService;
import com.barnali.petclinic.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private PetTypeService petTypeService;
    private PetService petService;

    @Autowired
    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Owner findByLastName(String lastName) {
        Optional<Owner> ownerOptional = findAll().stream()
                .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName)).findFirst();
        if(!ownerOptional.isPresent())
            return null;

        return ownerOptional.get();
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        if(object != null) {
            if(object.getPets() != null){
                object.getPets().forEach(pet -> {
                    if(pet.getPetType().getId() == null){
                        PetType savedPetType = petTypeService.save(pet.getPetType());
                        pet.setPetType(savedPetType);
                    }

                    if(pet.getId() == null){
                        Pet savedPet= petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
                return super.save(object);
            }else{
                throw new RuntimeException("Owner doesn't have Pets!!");
            }
        }
        return null;
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
