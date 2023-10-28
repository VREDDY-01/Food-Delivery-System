package com.vishnu.FoodDeliveryPlatform.repo;

import com.vishnu.FoodDeliveryPlatform.model.UserAuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPTokenRepo extends JpaRepository<UserAuthToken,Integer> {
    UserAuthToken findFirstByTokenValue(String tokenValue);
}
