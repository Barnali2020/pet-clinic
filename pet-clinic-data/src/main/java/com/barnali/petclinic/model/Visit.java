package com.barnali.petclinic.model;

import java.time.LocalDate;

public class Visit extends BaseEntity{

    //when visit has happen
    private LocalDate date;
    //which pet has visited
    private Pet pet;
    //description of the visit
    private String description;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
