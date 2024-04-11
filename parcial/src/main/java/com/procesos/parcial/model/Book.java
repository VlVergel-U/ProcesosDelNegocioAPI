package com.procesos.parcial.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.procesos.parcial.model.enums.Language;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToOne(mappedBy = "book")
    private Autor autor;

    @OneToOne(mappedBy = "book")
    private Category category;

    private String editionNumber;
    private Double price;
    private LocalDate publicationDate;
    @OneToOne(mappedBy = "book")
    private Editorial editorial;

    @Enumerated(EnumType.STRING)
    private Language language;


}
