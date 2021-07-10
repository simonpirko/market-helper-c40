package com.example.markethelperc40.util;


import org.apache.commons.lang3.RandomStringUtils;

public class RandomString {
    public static String generate(int length){
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
