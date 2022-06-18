package com.example.javamicroservicii.service;

import com.example.javamicroservicii.exceptions.NoAdoptionFound;
import com.example.javamicroservicii.model.Adoption;
import com.example.javamicroservicii.repository.AdoptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdoptionService {
    private final AdoptionRepository adoptionRepository;
    public AdoptionService(AdoptionRepository adoptionRepository) {
        this.adoptionRepository = adoptionRepository;
    }

    public Adoption saveAdoption(Adoption adoption){
        return adoptionRepository.save(adoption);
    }
    public List<Adoption> retrieveAdoptions() {
        return adoptionRepository.findAll();
    }
    public Adoption findByAnimalAndCenter(String animal, String center) {
        Adoption adoption = adoptionRepository.findByAnimalAndCenter(animal, center);
        return adoption;
    }
    public Adoption findId(Long id) {
        Optional<Adoption> adoption = adoptionRepository.findById(id);
        if(! adoption.isPresent()) {
            throw new NoAdoptionFound("Adoption " + id + " not found!");
        }
        return adoption.get();
    }
    public Adoption delete(Long id) {
        Optional<Adoption> adoptionOptional = adoptionRepository.findById(id);
        if(! adoptionOptional.isPresent()) {
            throw new NoAdoptionFound("Adoption " + id + " not found!");
        }
        adoptionRepository.delete(adoptionOptional.get());
        return adoptionOptional.get();
    }
}
