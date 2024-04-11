package com.procesos.parcial.model;

import com.procesos.parcial.model.enums.CategoryEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private CategoryEnum category;
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Book book;
}