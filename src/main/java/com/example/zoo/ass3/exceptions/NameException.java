package com.example.zoo.ass3.exceptions;

public class NameException extends GeneralException {
    private static final String MESSAGE = "Empty name";

    public NameException() {
        super(MESSAGE);
    }
}
