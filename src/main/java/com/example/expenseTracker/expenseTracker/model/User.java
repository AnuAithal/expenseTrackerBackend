package com.example.expenseTracker.expenseTracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotEmpty
    @Size(min=3,message="Username must be min of 4 characters")
    private String name;
    @Column
    @Email(message="Email address is not valid !!")
    private String email;

    @NotEmpty
    @Size(min=6,max=12,message="Password must be min of 6 character and max of 12 character")
    @Pattern(regexp="^.*(?=.{6,12})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$")
    @Column
    private String password;

    @Column
    private String firebaseId;
//   @OneToMany(targetEntity = Category.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private List<Category> categories;

}
