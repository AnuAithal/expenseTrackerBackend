package com.example.expenseTracker.expenseTracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="category")
public class Category {

    @Id
    private Long id;
    private String name;

}
