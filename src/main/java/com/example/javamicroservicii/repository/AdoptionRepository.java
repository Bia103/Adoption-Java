package com.example.javamicroservicii.repository;

import com.example.javamicroservicii.model.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
    Adoption findByAnimalAndCenter(String animal, String center);
}
