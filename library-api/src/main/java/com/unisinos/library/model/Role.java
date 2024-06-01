package com.unisinos.library.model;

public enum Role {
    ROLE_USER,
    ROLE_ADMIN;

    public static boolean canCovert(String value) {
        try {
            Role.valueOf(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}