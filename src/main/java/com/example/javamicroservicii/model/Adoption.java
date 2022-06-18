package com.example.javamicroservicii.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "Adoption")
public class Adoption extends RepresentationModel<Adoption> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "animal")
    @Size(max=20, message = "Maximum of 20 characters")
    private String animal;

    @Column(name = "center")
    private String center;

    public Adoption() {

    }

    @Column(name = "price")
    private int price;

    public Adoption(String animal, String center, int price) {
        this.animal = animal;
        this.center = center;
        this.price = price;
    }
}
