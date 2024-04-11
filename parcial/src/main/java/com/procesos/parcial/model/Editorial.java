package com.procesos.parcial.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String city;
    private String country;
    private String phoneNumber;
    @OneToOne(mappedBy = "editorial")
    private Address address;
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Book book;

}