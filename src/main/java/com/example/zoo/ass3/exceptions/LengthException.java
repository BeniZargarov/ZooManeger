package com.example.zoo.ass3.exceptions;

public class LengthException extends GeneralException {
    private static final String MESSAGE = "Invalid length";

    public LengthException() {
        super(MESSAGE);
    }
}
