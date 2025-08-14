package com.example.zoo.wrapper.api;

import com.example.zoo.ass1.models.AquariumFish;
import com.example.zoo.ass1.models.Lion;
import com.example.zoo.ass1.models.Penguin;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.example.zoo.wrapper.ZooApplication.manage;

// ============   Do‑Not‑Touch ====================
@RestController
@RequestMapping("/api")
public class ZooController {
    @GetMapping("/health")
    public Map<String,String> health() {
        return Map.of("status", "ok");
    }

    @GetMapping("/zoo")
    public Map<String, Object> getZooData() {
        Object[][] zooData = manage.showZoo();
        Map<String, Object> result = new HashMap<>();
        result.put("details", Arrays.asList(zooData[0]));
        result.put("lions", Arrays.asList(zooData[1]));
        result.put("penguins", Arrays.asList(zooData[2]));
        result.put("fishes", Arrays.asList(zooData[3]));
        return result;
    }

    @GetMapping("/penguins")
    public List<Penguin> getPenguins() {
        return Arrays.asList(manage.getPenguins());
    }

    @GetMapping("/lions")
    public List<Lion> getLions() {
         return Arrays.asList(manage.getLions());
    }

    @GetMapping("/fishes")
    public List<AquariumFish> getFishes() {
        return Arrays.asList(manage.getFishes());
    }

    @PostMapping(value = "/add-animal", consumes = "text/plain")
    public String addAnimal(@RequestBody String input) {
        String[] parts = input.split(",");
        String type = parts[0];
        String res = "";

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
                yield manage.addLion(name, age, weight, gender);
            }
            case "AquariumFish" -> {
                int age = Integer.parseInt(parts[1]);
                double length = Double.parseDouble(parts[2]);
                String pattern = parts[3];
                String[] colors = Arrays.copyOf(parts, parts.length - 4);
                yield manage.addFish( age, length, pattern, colors);
            }
            default -> "Invalid animal type.";
        };
    }
    @GetMapping("/feedAll")
    public String feedAll() {
        return manage.feedAll();
    }

}
