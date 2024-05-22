package com.procesos.parcial.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 20, message = "")
    @NotBlank(message = "Please provide the first name")
    @Column(nullable = false)
    private String firstName;

    @Size(min = 2, max = 20, message = "")
    @Column()
    private String secondName;

    @Size(min = 2, max = 20, message = "")
    @NotBlank(message = "Please provide the first last name")
    @Column(nullable = false)
    private String firstLastName;

    @Size(min = 2, max = 20, message = "")
    @NotBlank(message = "Please provide the second last name")
    @Column(nullable = false)
    private String secondLastName;


}
