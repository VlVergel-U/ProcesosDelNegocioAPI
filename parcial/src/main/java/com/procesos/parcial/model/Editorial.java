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
    @NotBlank(message = "Please provide the name of the editorial")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Please provide the city of the editorial")
    @Column(nullable = false)
    private String city;

    @NotBlank(message = "Please provide the country of the editorial")
    @Column(nullable = false)
    private String country;

    @NotBlank(message = "Please provide the phone number of the editorial")
    @Column(nullable = false)
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

}