package com.codehive.utils;

public interface HashingService {
    String hash(String data);

    boolean verify(String data, String hash);
}
