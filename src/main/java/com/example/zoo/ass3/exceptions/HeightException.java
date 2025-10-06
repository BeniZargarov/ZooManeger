package com.example.zoo.ass3.exceptions;

public class HeightException extends GeneralException {
    private static final String MESSAGE = "Invalid height";

    public HeightException() {
        super(MESSAGE);
    }
}
