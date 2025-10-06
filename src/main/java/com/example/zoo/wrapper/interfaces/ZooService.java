package com.example.zoo.wrapper.interfaces;

import com.example.zoo.ass3.models.Penguin;
import com.example.zoo.ass3.models.Lion;
import com.example.zoo.ass3.models.AquariumFish;

// ============   Do‑Not‑Touch ====================
public interface ZooService {
    void init();

    String getZooTitle();

    Object[][] showZoo();

    String addLion(String name, int age, double weight, String gender);

    String addPenguin(String name, int age, double height);

    String addFish(int age, double length, String pattern, String[] colors);

    Lion[] getLions();

    Penguin[] getPenguins();

    AquariumFish[] getFishes();

    String feedAll();
}
