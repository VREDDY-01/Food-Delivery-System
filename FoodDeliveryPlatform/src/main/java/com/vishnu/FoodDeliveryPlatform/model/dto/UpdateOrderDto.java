package com.vishnu.FoodDeliveryPlatform.model.dto;

import com.vishnu.FoodDeliveryPlatform.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderDto {
    AuthenticationInputDto authInfo;
    Order updatedOrder;
}
