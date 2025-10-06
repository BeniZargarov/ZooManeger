package com.example.zoo.ass3.exceptions;

public class AgeExeption extends GeneralException {
  private static final String MESSAGE = "Invalid Age: ";

  public AgeExeption(String text) {
    super(MESSAGE + text);
  }
}
