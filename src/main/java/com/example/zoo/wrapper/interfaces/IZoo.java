package com.example.zoo.wrapper.interfaces;

import com.example.zoo.ass3.exceptions.GeneralException;
import com.example.zoo.ass3.general.FoodSummary;
import com.example.zoo.ass3.general.enums.FishTypes;
import com.example.zoo.ass3.general.enums.PredatorsTypes;
import com.example.zoo.ass3.models.*;

import java.util.List;
import java.util.Map;


public interface IZoo {
    void init() throws GeneralException;
    String exit();

    String getZooTitle();

    Map<String, Object> showZoo();

    String addPredator(String name, int age, double weight, String gender, String type);

    String addPenguin(String name, int age, double height);

    String createFish(int age, double length, String pattern, String[] colors, String type);

    Map<PredatorsTypes, List<Predator>> getPredators();

    List<Penguin> getPenguins(String sortBy);

    Map<FishTypes, List<Fish>> getFish();

    List<FoodSummary> feedAll();

    String showAnimalsSounds();

    String createRandomFish(int amount);

    String getDominantColors();

    List<String> increasingAgeOneYear();

    String loadData();

    Map<Animal, List<MedicalTreatment>> getVeterinaryClinic();
}
