package com.barnali.petclinic.services.map;

import com.barnali.petclinic.model.Speciality;
import com.barnali.petclinic.model.Vet;
import com.barnali.petclinic.services.SpecialityService;
import com.barnali.petclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private SpecialityService specialityService;

    @Autowired
    public VetServiceMap(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Vet findByLastName(String lastName) {
        return null;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {
        if(object != null) {
            if(object.getSpecialities()!= null){
                object.getSpecialities().forEach(speciality -> {
                    if(speciality.getId() == null){
                        Speciality savedSpeciality= specialityService.save(speciality);
                        speciality.setId(savedSpeciality.getId());
                    }
                });
                return super.save(object);
            }else{
                throw new RuntimeException("Vet doesn't have Specialities!!");
            }
        }
        return null;
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }


}
