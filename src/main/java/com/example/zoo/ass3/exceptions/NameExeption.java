package com.example.zoo.ass3.exceptions;

public class NameExeption extends GeneralException {
    private static final String MESSAGE = "Invalid Name: Cant have a space in the first character and cant be empty";

    public NameExeption() {
        super(MESSAGE);
    }
}
