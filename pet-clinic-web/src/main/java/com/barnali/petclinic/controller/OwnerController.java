package com.barnali.petclinic.controller;

import com.barnali.petclinic.model.Owner;
import com.barnali.petclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/owners/find")
    public String getFindOwnersForm(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/owners/{ownerId}")
    public ModelAndView displayOwnerDetails(@PathVariable String ownerId){
        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
        modelAndView.addObject("owner", ownerService.findById(Long.valueOf(ownerId)));
        return modelAndView;
    }

    @GetMapping("/owners/find/result")
    public String processFindForm(Owner owner, BindingResult result, Model model){
        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        List<Owner> foundOwnerList = ownerService.findAllByLastNameLike("%"+ owner.getLastName() + "%");

        if (foundOwnerList.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "Owner with last name not found");
            return "owners/findOwners";
        } else if (foundOwnerList.size() == 1) {
            // 1 owner found
            owner = foundOwnerList.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            // multiple owners found
            model.addAttribute("selections", foundOwnerList);
            return "owners/ownersList";
        }
    }

    @GetMapping("/owners/new")
    public String getOwnerCreationForm(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/owners/new")
    public String createOwner(@Valid Owner owner, BindingResult result, Model model){

        if(result.hasErrors()){
            return "owners/createOrUpdateOwnerForm";
        }

        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/"+savedOwner.getId();
    }

    @GetMapping("/owners/{ownerId}/edit")
    public String getOwnerUpdateForm(@PathVariable String ownerId, Model model){
        model.addAttribute("owner", ownerService.findById(Long.valueOf(ownerId)));
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/owners/{ownerId}/edit")
    public String updateOwner(@Valid Owner owner, BindingResult result, @PathVariable String ownerId){

        if(result.hasErrors()){
            return "owners/createOrUpdateOwnerForm";
        }

        owner.setId(Long.valueOf(ownerId));         //id is needed to set because we are using @InitBinder
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/"+savedOwner.getId();
    }
}
