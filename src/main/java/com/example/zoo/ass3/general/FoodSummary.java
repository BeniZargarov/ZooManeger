package com.example.zoo.ass3.general;

import java.io.Serializable;

public class FoodSummary implements Serializable {
    private String animalType;
    private double amount;
    private String unit;

    public FoodSummary(String animalType, double amount, String unit) {
        this.animalType = animalType;
        this.amount = amount;
        this.unit = unit;
    }

    // getters & setters
    public String getAnimalType() { return animalType; }
    public double getAmount() { return amount; }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() { return unit; }
}

