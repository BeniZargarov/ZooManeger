package com.example.zoo.ass3.exceptions;

public class WeightExeption extends GeneralException {
    private static final String MESSAGE = "Invalid Age: cant be smaller or equal to 0";

    public WeightExeption() {
        super(MESSAGE);
    }
}
