package com.procesos.parcial.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uniqueCode;

    @Size(min = 2, max = 20, message = "")
    @NotBlank(message = "Please provide the first name of the author")
    @Column(nullable = false)
    private String firstName;

    @Size(min = 2, max = 20, message = "")
    @Column()
    private String secondName;

    @Size(min = 2, max = 20, message = "")
    @NotBlank(message = "Please provide the first last name of the author")
    @Column(nullable = false)
    private String firstLastName;

    @Size(min = 2, max = 20, message = "")
    @NotBlank(message = "Please provide the second last name of the author")
    @Column(nullable = false)
    private String secondLastName;

    @NotNull(message = "Please provide the birthdate of the author")
    @Column(nullable = false)
    private Date birthDate;

    private String generateUniqueCode() {
        String code = firstName.substring(0, 1) + firstLastName.substring(0, 1) +
                secondLastName.substring(0, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
        String birthDateCode = sdf.format(birthDate);
        return (code + birthDateCode).toUpperCase();
    }

    @PrePersist
    public void beforeInsert() {
        this.uniqueCode = generateUniqueCode();
    }
}
