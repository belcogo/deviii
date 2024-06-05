package com.unisinos.library.model;

public enum BorrowStatus {
    PENDING, ACCEPTED, REJECTED, WAITING_TO_RETURN, RETURNED;

    public static boolean canCovert(String value) {
        try {
            BorrowStatus.valueOf(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
