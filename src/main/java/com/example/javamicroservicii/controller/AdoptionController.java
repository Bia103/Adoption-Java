package com.example.javamicroservicii.controller;

import com.example.javamicroservicii.config.PropertiesConfig;
import com.example.javamicroservicii.model.Adoption;
import com.example.javamicroservicii.model.Discount;
import com.example.javamicroservicii.service.AdoptionService;
import com.example.javamicroservicii.service.client.DiscountServiceProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@Slf4j
@RequestMapping("/adoption")
public class AdoptionController {
    @Autowired
    private final AdoptionService adoptionService;
    @Autowired
    private PropertiesConfig configuration;
    @Autowired
    DiscountServiceProxy discountServiceProxy;

    @GetMapping("/newFromConfig")
    public Adoption getAdoption() {
        return new Adoption(configuration.getAnimal(), configuration.getCenter(), configuration.getPrice());
    }

    public AdoptionController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }

    @PostMapping("/new")
    public ResponseEntity<Adoption> saveClub(@RequestBody Adoption adoption) {
        return ResponseEntity.ok().body(adoptionService.saveAdoption(adoption));
    }

    @GetMapping("/list")
    public CollectionModel<Adoption> retrieveAdoptions(){
        List<Adoption> adoptions = adoptionService.retrieveAdoptions();
        for(final Adoption adoption : adoptions) {
            Link selfLink = linkTo(methodOn(AdoptionController.class).getAdoption(adoption.getId())).withSelfRel();
            adoption.add(selfLink);
            Link deleteLink = linkTo(methodOn(AdoptionController.class).deleteAdoption(adoption.getId())).withRel("deleteAdoption");
            adoption.add(deleteLink);
        }
        Link link = linkTo(methodOn(AdoptionController.class).retrieveAdoptions()).withSelfRel();
        CollectionModel<Adoption> result = CollectionModel.of(adoptions, link);
        return result;
    }

    @GetMapping("/data/{animal}/{center}")
    public Adoption findByAnimalAndCenter(@PathVariable String animal, @PathVariable String center){
        Adoption adoption = adoptionService.findByAnimalAndCenter(animal, center);

        Link selfLink = linkTo(methodOn(AdoptionController.class).getAdoption(adoption.getId())).withSelfRel();
        adoption.add(selfLink);
        return adoption;
    }

    @GetMapping("/{adoptionId}")
    public Adoption getAdoption(@PathVariable Long adoptionId){
        Adoption adoption = adoptionService.findId(adoptionId);

        Discount discount = discountServiceProxy.findDiscount();
        log.info(discount.getVersion());
        adoption.setPrice(adoption.getPrice() - discount.getPrice());

        return adoption;
    }

    @DeleteMapping("/delete/{id}")
    public Adoption deleteAdoption(@PathVariable Long id){
       Adoption adoption = adoptionService.delete(id);
       return adoption;
    }
}
