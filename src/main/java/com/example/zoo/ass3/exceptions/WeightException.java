package com.example.zoo.ass3.exceptions;

public class WeightException extends GeneralException {
    private static final String MESSAGE = "Invalid weight";

    public WeightException() {
        super(MESSAGE);
    }
}
