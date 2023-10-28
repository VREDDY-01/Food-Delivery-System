package com.vishnu.FoodDeliveryPlatform.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PAuthentication")
public class UserAuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationTime;

    public UserAuthToken(User user) {
        this.user = user;
        this.tokenCreationTime = LocalDateTime.now();
        this.tokenValue = UUID.randomUUID().toString();
    }

    @OneToOne
    @JoinColumn(name = "fk_user_id")
    User user;
}
