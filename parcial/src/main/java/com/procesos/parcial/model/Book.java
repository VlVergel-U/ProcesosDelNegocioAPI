package com.procesos.parcial.model;

import com.procesos.parcial.model.enums.Language;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
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
    @NotBlank(message = "Please provide the name of the book")
    @Column(nullable = false)
    private String name;

    @Size(min = 50, max = 200, message = "")
    @NotBlank(message = "Please provide the description of the book")
    @Column(nullable = false)
    private String description;

    @Valid
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;

    @Valid
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();

    @NotBlank(message = "Please provide the edition number of the book")
    @Column(nullable = false)
    private String editionNumber;

    @NotNull(message = "Please provide the price of the book")
    @Column(nullable = false)
    private Double price;

    @NotNull(message = "Please provide the publication date of the book")
    @Column(nullable = false)
    private LocalDate publicationDate;

    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;

    @NotNull(message = "Please provide the language of the book")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Language language;


}
