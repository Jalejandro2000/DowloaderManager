package com.example.dowloadermanager.util;

public class Utils {
    public static <T> T coalesce(T one, T two)
    {
        return one != null ? one : two;
    }
}
