package com.vishnu.FoodDeliveryPlatform.repo;

import com.vishnu.FoodDeliveryPlatform.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFoodItemRepo extends JpaRepository<FoodItem,Integer> {
}
