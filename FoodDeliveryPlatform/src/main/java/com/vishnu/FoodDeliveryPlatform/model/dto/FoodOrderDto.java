package com.vishnu.FoodDeliveryPlatform.model.dto;

import com.vishnu.FoodDeliveryPlatform.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodOrderDto {
    AuthenticationInputDto authInfo;
    Order order;
}
