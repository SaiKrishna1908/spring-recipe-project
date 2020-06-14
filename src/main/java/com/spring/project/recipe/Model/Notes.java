package com.spring.project.recipe.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude = "recipe")
public class Notes {

    //lob is used when a large object is expected
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Lob
    private String notes;

    @OneToOne
    private Recipe recipe;
}
