package com.vishnu.FoodDeliveryPlatform.model.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminAuthInputDto {
    @Email
    private String email;

    private String password;
}
