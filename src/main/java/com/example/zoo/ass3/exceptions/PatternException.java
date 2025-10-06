package com.example.zoo.ass3.exceptions;

public class PatternException extends GeneralException {
    private static final String MESSAGE = "Invalid Pattern";

    public PatternException() {
        super(MESSAGE);
    }
}
