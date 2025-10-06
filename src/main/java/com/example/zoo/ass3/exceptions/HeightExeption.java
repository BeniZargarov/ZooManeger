package com.example.zoo.ass3.exceptions;

public class HeightExeption extends GeneralException {
    private static final String MESSAGE = "Error:";

    public HeightExeption(String text) {
        super(MESSAGE + text);
    }
}
