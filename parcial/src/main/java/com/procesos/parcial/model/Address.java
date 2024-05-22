package com.procesos.parcial.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please provide the street of the editorial")
    @Column(nullable = false)
    private String street;

    @NotBlank(message = "Please provide the avenue of the editorial")
    @Column(nullable = false)
    private String avenue;

    @NotBlank(message = "Please provide the postal code of the editorial")
    @Column(nullable = false)
    private String postalCode;

    @NotBlank(message = "Please provide the neighborhood of the editorial")
    @Column(nullable = false)
    private String neighborhood;

}
