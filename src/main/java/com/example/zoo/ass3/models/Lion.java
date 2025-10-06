package com.example.zoo.ass3.models;

import com.example.zoo.ass3.exceptions.GeneralException;
import com.example.zoo.ass3.general.enums.Gender;

public class Lion extends Predator {
    private final static int MAX_EAT = 25;

    public Lion(String name, int age, double weight, Gender gender) throws GeneralException {
        super(name, age, weight, gender);
    }

    public double feed() {
        return Math.min(MAX_EAT, super.feed());
    }

    @Override
    public String makeNoise() {
        return "ROAR";
    }

}
