package com.example.zoo.ass2.exceptions;

public class LengthExeption extends RuntimeException {
  private static final String MESSAGE = "Invalid Length: can only be 0-50";

  public LengthExeption() {
    super(MESSAGE);
  }
}
