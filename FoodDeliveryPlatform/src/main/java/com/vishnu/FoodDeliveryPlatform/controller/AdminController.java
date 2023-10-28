package com.vishnu.FoodDeliveryPlatform.controller;

import com.vishnu.FoodDeliveryPlatform.model.FoodItem;
import com.vishnu.FoodDeliveryPlatform.model.Order;
import com.vishnu.FoodDeliveryPlatform.model.dto.AdminAuthInputDto;
import com.vishnu.FoodDeliveryPlatform.model.dto.AdminFoodItemDto;
import com.vishnu.FoodDeliveryPlatform.model.dto.AuthenticationInputDto;
import com.vishnu.FoodDeliveryPlatform.service.ItemService;
import com.vishnu.FoodDeliveryPlatform.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Validated
public class AdminController {
    @Autowired
    ItemService itemService;

    @Autowired
    OrderService orderService;

    @GetMapping("orders")
    public List<Order> getAllOrders(@RequestBody AdminAuthInputDto adminAuthInfo)
    {
        return orderService.getAllOrders(adminAuthInfo);
    }

    @GetMapping("items")
    public List<FoodItem> getItems(@RequestBody AdminAuthInputDto adminAuthInfo){
        return itemService.getItems(adminAuthInfo);
    }

    @PutMapping("item/{id}/{itemId}")
    public String updateItem(@RequestBody @Valid AdminFoodItemDto adminFoodItemDto, @PathVariable Integer itemId){
        return itemService.updateItem(adminFoodItemDto.getFoodItem(),adminFoodItemDto.getAdminAuthInfo(),itemId);
    }

    @DeleteMapping("item/{id}/{itemId}")
    public String deleteItem(@RequestBody AdminAuthInputDto adminAuthInfo,@PathVariable Integer id,@PathVariable Integer itemId){
        return itemService.deleteItem(adminAuthInfo,id,itemId);
    }

    @PostMapping("foodItem")
    public String addItem(@RequestBody AdminFoodItemDto adminFoodItemDto)
    {
        return itemService.addItem(adminFoodItemDto.getFoodItem(),adminFoodItemDto.getAdminAuthInfo());
    }

}
