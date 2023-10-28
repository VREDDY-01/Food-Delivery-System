package com.vishnu.FoodDeliveryPlatform.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,scope = Order.class,property = "orderId")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne()
    @JoinColumn(name = "userId")
    User user;

    @OneToMany
    @JoinColumn(name = "foodItemId")
    List<FoodItem> foodItems;

    private Integer quantity;
    private Status status;

    @OneToOne
    private Address address;
}
