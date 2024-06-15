package com.procesos.parcial.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Please provide the street of the address")
    @Size(min = 2, max = 100, message = "The street must be between 2 and 100 characters")
    @Column(nullable = false)
    private String street;

    @NotBlank(message = "Please provide the avenue of the address")
    @Size(min = 2, max = 100, message = "The avenue must be between 2 and 100 characters")
    @Column(nullable = false)
    private String avenue;

    @NotBlank(message = "Please provide the postal code of the address")
    @Size(min = 5, max = 10, message = "The postal code must be between 5 and 10 characters")
    @Pattern(regexp = "^[0-9]+$", message = "The postal code must be a number")
    @Column(nullable = false)
    private String postalCode;

    @NotBlank(message = "Please provide the neighborhood of the address")
    @Size(min = 2, max = 100, message = "The neighborhood must be between 2 and 100 characters")
    @Column(nullable = false)
    private String neighborhood;

}
