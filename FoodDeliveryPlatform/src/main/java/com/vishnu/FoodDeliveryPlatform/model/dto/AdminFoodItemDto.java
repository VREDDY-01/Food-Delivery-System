package com.vishnu.FoodDeliveryPlatform.model.dto;

import com.vishnu.FoodDeliveryPlatform.model.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminFoodItemDto {
    AdminAuthInputDto adminAuthInfo;
    FoodItem foodItem;
}
