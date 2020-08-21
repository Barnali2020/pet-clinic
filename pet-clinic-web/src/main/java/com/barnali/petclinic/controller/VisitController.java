package com.barnali.petclinic.controller;

import com.barnali.petclinic.model.Owner;
import com.barnali.petclinic.model.Pet;
import com.barnali.petclinic.model.Visit;
import com.barnali.petclinic.services.OwnerService;
import com.barnali.petclinic.services.PetService;
import com.barnali.petclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by barnali on Aug, 2020
 */
@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}")
public class VisitController {

    private final PetService petService;
    private final VisitService visitService;

    public VisitController(PetService petService,
                           VisitService visitService) {
        this.petService = petService;
        this.visitService = visitService;
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable("petId") Long petId, Model model) {
        Pet pet = petService.findById(petId);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        model.addAttribute("pet", pet);
        return visit;
    }

    @InitBinder
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/visits/new")
    public String getVisitCreationForm(){
        return "visits/createOrUpdateVisitForm";
    }

    @PostMapping("/visits/new")
    public String submitVisitCreationForm(@Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return "visits/createOrUpdateVisitForm";
        } else {
            visitService.save(visit);
            return "redirect:/owners/" + visit.getPet().getOwner().getId();
        }
    }
}
