package com.procesos.parcial.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Size;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "editorial")
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 2, max = 40, message = "")
    @NotBlank(message = "Please provide the name")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Please provide the city")
    @Column(nullable = false)
    private String city;

    @NotBlank(message = "Please provide the country")
    @Column(nullable = false)
    private String country;

    @NotBlank(message = "Please provide the phone number")
    @Column(nullable = false)
    private String phoneNumber;

    @OneToOne()
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "editorial")
    private List<Book> books;
}