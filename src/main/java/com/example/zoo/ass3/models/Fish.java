package com.example.zoo.ass3.models;

import com.example.zoo.ass3.exceptions.AgeException;
import com.example.zoo.ass3.exceptions.GeneralException;
import com.example.zoo.ass3.exceptions.LengthException;
import com.example.zoo.ass3.general.enums.Color;
import com.example.zoo.ass3.general.enums.Pattern;

public abstract class Fish extends Animal {
    public final static int MAX_AGE = 30;
    public final static int MAX_LENGTH = 50;
    protected double length;
    protected Pattern pattern;

    public Fish(int age, double length, Pattern pattern) throws GeneralException {
        setAge(age);
        setLength(length);
        setPattern(pattern);
    }

    abstract public Color[] getColors();

    @Override
    public String makeNoise() {
        return "blob";
    }

    public void setAge(int age) throws AgeException {
        if (age <= 0 || age > MAX_AGE) {
            throw new AgeException();
        }
        this.age = age;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) throws LengthException {
        if (length <= 0 || length > MAX_LENGTH) {
            throw new LengthException();
        }
        this.length = length;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + "age: " +
                age +
                ", length: " +
                length +
                ", pattern: " +
                pattern;
    }
}
