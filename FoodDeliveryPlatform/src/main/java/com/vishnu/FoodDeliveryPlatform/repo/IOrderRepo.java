package com.vishnu.FoodDeliveryPlatform.repo;

import com.vishnu.FoodDeliveryPlatform.model.Order;
import com.vishnu.FoodDeliveryPlatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderRepo extends JpaRepository<Order,Integer> {

    List<Order> findByUser(User user);
}
