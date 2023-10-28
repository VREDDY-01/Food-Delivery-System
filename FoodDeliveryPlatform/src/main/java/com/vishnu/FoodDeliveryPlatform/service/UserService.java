package com.vishnu.FoodDeliveryPlatform.service;

import com.vishnu.FoodDeliveryPlatform.model.User;
import com.vishnu.FoodDeliveryPlatform.model.UserAuthToken;
import com.vishnu.FoodDeliveryPlatform.model.dto.AuthenticationInputDto;
import com.vishnu.FoodDeliveryPlatform.model.dto.SignInInputDto;
import com.vishnu.FoodDeliveryPlatform.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;

    @Autowired
    PTokenService pTokenService;

    public String userSignUp(User user) {
        //check if already exist -> Not allowed : try logging in

        String newEmail = user.getUserEmail();

        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser != null)
        {
            return "email already in use";
        }

        // passwords are encrypted before we store it in the table

        String signUpPassword = user.getUserPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(signUpPassword);

            user.setUserPassword(encryptedPassword);
            user.setOrders(null);



            // patient table - save patient
            userRepo.save(user);
            return "user registered";

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }
    }

    public String userSignIn(SignInInputDto signInInput) {
        String email = signInInput.getEmail();

        User existingUser = userRepo.findFirstByUserEmail(email);

        if(existingUser == null)
        {
            return "Not a valid email, Please sign up first !!!";
        }

        //password should be matched

        String password = signInInput.getPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if(existingUser.getUserPassword().equals(encryptedPassword))
            {
                // return a token for this sign in
                UserAuthToken token  = new UserAuthToken(existingUser);

                if(EmailService.sendEmail(email,"otp after login", token.getTokenValue())) {
                    pTokenService.createToken(token);
                    return "check email for otp/token!!!";
                }
                else {
                    return "error while generating token!!!";
                }

            }
            else {
                //password was wrong!!!
                return "Invalid Credentials!!!";
            }

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }
    }

    public String userSignOut(AuthenticationInputDto authInfo) {
        if(pTokenService.authenticate(authInfo)) {
            String tokenValue = authInfo.getTokenValue();
            pTokenService.deleteToken(tokenValue);
            return "Sign Out successful!!";
        }
        else {
            return "Un Authenticated access!!!";
        }
    }
}
