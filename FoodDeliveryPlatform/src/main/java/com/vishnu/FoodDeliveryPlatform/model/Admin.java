package com.vishnu.FoodDeliveryPlatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AdminUser")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;
    private String adminName;

    @Pattern(regexp = ".*@restaurant\\.admin\\.in$")
    private String adminEmail;
    private String adminPassword;
}
