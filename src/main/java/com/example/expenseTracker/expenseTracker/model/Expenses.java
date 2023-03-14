package com.example.expenseTracker.expenseTracker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="expenses")
public class Expenses {
    @Id
    private Long id;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date expenseDate;
    private String description;
    private String amount;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

}
