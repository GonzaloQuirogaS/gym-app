package com.app.gym_management.persistance.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3)
    private String name;

    @NotNull
    private Integer price;

    @JsonIgnore
    @ManyToMany(mappedBy = "discipline")
    private Set<Client> client;


    public Discipline() {
        this.client = new HashSet<>();
    }

    public Discipline(String name, Integer price) {
        this();
        this.name = name;
        this.price = price;
    }

}
