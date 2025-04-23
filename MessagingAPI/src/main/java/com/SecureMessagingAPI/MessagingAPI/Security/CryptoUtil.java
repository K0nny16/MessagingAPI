package com.SecureMessagingAPI.MessagingAPI.Security;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {
    
    public static KeyPair generateRsaKeyPair() throws NoSuchAlgorithmException{
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(4096);
        return keyGen.generateKeyPair();
    }

    public static byte[] encryptPrivateKey(PrivateKey privateKey,String password) {
        SecureRandom secureRandom = new SecureRandom();
        try {
            //Slumpar salt.
            byte[] salt = new byte[16];
            secureRandom.nextBytes(salt);

            //Skapar en AES-nyckel (secret key) från lösenordet + salt.
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(),salt,65536,256);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            SecretKey temp = factory.generateSecret(spec);
            SecretKeySpec aesKey = new SecretKeySpec(temp.getEncoded(),"AES");

            //Slumpar IV (Initialiseringsvektor)
            byte[] iv = new byte[16];
            secureRandom.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            //Krypterar privateKey.
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey,ivSpec);
            byte[] encryptedPrivateKey = cipher.doFinal(privateKey.getEncoded());

            //Slår ihopa salt + iv + encryptedPrivateKey.
            ByteBuffer buffer = ByteBuffer.allocate(salt.length+iv.length+encryptedPrivateKey.length);
            buffer.put(salt);
            buffer.put(iv);
            buffer.put(encryptedPrivateKey);
            
            return buffer.array();

        }catch(GeneralSecurityException e){
            throw new RuntimeException("Failed to encrypt privateKey", e);
        }
    }
}
