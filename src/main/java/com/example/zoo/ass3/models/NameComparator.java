package com.example.zoo.ass3.models;

import java.util.Comparator;

public class NameComparator implements Comparator<Penguin> {
    @Override
    public int compare(Penguin p1, Penguin p2) {
        return p1.getName().compareTo(p1.getName());
    }
}
