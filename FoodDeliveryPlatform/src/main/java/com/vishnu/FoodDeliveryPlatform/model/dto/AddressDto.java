package com.vishnu.FoodDeliveryPlatform.model.dto;

import com.vishnu.FoodDeliveryPlatform.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    Address address;
    AuthenticationInputDto authInfo;
}
