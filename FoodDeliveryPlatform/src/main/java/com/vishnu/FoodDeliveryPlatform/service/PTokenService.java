package com.vishnu.FoodDeliveryPlatform.service;

import com.vishnu.FoodDeliveryPlatform.model.UserAuthToken;
import com.vishnu.FoodDeliveryPlatform.model.dto.AuthenticationInputDto;
import com.vishnu.FoodDeliveryPlatform.repo.IPTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PTokenService {
    @Autowired
    IPTokenRepo ipTokenRepo;

    public void createToken(UserAuthToken token) {
        ipTokenRepo.save(token);
    }

    public void deleteToken(String tokenValue) {

        UserAuthToken token =  ipTokenRepo.findFirstByTokenValue(tokenValue);
        ipTokenRepo.delete(token);

    }

    public boolean authenticate(AuthenticationInputDto authInfo) {

        String email = authInfo.getEmail();
        String tokenValue = authInfo.getTokenValue();

        //find thr actual token -> get the connected patient -> get its email-> verify with passed email

        //return ipTokenRepo.findFirstByTokenValue(tokenValue).getPatient().getPatientEmail().equals(email);

        UserAuthToken token =  ipTokenRepo.findFirstByTokenValue(tokenValue);
        if(token!=null)
        {
            return token.getUser().getUserEmail().equals(email);
        }
        else
        {
            return false;
        }

    }
}
