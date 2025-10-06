package com.example.zoo.ass3.models;

import com.example.zoo.ass3.exceptions.AgeException;
import com.example.zoo.ass3.exceptions.GeneralException;
import com.example.zoo.ass3.exceptions.NameException;
import com.example.zoo.ass3.exceptions.WeightException;
import com.example.zoo.ass3.general.enums.Gender;

public abstract class Predator extends Animal {
    public static final int LIFE_EXPECTANCY = 15;
    public final static int MAX_AGE = 30;
    protected final static double MALE_FACTOR = 0.02f;
    protected final static double FEMALE_FACTOR = 0.03f;
    protected String name;
    protected double weight;
    protected Gender gender;

    public Predator(String name, int age, double weight, Gender gender) throws GeneralException {
        setName(name);
        setAge(age);
        setWeight(weight);
        setGender(gender);
    }

    public double feed() {
        return (int) (weight * age * (gender == Gender.MALE ? MALE_FACTOR : FEMALE_FACTOR));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NameException {
        if (name.isEmpty()) {
            throw new NameException();
        }
        this.name = name;
    }

    public void setAge(int age) throws AgeException {
        if (age <= 0 || age > MAX_AGE) {
            throw new AgeException();
        }
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) throws WeightException {
        if (weight <= 0) {
            throw new WeightException();
        }
        this.weight = weight;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + name + ", age: " + age + ", weight: " + weight + ", gender: " + gender;
    }
}
