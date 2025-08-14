package com.example.zoo.ass1.manage;

import com.example.zoo.ass1.models.*;
import com.example.zoo.wrapper.interfaces.ZooService;

public class Manage implements ZooService {
    private Zoo zoo;

    @Override
    public void init() {
        zoo = new Zoo("TVLZoo", new Address("Tel Aviv", "Bazel", "22"));
        zoo.initializeZoo();
    }

    @Override
    public String getZooTitle() {
        // TODO need to implements
        return "hi beni";
    }

    @Override
    public Object[][] showZoo() {
        Object[][] data = new Object[4][1];
        data[0][0] = getZooTitle();
        data[1][0] = getLions().length;
        data[2][0] = getPenguins().length;
        data[3][0] = getFishes().length;
        return data;
    }

    @Override
    public String addPenguin(String name, int age, double height) {
        // TODO need to implements
        return "not working.. need to implements";
    }

    @Override
    public String addLion(String name, int age, double weight, String gender) {
        // TODO need to implements
        return "not working.. need to implements";
    }

    @Override
    public String addFish(int age, double length, String pattern, String[] colors) {
        // TODO need to implements
        return "not working.. need to implements";
    }

    @Override
    public Lion[] getLions() {
        // TODO need to implements
        return new Lion[0];
    }

    @Override
    public Penguin[] getPenguins() {
        // TODO need to implements
        return new Penguin[0];
    }

    @Override
    public AquariumFish[] getFishes() {
        // TODO need to implements
        return new AquariumFish[0];
    }

    @Override
    public String feedAll() {
        // TODO need to implements
        return "not working.. need to implements";
    }
}
