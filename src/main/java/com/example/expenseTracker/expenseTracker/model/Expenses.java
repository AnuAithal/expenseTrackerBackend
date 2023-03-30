package com.example.expenseTracker.expenseTracker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="expenses")
public class Expenses {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private Long id;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
    @Column
    private LocalDateTime expenseDate;
    @Column
    private String description;
    @Column
    private String amount;

    @ManyToOne
//    @JoinColumn(name="category_id",referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;

}
