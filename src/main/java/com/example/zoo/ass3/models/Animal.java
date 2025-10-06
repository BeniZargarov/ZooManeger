package com.example.zoo.ass3.models;

public abstract class Animal {
    protected int age;
    protected int happiness = 100;

    public abstract double feed();

    public abstract String makeNoise();

    public void ageOneYear() {
        this.age++;
    }

    public int getAge() {
        return age;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }
}
