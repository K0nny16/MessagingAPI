package com.SecureMessagingAPI.MessagingAPI.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SecureMessagingAPI.MessagingAPI.DTO.RegisterRequest;
import com.SecureMessagingAPI.MessagingAPI.Service.RegisterUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.security.NoSuchAlgorithmException;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private RegisterUserService registerUserService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request ) {
        try {
            registerUserService.register(request);
            return ResponseEntity.ok("User registerd successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("IllegalArgumentException (wrong encryption type): " +e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.badRequest().body("Cryptographic error: " + e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
}
