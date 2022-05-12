package com.ra.model.enums;

import java.util.NoSuchElementException;

public enum OrderState {
    IN_WORK, WAIT_FOR_PAYMENT, PAID, CANCELED, COMPLETED;

    public int toInt() {
        return switch (this) {
            case IN_WORK -> 1;
            case WAIT_FOR_PAYMENT -> 2;
            case PAID -> 3;
            case CANCELED -> 4;
            case COMPLETED -> 5;
        };
    }

    public static OrderState fromInt(int value) {
        return switch (value) {
            case 1 -> IN_WORK;
            case 2 -> WAIT_FOR_PAYMENT;
            case 3 -> PAID;
            case 4 -> CANCELED;
            case 5 -> COMPLETED;
            default -> throw new NoSuchElementException("No state for given int value : " + value);
        };
    }
}