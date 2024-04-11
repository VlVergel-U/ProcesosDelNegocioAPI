package com.procesos.parcial.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String avenue;
    private String postalCode;
    private String neighborhood;
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Editorial editorial;


}
