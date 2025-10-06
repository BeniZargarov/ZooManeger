package com.example.zoo.ass3.models;

import java.time.LocalDate;

public class MedicalTreatment {
    private final String description;
    private final LocalDate date;

    public MedicalTreatment(String description) {
        this.description = description;
        this.date = LocalDate.now();
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return date + " - " + description + ")";
    }
}

