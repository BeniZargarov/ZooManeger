package com.example.zoo.ass3.exceptions;

public class ColorExistException extends GeneralException {
    private static final String MESSAGE = "Color already exist..";

    public ColorExistException() {
        super(MESSAGE);
    }
}
