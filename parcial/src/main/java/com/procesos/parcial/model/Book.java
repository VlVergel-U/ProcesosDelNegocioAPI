package com.procesos.parcial.model;

import com.procesos.parcial.model.enums.Language;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    private Autor autor;

    @ManyToOne
    private Category category;

    private String editionNumber;
    private Double price;
    private LocalDate publicationDate;

    @ManyToMany
    private List<Editorial> editorials;

    private Language language;


}
