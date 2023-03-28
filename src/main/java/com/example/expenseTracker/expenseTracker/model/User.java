package com.example.expenseTracker.expenseTracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String password;

//   @OneToMany(targetEntity = Category.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private List<Category> categories;

}
