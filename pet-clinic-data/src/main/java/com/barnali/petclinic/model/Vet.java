package com.barnali.petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "vets")
public class Vet extends Person{

    /** This call is subclass of Person so need to provide explicit constructor **/
    //@NoArgsConstructor and @AllArgsConstructor allows lombok to create Builder patter
    @Builder
    public Vet(Long id, String firstName, String lastName, Set<Speciality> specialities){
        super(id, firstName, lastName);
        this.specialities = specialities;
    }

    @ManyToMany(fetch = FetchType.EAGER)        //By-default @ManyToMany is LAZY fetch type, so setting EAGER fetch explicitly
    @JoinTable(name = "vet_specialities", joinColumns = @JoinColumn(name = "vet_id"), inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Speciality> specialities = new HashSet<>();

}
