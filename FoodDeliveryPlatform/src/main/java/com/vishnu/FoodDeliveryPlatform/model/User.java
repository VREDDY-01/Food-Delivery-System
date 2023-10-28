package com.vishnu.FoodDeliveryPlatform.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customers_Users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,scope = User.class,property = "userId")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;

    @Email
    private String userEmail;


    private String userPassword;

    @Enumerated(value = EnumType.STRING)
    private Gender userGender;


    @Size(min = 10,max = 10)
    @Pattern(regexp = "\\d+")
    private String userContact;

    @OneToMany(mappedBy = "user")
    List<Order> orders;

}
