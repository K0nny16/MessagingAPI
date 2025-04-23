package com.SecureMessagingAPI.MessagingAPI.Service;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.SecureMessagingAPI.MessagingAPI.DTO.RegisterRequest;
import com.SecureMessagingAPI.MessagingAPI.Entity.AuthEntity;
import com.SecureMessagingAPI.MessagingAPI.Entity.UserEntity;
import com.SecureMessagingAPI.MessagingAPI.Repo.AuthRepository;
import com.SecureMessagingAPI.MessagingAPI.Repo.UserRepository;
import com.SecureMessagingAPI.MessagingAPI.Security.CryptoUtil;

@Service
public class RegisterUserService {

    
    @Autowired
    AuthRepository authRepository;
    
    @Autowired
    UserRepository userRepository;

    public void register(RegisterRequest request)throws IllegalArgumentException, NoSuchAlgorithmException{
        checkIfUserExist(request.getEmail(),request.getDisplayName());
        UserEntity savedUser = createUserEntity(request);
        createAuthEntity(request,savedUser);
    }

    private void checkIfUserExist(String email,String displayName){
        if(authRepository.findByEmail(email) == null){
            if(userRepository.findByDisplayName(displayName) == null){
                return;
            }
            else{
                throw new IllegalArgumentException("Displayname allready exists in table users!");
            }
        }
        else{
            throw new IllegalArgumentException("Email allready exists in table auth!");
        }
    }

    private UserEntity createUserEntity(RegisterRequest request) {
        UserEntity newUser = new UserEntity();
        newUser.setDisplayName(request.getDisplayName());
        return userRepository.save(newUser);
    }

    private void createAuthEntity(RegisterRequest request,UserEntity savedUser)throws NoSuchAlgorithmException{
        AuthEntity newAuth = new AuthEntity();
        newAuth.setEmail(request.getEmail());
        newAuth.setUser(savedUser);

        KeyPair keyPair = CryptoUtil.generateRsaKeyPair();
        
        byte[] encryptedPrivateKey = CryptoUtil.encryptPrivateKey(keyPair.getPrivate(),request.getPassword());
        byte[] publicKey = keyPair.getPublic().getEncoded();
        String passwordHash = hashPassword(request.getPassword());

        newAuth.setEncryptedPrivateKey(encryptedPrivateKey);
        newAuth.setPublicKey(publicKey);
        newAuth.setPasswordHash(passwordHash);

        authRepository.save(newAuth);
    }


    public String hashPassword(String unHashedPassword){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(unHashedPassword);
    }
}
