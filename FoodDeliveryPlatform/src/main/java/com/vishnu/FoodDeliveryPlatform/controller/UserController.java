package com.vishnu.FoodDeliveryPlatform.controller;

import com.vishnu.FoodDeliveryPlatform.model.Address;
import com.vishnu.FoodDeliveryPlatform.model.Order;
import com.vishnu.FoodDeliveryPlatform.model.User;
import com.vishnu.FoodDeliveryPlatform.model.dto.*;
import com.vishnu.FoodDeliveryPlatform.service.OrderService;
import com.vishnu.FoodDeliveryPlatform.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @PostMapping("user/signup")
    public String userSignUp(@Valid @RequestBody User user)
    {
        return userService.userSignUp(user);
    }

    @PostMapping("user/signIn")
    public String userSignIn(@RequestBody SignInInputDto signInInput)
    {
        return userService.userSignIn(signInInput);
    }

    @DeleteMapping("user/signOut")
    public String userSignOut(@RequestBody AuthenticationInputDto authInfo)
    {
        return userService.userSignOut(authInfo);
    }

    @PostMapping("user/order")
    public String addOrder(@RequestBody FoodOrderDto foodOrderDto){
        return orderService.addOrder(foodOrderDto.getAuthInfo(),foodOrderDto.getOrder());
    }
    @GetMapping("order/{userId}")
    public List<Order> getUserOrders(@PathVariable Integer userId,@RequestBody AuthenticationInputDto authInfo){
        return orderService.getUserOrders(userId,authInfo);
    }
    @PutMapping("order/{userId}/{orderId}")
    public String updateOrder(@PathVariable Integer userId,@PathVariable Integer orderId,@RequestBody @Valid UpdateOrderDto updatedOrder){
        return orderService.updateOrder(orderId,updatedOrder.getAuthInfo(),updatedOrder.getUpdatedOrder());
    }
    @DeleteMapping("order/{userId}/{orderId}")
    public String deleteOrder(@PathVariable Integer userId,@PathVariable Integer orderId,@RequestBody AuthenticationInputDto authInfo){
        return orderService.deleteOrder(orderId,authInfo);
    }

    @PostMapping("user/address")
    public String addAddress(@RequestBody AddressDto addressDto){
        return orderService.addAddress(addressDto.getAddress(),addressDto.getAuthInfo());
    }

}
