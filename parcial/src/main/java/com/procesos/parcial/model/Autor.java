package com.procesos.parcial.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "autor")
public class Autor {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Book book;

}
