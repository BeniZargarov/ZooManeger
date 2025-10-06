package com.example.zoo.ass3.manage;

import com.example.zoo.ass3.exceptions.GeneralException;
import com.example.zoo.ass3.general.Address;
import com.example.zoo.ass3.general.FoodSummary;
import com.example.zoo.ass3.general.enums.FishTypes;
import com.example.zoo.ass3.general.enums.PredatorsTypes;
import com.example.zoo.ass3.models.*;
import com.example.zoo.wrapper.interfaces.IZoo;

import java.io.*;
import java.util.*;

import static com.example.zoo.ass3.general.DataUtils.getFishTypes;
import static com.example.zoo.ass3.general.DataUtils.getPredatorType;

public class Manage implements IZoo, Serializable {
    private static Zoo zoo;
    private static final String FILENAME = "zoo.data";

    @Override
    public void init() throws GeneralException {
        File file = new File(FILENAME);
        zoo = new Zoo("TVLZoo", new Address("Tel Aviv", "Bazel", "22"));
        zoo.init();
    }

    @Override
    public String loadData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME))) {
            zoo.loadData(in);
            return "Succeed load zoo.data";
        } catch (FileNotFoundException e) {
            return "No save file found";
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String getZooTitle() {
        return zoo.getZooTitle();
    }

    @Override
    public Map<String, Object> showZoo() {
        Map<String, Object> result = new HashMap<>();
        result.put("details", getZooTitle());
        result.put("predators", zoo.getPredatorSummary());
        result.put("penguins", zoo.getPenguinAmount());
        result.put("fish", zoo.getFishSummary());
        result.put("veterinarySummary", zoo.veterinarySummary());
        return result;
    }

    @Override
    public String addPenguin(String name, int age, double height) {
        try {
            zoo.addPenguin(name, age, height);
            return "Penguin added successfully";
        } catch (GeneralException e) {
            return "Failed to add penguin, " + e.getMessage() + ", Please try again..";
        }
    }

    @Override
    public String addPredator(String name, int age, double weight, String gender, String type) {
        try {
            PredatorsTypes predatorType = getPredatorType(type);
            if (predatorType == null) {
                return "Failed to add predator, invalid predator type, Please try again..";
            }
            zoo.addPredator(name, age, weight, gender, predatorType);
            return type + " added successfully";
        } catch (GeneralException e) {
            return "Failed to add predator, " + e.getMessage() + ", Please try again..";
        }
    }

    @Override
    public String createFish(int age, double length, String pattern, String[] colors, String type) {
        try {
            FishTypes fishType = getFishTypes(type);
            if (fishType == null) {
                return "Failed to add Fish, invalid fish type, Please try again..";
            }
            zoo.addFish(age, length, pattern, colors, fishType);
            return "Fish added successfully";
        } catch (GeneralException e) {
            return "Failed to add fish, " + e.getMessage() + ", Please try again..";
        }
    }

    @Override
    public String createRandomFish(int amount) {
        try {
            return zoo.createFish(amount);
        } catch (GeneralException e) {
            return "Failed to create random fish, " + e.getMessage() + ", Please try again..";
        }
    }

    @Override
    public String getDominantColors() {
        return zoo.getDominantColors();
    }

    @Override
    public List<String> increasingAgeOneYear() {
        return zoo.increasingAgeOneYear();
    }

    @Override
    public Map<PredatorsTypes, List<Predator>> getPredators() {
        return zoo.getPredators();
    }

    @Override
    public List<Penguin> getPenguins(String sortBy) {
        List<Penguin> penguins = new ArrayList<>();
        try {
            penguins = zoo.getPenguins();

            switch (sortBy) {
                case "height" -> Collections.sort(penguins);
                case "name" -> penguins.sort(new NameComparator());
                case "age" -> penguins.sort(new AgeComparator());
            }

            return penguins;
        } catch (GeneralException e) {
            System.out.println(e.getMessage());
        } finally {
            return penguins;
        }
    }

    @Override
    public Map<FishTypes, List<Fish>> getFish() {
        return zoo.getFish();
    }

    @Override
    public List<FoodSummary> feedAll() {
        return zoo.feedAnimals();
    }

    @Override
    public String showAnimalsSounds() {
        return zoo.showAnimalsSounds();
    }

    @Override
    public Map<Animal, List<MedicalTreatment>> getVeterinaryClinic() {
        return zoo.getVeterinaryClinic();
    }

    @Override
    public String exit() {
        ObjectOutputStream file;
        try {
            file = new ObjectOutputStream(new FileOutputStream(FILENAME));
            zoo.saveData(file);
            file.close();
        } catch (IOException e) {
            return "Error: IOException";
        }
        return "All Data saved";
    }


}
