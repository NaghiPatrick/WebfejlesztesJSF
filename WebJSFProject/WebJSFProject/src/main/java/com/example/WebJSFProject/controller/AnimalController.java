package com.example.WebJSFProject.controller;


import com.example.WebJSFProject.Entity.Animal;
import com.example.WebJSFProject.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Controller
public class AnimalController {

    static final int DEFAULT_PAGE_SIZE = 4;

    private final AnimalService service;

    @Autowired
    public AnimalController(final AnimalService service){
        this.service = service;
    }

    @GetMapping("/animals/")
    public String index() {
        return "redirect:list";
    }

    @GetMapping("/animals/list")
    public String getAnimals(final Model model,
                             @RequestParam(value = "page", defaultValue = "0" )final int pageNumber,
                             @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE + "") final int pageSize) {

       final Page<Animal> page = service.getAnimals(pageNumber, pageSize);

       final int currentPageNumber = page.getNumber();
       final int previousPageNumber = page.hasPrevious() ? currentPageNumber -1 :-1;
       final int nextPageNumber = page.hasNext() ? currentPageNumber +1 : -1;


       model.addAttribute("animals",page.getContent());
       model.addAttribute("previousPageNumber", previousPageNumber);
       model.addAttribute("nextPageNumber", nextPageNumber);
       model.addAttribute("currentPageNumber", currentPageNumber);
       model.addAttribute("pageSize",pageSize);

        return "animals/list";
    }

    @GetMapping("/animals/add")
    public String add(final Model model) {

        model.addAttribute("animal",  new Animal());

        return "animals/add";

    }

    @GetMapping("/animals/view")
    public String view(final Model model,
                       @RequestParam final UUID id) {
        final Optional<Animal> record = service.getAnimal(id);

        model.addAttribute("animal", record.isPresent() ? record.get() : new Animal());
        model.addAttribute("id", id);
        return "animals/view";
    }
    @GetMapping("/animals/edit")
    public String edit(final Model model,
                       @RequestParam final UUID id) {

        final Optional<Animal> record = service.getAnimal(id);

        model.addAttribute("animal", record.isPresent() ? record.get() : new Animal());
        model.addAttribute("id", id);

        return "animals/edit";
    }
    @GetMapping("/animals/delete")
    public String delete(final Model model, @RequestParam final UUID id) {

        final Optional<Animal> record = service.getAnimal(id);

        model.addAttribute("animal", record.isPresent() ? record.get() : new Animal());
        model.addAttribute("id", id);


        return "animals/delete";
    }
    @PostMapping("/animals/save")
    public String save(final Model model, @ModelAttribute final Animal animal, final BindingResult errors){

        service.save(animal);

        return "redirect:list";
    }
    @PostMapping("/animals/delete")
    public String save(final Model model, @RequestParam final UUID id){

        service.delete(id);

        return "redirect:list";
    }
}
