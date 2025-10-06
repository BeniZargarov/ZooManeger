package com.example.zoo.ass3.models;

import com.example.zoo.wrapper.interfaces.IVeterinaryClinic;

import java.util.*;

public class VeterinaryClinic<T extends Animal> implements IVeterinaryClinic<T> {

    private final Map<T, List<MedicalTreatment>> records = new HashMap<>();

    @Override
    public void addAnimal(T animal) {
        records.put(animal, new ArrayList<>());
    }

    @Override
    public void addTreatment(T animal, MedicalTreatment treatment) {
        records.get(animal).add(treatment);
    }

    @Override
    public Set<T> getAllAnimals() {
        return records.keySet();
    }

    @Override
    public Map<T, List<MedicalTreatment>> getRecords() {
        return records;
    }
}
