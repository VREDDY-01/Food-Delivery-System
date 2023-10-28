package com.vishnu.FoodDeliveryPlatform.service;

import com.vishnu.FoodDeliveryPlatform.model.Admin;
import com.vishnu.FoodDeliveryPlatform.model.FoodItem;
import com.vishnu.FoodDeliveryPlatform.model.dto.AdminAuthInputDto;
import com.vishnu.FoodDeliveryPlatform.repo.IAdminRepo;
import com.vishnu.FoodDeliveryPlatform.repo.IFoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    IFoodItemRepo foodItemRepo;
    @Autowired
    IAdminRepo adminRepo;

    public String addItem(FoodItem newFoodItem, AdminAuthInputDto adminAuthInfo) {
        Admin admin = adminRepo.findByAdminEmail(adminAuthInfo.getEmail());
        if (admin!=null){
            Integer itemId = newFoodItem.getItemId();

            if(itemId!=null && foodItemRepo.existsById(itemId))
            {
                return "item already exists!!!";
            }

            foodItemRepo.save(newFoodItem);

            return "item added!!!";
        }else {
            return "access denied";
        }
    }

    public List<FoodItem> getItems(AdminAuthInputDto adminAuthInfo) {
        Admin admin = adminRepo.findByAdminEmail(adminAuthInfo.getEmail());
        boolean flag = admin!=null;
        if (flag){
            return foodItemRepo.findAll();
        }else {
            return null;
        }

    }

    public String updateItem(FoodItem item, AdminAuthInputDto adminAuthInfo, Integer itemId) {
        Admin admin = adminRepo.findByAdminEmail(adminAuthInfo.getEmail());
        if (admin!=null){
            FoodItem found = foodItemRepo.findById(itemId).orElse(null);
            if (found!=null) {
                found.setItemPrice(item.getItemPrice());
                found.setItemName(item.getItemName());
                found.setRestaurantName(item.getRestaurantName());
                foodItemRepo.save(found);
                return "item has been updated";
            }
            return "item not found or Invalid Id";
        }else {
            return "access denied";
        }

    }

    public String deleteItem(AdminAuthInputDto adminAuthInfo, Integer id, Integer itemId) {
        Admin admin = adminRepo.findByAdminEmail(adminAuthInfo.getEmail());
        if (admin!=null){
            foodItemRepo.deleteById(id);
            return "item deleted";
        }else {
            return "access denied";
        }
    }
}
