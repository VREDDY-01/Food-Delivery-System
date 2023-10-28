package com.vishnu.FoodDeliveryPlatform.repo;

import com.vishnu.FoodDeliveryPlatform.model.Address;
import com.vishnu.FoodDeliveryPlatform.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepo extends JpaRepository<Address,Integer> {

}
