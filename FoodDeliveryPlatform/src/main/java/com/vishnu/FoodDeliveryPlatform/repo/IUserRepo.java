package com.vishnu.FoodDeliveryPlatform.repo;

import com.vishnu.FoodDeliveryPlatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Integer> {
    User findFirstByUserEmail(String newEmail);
}
