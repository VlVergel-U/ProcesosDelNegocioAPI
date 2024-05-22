package com.procesos.parcial.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.procesos.parcial.model.enums.Language;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 10, max = 100, message = "")
    @NotBlank(message = "Please provide the name")
    @Column(nullable = false)
    private String name;

    @Size(min = 50, max = 200, message = "")
    @NotBlank(message = "Please provide the description")
    @Column(nullable = false)
    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Autor> authors = new HashSet<>();

    @NotBlank(message = "Please provide the edition number")
    @Column(nullable = false)
    private String editionNumber;

    @NotNull(message = "Please provide the price")
    @Column(nullable = false)
    private Double price;

    @NotNull(message = "Please provide the publication date")
    @Column(nullable = false)
    private LocalDate publicationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;

    @NotNull(message = "Please provide the language")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Language language;


}
