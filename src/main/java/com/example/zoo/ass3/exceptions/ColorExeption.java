package com.example.zoo.ass3.exceptions;

public class ColorExeption extends GeneralException {
    private static final String MESSAGE = "Invalid Color/s";

    public ColorExeption() {
        super(MESSAGE);
    }
}
