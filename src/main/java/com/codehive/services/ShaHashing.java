package com.codehive.services;

import com.codehive.utils.HashingService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class ShaHashing implements HashingService {
    public String hash(String data) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] hashedBytes = md.digest(data.getBytes(StandardCharsets.UTF_8));

            // Combine salt and hash
            byte[] result = new byte[salt.length + hashedBytes.length];
            System.arraycopy(salt, 0, result, 0, salt.length);
            System.arraycopy(hashedBytes, 0, result, salt.length, hashedBytes.length);

            return Base64.getEncoder().encodeToString(result);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No such algorithm: " + e.getMessage());
            return null;
        }
    }
}
