package com.hc.bean;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnSafeContainer {

    private static Unsafe unsafe;

    static{
        Class<?> unsafeClass = Unsafe.class;
        for (Field f : unsafeClass.getDeclaredFields()) {
            if ("theUnsafe".equals(f.getName())) {
                f.setAccessible(true);
                try {
                    unsafe =  (Unsafe) f.get(null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private UnSafeContainer(){}

    public static Unsafe getUnsafe() {
        return unsafe;
    }

}
