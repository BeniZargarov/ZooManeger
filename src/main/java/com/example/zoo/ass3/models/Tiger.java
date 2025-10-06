package com.example.zoo.ass3.models;

import com.example.zoo.ass3.exceptions.GeneralException;
import com.example.zoo.ass3.general.enums.Gender;

public class Tiger extends Predator {

    public Tiger(String name, int age, double weight, Gender gender) throws GeneralException {
        super(name, age, weight, gender);
    }

    @Override
    public String makeNoise() {
        return "roar";
    }

}
