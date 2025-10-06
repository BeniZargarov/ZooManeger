package com.example.zoo.ass3.exceptions;

public class AgeException extends GeneralException {
    private static final String MESSAGE = "Invalid age";

    public AgeException() {
        super(MESSAGE);
    }
}
