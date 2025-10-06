package com.example.zoo.wrapper.controller;

import com.example.zoo.ass3.general.FoodSummary;
import com.example.zoo.ass3.general.enums.FishTypes;
import com.example.zoo.ass3.general.enums.PredatorsTypes;
import com.example.zoo.ass3.models.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.example.zoo.wrapper.ZooApplication.manage;


@RestController
@RequestMapping("/api")
public class ZooController {
    @GetMapping("/loadData")
    public String loadData() {
        return manage.loadData();
    }

    @GetMapping("/exit")
    public String exit() {
        return manage.exit();
    }

    @GetMapping("/zoo")
    public Map<String, Object> getZooData() {
        return manage.showZoo();
    }

    @GetMapping("/penguins")
    public List<Penguin> getPenguins(
            @RequestParam(defaultValue = "height") String sortBy) {
        return manage.getPenguins(sortBy);
    }

    @GetMapping("/predators")
    public Map<PredatorsTypes, List<Predator>> getPredators() {
        return manage.getPredators();
    }

    @GetMapping("/fish")
    public Map<FishTypes, List<Fish>> getFish() {
        Map<FishTypes, List<Fish>> fish = manage.getFish();
        return fish;
    }

    @PostMapping(value = "/add-animal", consumes = "text/plain")
    public String addAnimal(@RequestBody String input) {
        try {
            String[] parts = input.split(",");
            String type = parts[0];
            return switch (type) {
                case "Penguin" -> {
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    double height = Double.parseDouble(parts[3]);
                    yield manage.addPenguin(name, age, height);
                }
                case "Lion" -> {
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    double weight = Double.parseDouble(parts[3]);
                    String gender = parts[4];
                    yield manage.addPredator(name, age, weight, gender, "Lion");
                }
                case "Tiger" -> {
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    double weight = Double.parseDouble(parts[3]);
                    String gender = parts[4];
                    yield manage.addPredator(name, age, weight, gender, "Tiger");
                }
                case "AquariumFish" -> {
                    int age = Integer.parseInt(parts[1]);
                    double length = Double.parseDouble(parts[2]);
                    String pattern = parts[3];
                    String[] colors = Arrays.copyOfRange(parts, 4, parts.length);
                    yield manage.createFish(age, length, pattern, colors, "AquariumFish");
                }
                case "ClownFish" -> {
                    int age = Integer.parseInt(parts[1]);
                    double length = Double.parseDouble(parts[2]);
                    String[] colors = Arrays.copyOfRange(parts, 3, parts.length);
                    yield manage.createFish(age, length, null, colors, "ClownFish");
                }
                case "GoldFish" -> {
                    int age = Integer.parseInt(parts[1]);
                    double length = Double.parseDouble(parts[2]);
                    String[] colors = Arrays.copyOfRange(parts, 3, parts.length);
                    yield manage.createFish(age, length, null, colors, "GoldFish");
                }
                case "RandomFish" -> {
                    int amount = Integer.parseInt(parts[1]);
                    yield manage.createRandomFish(amount);
                }
                default -> "Invalid animal type.";
            };
        } catch (Exception e) {
            return "Error: something went wrong.. " + e.getMessage() + ",  check your data request again ..";
        }
    }

    @GetMapping("/feedAll")
    public List<FoodSummary> feedAll() {
        return manage.feedAll();
    }

    @GetMapping("/soundAll")
    public String soundAll() {
        return manage.showAnimalsSounds();
    }

    @GetMapping("/dominantColors")
    public String dominantColors() {
        return manage.getDominantColors();
    }

    @GetMapping("/increasingAgeOneYear")
    public List<String> increasingAgeOneYear() {
        return manage.increasingAgeOneYear();
    }

    @GetMapping("/veterinaryClinic")
    public Map<Animal, List<MedicalTreatment>> veterinaryClinic() {
        return manage.getVeterinaryClinic();
    }

}
