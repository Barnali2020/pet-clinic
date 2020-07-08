package com.barnali.petclinic.model;

import java.util.HashSet;
import java.util.Set;

public class Vet extends Person{

    private Set<Speciality> specialities;

    public Set<Speciality> getSpecialities() {
        if(specialities == null)
            specialities = new HashSet<>();
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }
}
