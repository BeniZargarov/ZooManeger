package com.example.zoo.ass3.exceptions;

public class ColorNotExistException extends GeneralException {
    private static final String MESSAGE = "Color not exist..";

    public ColorNotExistException() {
        super(MESSAGE);
    }
}
