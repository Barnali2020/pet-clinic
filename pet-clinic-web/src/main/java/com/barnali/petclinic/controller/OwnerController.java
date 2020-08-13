package com.barnali.petclinic.controller;

import com.barnali.petclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping("/find")
    public String findOwners(Model model){
        return "notimplemented";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView displayOwnerDetails(@PathVariable String ownerId){
        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
        modelAndView.addObject("owner", ownerService.findById(Long.valueOf(ownerId)));
        return modelAndView;
    }
}
