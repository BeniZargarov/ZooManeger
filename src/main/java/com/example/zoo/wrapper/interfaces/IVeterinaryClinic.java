package com.example.zoo.wrapper.interfaces;

import com.example.zoo.ass3.models.Animal;
import com.example.zoo.ass3.models.MedicalTreatment;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IVeterinaryClinic<T extends Animal> {
    void addAnimal(T animal);
    void addTreatment(T animal, MedicalTreatment treatment);
    Set<T> getAllAnimals();
    Map<T, List<MedicalTreatment>> getRecords();
}

