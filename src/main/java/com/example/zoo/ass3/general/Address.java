package com.example.zoo.ass3.general;

import java.io.Serializable;

public class Address implements Serializable {
    private final String city;
    private final String street;
    private final String number;

    public Address(String city, String street, String number) {
        this.city = city;
        this.street = street;
        this.number = number;
    }

    @Override
    public String toString() {
        return street + ", " + number + ", " + city;
    }
}
