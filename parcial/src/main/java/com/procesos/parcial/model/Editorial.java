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

    @Size(min = 2, max = 40, message = " The name of the editorial must be in 2-40 characters")
    @NotBlank(message = "Please provide the name of the editorial")
    @Column(nullable = false)
    private String name;

    @Size(min = 2, max = 40, message = " The name of the city of the editorial must be in 2-40 characters")
    @NotBlank(message = "Please provide the city of the editorial")
    @Column(nullable = false)
    private String city;

    @Size(min = 2, max = 40, message = " The name of the country of the editorial must be in 2-40 characters")
    @NotBlank(message = "Please provide the country of the editorial")
    @Column(nullable = false)
    private String country;

    @Size(min = 10, max = 10, message = " The phone number of the editorial must be in 10 characters")
    @NotBlank(message = "Please provide the phone number of the editorial")
    @Column(nullable = false)
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

}