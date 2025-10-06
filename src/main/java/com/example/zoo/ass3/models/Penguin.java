package com.example.zoo.ass3.models;

import com.example.zoo.ass3.exceptions.AgeException;
import com.example.zoo.ass3.exceptions.GeneralException;
import com.example.zoo.ass3.exceptions.HeightException;
import com.example.zoo.ass3.exceptions.NameException;

public class Penguin extends Animal implements Comparable<Penguin> {
    public static final int LIFE_EXPECTANCY = 6;
    public final static int MAX_AGE = 50;
    public final static int MAX_HEIGHT = 220;
    private final static double EAT = 1f;
    private String name;
    private double height;
    private boolean isLeader = false;
    private Penguin next;

    public Penguin(String name, int age, double height) throws GeneralException {
        setAge(age);
        setName(name);
        setHeight(height);
    }

    public double feed() {
        if (this.isLeader) {
            return 2 * EAT;
        }
        return EAT;
    }

    @Override
    public String makeNoise() {
        Penguin temp = this;
        StringBuilder sb = new StringBuilder();
        while (temp != null) {
            sb.append("squack");
            temp = temp.next;
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NameException {
        if (name.trim().isEmpty()) {
            throw new NameException();
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws AgeException {
        if (age <= 0 || age > Penguin.MAX_AGE) {
            throw new AgeException();
        }
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) throws HeightException {
        if (height <= 0 || height > Penguin.MAX_HEIGHT) {
            throw new HeightException();
        }
        this.height = height;
    }

    public Penguin getNext() {
        return next;
    }

    public void setNext(Penguin next) {
        this.next = next;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public void setIsLeader(boolean leader) {
        isLeader = leader;
    }

    @Override
    public String toString() {
        return name + ", age: " + age + ", height: " + height;
    }

    @Override
    public int compareTo(Penguin o) {
        return Double.compare(o.height, this.height);
    }
}


