package com.example.zoo.wrapper.service;

import com.example.zoo.ass3.models.Fish;
import com.example.zoo.ass3.models.Penguin;
import com.example.zoo.ass3.models.Predator;


public interface ZooService {
    void init();

    String getZooTitle();

    Object[][] showZoo();

    String addPredator(String name, int age, double weight, String gender, String type);

    String addPenguin(String name, int age, double height);

    String createFish(int age, double length, String pattern, String[] colors, String type);

    Predator[][] getPredators();

    Penguin[] getPenguins();

    Fish[][] getFishes();

    String feedAll();

    String showAnimalsSounds();

    String createRandomFish(int amount);

    String getDominantColors();
}
