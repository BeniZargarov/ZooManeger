package com.example.zoo.ass3.exceptions;

public class PenguinHeightException extends GeneralException {
    private static final String MESSAGE = "Height is taller then leader";

    public PenguinHeightException() {
        super(MESSAGE);
    }
}
