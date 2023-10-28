package com.vishnu.FoodDeliveryPlatform.service;

import com.vishnu.FoodDeliveryPlatform.model.Address;
import com.vishnu.FoodDeliveryPlatform.model.Admin;
import com.vishnu.FoodDeliveryPlatform.model.Order;
import com.vishnu.FoodDeliveryPlatform.model.User;
import com.vishnu.FoodDeliveryPlatform.model.dto.AdminAuthInputDto;
import com.vishnu.FoodDeliveryPlatform.model.dto.AuthenticationInputDto;
import com.vishnu.FoodDeliveryPlatform.repo.IAddressRepo;
import com.vishnu.FoodDeliveryPlatform.repo.IAdminRepo;
import com.vishnu.FoodDeliveryPlatform.repo.IOrderRepo;
import com.vishnu.FoodDeliveryPlatform.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    IOrderRepo orderRepo;
    @Autowired
    IUserRepo userRepo;

    @Autowired
    IAddressRepo addressRepo;
    @Autowired
    PTokenService pTokenService;
    @Autowired
    IAdminRepo adminRepo;

    public List<Order> getAllOrders(AdminAuthInputDto adminAuthInfo) {
        Admin admin = adminRepo.findByAdminEmail(adminAuthInfo.getEmail());
        boolean flag = admin!=null;
        if (flag){
            return orderRepo.findAll();
        }else {
            return null;
        }
    }

    public String addOrder(AuthenticationInputDto authInfo, Order order) {
        if(pTokenService.authenticate(authInfo)) {

            //find thr patient
            String email = authInfo.getEmail();

            User user = userRepo.findFirstByUserEmail(email);

            order.setUser(user);

            orderRepo.save(order);
            return "created new order";

        }
        else {
            return "Un Authenticated access!!!";
        }
    }


    public String updateOrder(Integer orderId, AuthenticationInputDto authInfo, Order updatedOrder) {
        if(pTokenService.authenticate(authInfo)) {

            Order found = orderRepo.findById(orderId).orElse(null);
            if (found!=null && found.getUser().getUserEmail().equals(authInfo.getEmail())){
                found.setQuantity(updatedOrder.getQuantity());
                found.setStatus(updatedOrder.getStatus());
                orderRepo.save(found);
                return "order updated";
            }
            return "Invalid order Id";

        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public String deleteOrder(Integer orderId, AuthenticationInputDto authInfo) {
        if(pTokenService.authenticate(authInfo)) {
            Order found = orderRepo.findById(orderId).orElse(null);
            if (found!=null && found.getUser().getUserEmail().equals(authInfo.getEmail())){
                orderRepo.deleteById(orderId);
                return "order deleted";
            }
            return "Invalid order Id or Invalid access";
        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public String addAddress(Address address, AuthenticationInputDto authInfo) {
        if(pTokenService.authenticate(authInfo)) {
            addressRepo.save(address);
            return "Address added";
        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public List<Order> getUserOrders(Integer userId, AuthenticationInputDto authInfo) {
        boolean isAuthenticated = pTokenService.authenticate(authInfo);
        if (isAuthenticated){
            User user = userRepo.findFirstByUserEmail(authInfo.getEmail());
            return orderRepo.findByUser(user);
        }else {
            return null;
        }
    }
}
