package com.example.zoo.ass3.models;

import java.util.Comparator;

public class AgeComparator implements Comparator<Penguin> {
    @Override
    public int compare(Penguin p1, Penguin p2) {
        return Integer.compare(p1.getAge(), p2.getAge());
    }
}
