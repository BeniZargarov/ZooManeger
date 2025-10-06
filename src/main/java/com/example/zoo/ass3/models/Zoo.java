package com.example.zoo.ass3.models;

import com.example.zoo.ass3.exceptions.*;
import com.example.zoo.ass3.general.Address;
import com.example.zoo.ass3.general.FoodSummary;
import com.example.zoo.ass3.general.enums.*;
import org.springframework.boot.autoconfigure.ssl.PemSslBundleProperties;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

import static com.example.zoo.ass3.general.DataUtils.*;

public class Zoo {
    private final String name;
    private final Address address;
    private final Map<String, List<Animal>> animals;
    private final VeterinaryClinic<Animal> veterinaryClinic;

    public Zoo(String name, Address address) {
        this.name = name;
        this.address = address;
        animals = new HashMap<>();
        animals.put(Predator.class.getSimpleName(), new ArrayList<>());
        animals.put(Fish.class.getSimpleName(), new ArrayList<>());
        animals.put(Penguin.class.getSimpleName(), new ArrayList<>());
        veterinaryClinic = new VeterinaryClinic<>();
    }

    public void addPenguin(String name, int age, double height) throws GeneralException {
        if (!isValidMinimumPhysicalSize(age) || !isValidMinimumPhysicalSize(height)) {
            throw new PenguinHeightException();
        }
        addPenguin(new Penguin(name, age, height));
    }

    public void addPenguin(Penguin penguin) throws PenguinHeightException {
        if (penguin.getHeight() > Penguin.MAX_HEIGHT) {
            throw new PenguinHeightException();
        }
        animals.get(Penguin.class.getSimpleName()).add(penguin);
        setPenguinLeader();
    }

    private void setPenguinLeader() {
        if (!(animals.get(Penguin.class.getSimpleName()).size() == 0)) {
            Penguin biggest = (Penguin) animals.get(Penguin.class.getSimpleName()).get(0);

            for (int i = 0; i < animals.get(Penguin.class.getSimpleName()).size(); i++) {
                Penguin penguin1 = (Penguin) animals.get(Penguin.class.getSimpleName()).get(i);
                if (penguin1.isLeader()) {
                    penguin1.setIsLeader(false);
                }
            }

            for (int i = 0; i < animals.get(Penguin.class.getSimpleName()).size(); i++) {
                Penguin penguin1 = (Penguin) animals.get(Penguin.class.getSimpleName()).get(i);

                if (biggest.getHeight() < penguin1.getHeight()) {
                    biggest = penguin1;
                }
            }
            biggest.setIsLeader(true);
        }
    }

    private Penguin getPenguinsLeader() {
        List<Animal> list = animals.get(Penguin.class.getSimpleName());
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (Penguin) animals.get(Penguin.class.getSimpleName()).get(0);
    }

    public void addPredator(String name, int age, double weight, String gender, PredatorsTypes type) throws GeneralException {
        if (!isValidMinimumPhysicalSize(age)) {
            throw new AgeException();
        }
        if (!isValidMinimumPhysicalSize(weight)) {
            throw new WeightException();
        }
        Gender g = getGender(gender);
        if (g == null) {
            throw new GenderException();
        }
        switch (type) {
            case Lion -> addPredator(new Lion(name, age, weight, g));
            case Tiger -> addPredator(new Tiger(name, age, weight, g));
        }
    }

    public void addPredator(Predator predator) {
        animals.get(Predator.class.getSimpleName()).add(predator);
    }

    public void addFish(int age, double length, String patternStr, String[] colorsStr, FishTypes type) throws GeneralException {
        Color[] colors;
        switch (type) {
            case AquariumFish -> {
                Pattern pattern = getPattern(patternStr);
                if (pattern == null) {
                    throw new PatternException();
                }
                colors = createColorArr(colorsStr);
                animals.get(Fish.class.getSimpleName()).add(new AquariumFish(age, length, pattern, colors));
            }
            case ClownFish -> {
                colors = createColorArr(colorsStr);
                animals.get(Fish.class.getSimpleName()).add(new ClownFish(age, length, colors));
            }
            case GoldFish -> {
                colors = createColorArr(colorsStr);
                animals.get(Fish.class.getSimpleName()).add(new GoldFish(age, length, colors[0]));
            }
        }
    }

    private Color[] createColorArr(String[] colorsStr) throws ColorNotExistException {
        if (colorsStr == null || colorsStr.length == 0) {
            throw new ColorNotExistException();
        }
        Color[] colors = new Color[colorsStr.length];
        Color temp;
        for (int i = 0; i < colorsStr.length; i++) {
            temp = getColor(colorsStr[i]);
            if (temp == null) {
                throw new ColorNotExistException();
            }
            colors[i] = temp;
        }
        return colors;
    }

    public List<FoodSummary> feedAnimals() {
        Map<String, FoodSummary> totals = new HashMap<>();
        for (List<Predator> predatorList : getPredators().values()) {
            for (Predator predator : predatorList) {
                predator.setHappiness(100);
                double amount = predator.feed();

                String type = predator.getClass().getSimpleName();
                String unit = "kg";

                totals.putIfAbsent(type, new FoodSummary(type, 0, unit));
                totals.get(type).setAmount(totals.get(type).getAmount() + amount);
            }
        }
        for (List<Fish> fishList : getFish().values()) {
            for (Fish fish : fishList) {
                fish.setHappiness(100);
                double amount = fish.feed();

                String type = fish.getClass().getSimpleName();
                String unit = "dishes";

                totals.putIfAbsent(type, new FoodSummary(type, 0, unit));
                totals.get(type).setAmount(totals.get(type).getAmount() + amount);
            }
        }
        List<Animal> penguinList = animals.get(Penguin.class.getSimpleName());
        for (Animal penguin : penguinList) {
            penguin.setHappiness(100);
            double amount = penguin.feed();

            String type = penguin.getClass().getSimpleName();
            String unit = "fish";

            totals.putIfAbsent(type, new FoodSummary(type, 0, unit));
            totals.get(type).setAmount(totals.get(type).getAmount() + amount);
        }

        return new ArrayList<>(totals.values());
    }

    public String showAnimalsSounds() {
        StringBuilder sb = new StringBuilder();
        for (List<Animal> list : animals.values()) {
            for (Animal animal : list) {
                sb.append(animal.makeNoise());
            }
        }
        return sb.toString();
    }

    private int getNumOfPenguins(Penguin penguin) {
        if (penguin == null) {
            return 0;
        }
        return 1 + getNumOfPenguins(penguin.getNext());
    }

    public List<String> increasingAgeOneYear() {
        List<String> results = new ArrayList<>();
        results.add("All Animals was added on year..");
        Map<PredatorsTypes, List<Predator>> predators = new HashMap<>();
        predators = getPredators();
        for (List<Predator> predatorlist : predators.values()) {
            List<Predator> toRemove = new ArrayList<>();
            for (Predator predator : predatorlist) {

                if (predator.getAge() + 1 > Predator.LIFE_EXPECTANCY) {
                    results.add(predator.toString());
                    toRemove.add(predator);
                } else {
                    try {
                        predator.ageOneYear();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            animals.get(Predator.class.getSimpleName()).removeAll(toRemove);
        }

        Map<FishTypes, List<Fish>> fishMap = new HashMap<>();
        fishMap = getFish();
        for (List<Fish> listfish  : fishMap.values()) {
            List<String> toRemove = new ArrayList<>();
            toRemove = increaseAgeHelper(listfish);
            results.addAll(toRemove);
        }

        List<Penguin> toRemovePenguins = new ArrayList<>();
        Random rand2 = new Random();

        Penguin penguin;
        for (int i = 0; i < animals.get(Penguin.class.getSimpleName()).size(); i++) {
            penguin = (Penguin) animals.get(Penguin.class.getSimpleName()).get(i);

            if (penguin.getAge() + 1 >= Penguin.LIFE_EXPECTANCY) {
                results.add(penguin + " died of old age");
                toRemovePenguins.add(penguin);
            } else {
                penguin.ageOneYear();


                int decrease = 15 + rand2.nextInt(16); // 15–30
                penguin.setHappiness(penguin.getHappiness() - decrease);

                if (penguin.getHappiness() <= 0) {
                    results.add(penguin + " removed due to unhappiness");
                    toRemovePenguins.add(penguin);
                }
            }
        }
        animals.get(Penguin.class.getSimpleName()).removeAll(toRemovePenguins);
        setPenguinLeader();

        return results;
    }

    private <T extends Fish> List<String> increaseAgeHelper(List<T> fishs) {
        List<String> results = new ArrayList<>();
        List<T> toRemove = new ArrayList<>();

        for (T fish : fishs) {
            try {
                Field field = fish.getClass().getDeclaredField("LIFE_EXPECTANCY");
                field.setAccessible(true);
                int lifeExpectancy = (int) field.get(null);
                if (fish.getAge() + 1 > lifeExpectancy) {
                    toRemove.add(fish);
                    results.add(fish.toString());
                } else {
                    fish.ageOneYear();
                }

            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.out.println("No LIFE_EXPECTANCY found for " + fish.getClass().getSimpleName());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        animals.get(Fish.class.getSimpleName()).removeAll(toRemove);
        return results;
    }

    public void init() throws GeneralException {
        Penguin penguin = new Penguin("Doris", 12, 200f);
        addPenguin(penguin);
        penguin = new Penguin("Shalom", 2, 51.5);
        addPenguin(penguin);
        penguin = new Penguin("Bar", 10, 162.8);
        addPenguin(penguin);
        veterinaryClinic.addAnimal(penguin);
        veterinaryClinic.addTreatment(penguin, new MedicalTreatment("Antibiotic treatment for infection"));
        Lion lion = new Lion("Barak", 5, 80, Gender.MALE);
        addPredator(lion);
        lion = new Lion("Dany", 12, 180, Gender.MALE);
        addPredator(lion);
        lion = new Lion("Ana", 3, 50, Gender.FEMALE);
        addPredator(lion);
        veterinaryClinic.addAnimal(lion);
        veterinaryClinic.addTreatment(lion, new MedicalTreatment("Dental check-up"));
        lion = new Lion("Tami", 15, 200, Gender.FEMALE);
        addPredator(lion);
        Tiger tiger = new Tiger("Dana", 5, 80, Gender.FEMALE);
        addPredator(tiger);
        tiger = new Tiger("Ringo", 9, 120, Gender.MALE);
        veterinaryClinic.addAnimal(tiger);
        veterinaryClinic.addTreatment(tiger, new MedicalTreatment("Routine health examination"));
        addPredator(tiger);
        addFish(10);

    }

    private void addFish(int amount) throws GeneralException {
        Random r = new Random();
        for (int i = 0; i < amount; i++) {
            createAqFish(r);
        }
    }

    public String createFish(int amount) throws GeneralException {
        Random r = new Random();
        FishTypes fishTypes = FishTypes.values()[r.nextInt(FishTypes.values().length)];
        switch (fishTypes) {
            case AquariumFish -> {
                for (int i = 0; i < amount; i++) {
                    createAqFish(r);
                }
            }
            case ClownFish -> {
                for (int i = 0; i < amount; i++) {
                    createClownFish(r);
                }
            }
            case GoldFish -> {
                for (int i = 0; i < amount; i++) {
                    createGoldFish(r);
                }
            }
        }
        return amount + " fish created successfully";
    }

    private void createAqFish(Random r) throws GeneralException {
        double length;
        Color[] colors;
        int numOfColors;
        int age;
        Pattern pattern;
        int patternSize = Pattern.values().length, colorSize = Color.values().length;
        age = r.nextInt(15) + 1;
        length = set2Digits(r.nextDouble(4) + 1);
        pattern = Pattern.values()[r.nextInt(patternSize)];
        numOfColors = r.nextInt(4) + 1;
        colors = new Color[numOfColors];
        Color color;
        boolean colorExist;
        for (int j = 0; j < numOfColors; j++) {
            do {
                color = Color.values()[r.nextInt(colorSize)];
                colorExist = colorExist(colors, j, color);
            } while (colorExist);
            colors[j] = color;
        }
        List<Animal> fish = animals.get(Fish.class.getSimpleName());
        if (fish == null) {
            fish = new ArrayList<>();
        }
        fish.add(new AquariumFish(age, length, pattern, colors));
        animals.put(Fish.class.getSimpleName(), fish);
    }

    private void createClownFish(Random r) throws GeneralException {
        double length;
        Color[] colors;
        int numOfColors;
        int age;
        age = r.nextInt(15) + 1;
        length = set2Digits(r.nextDouble(4) + 1);
        numOfColors = r.nextInt(2) + 1;
        colors = new Color[numOfColors];
        Color color;
        boolean colorExist;
        for (int j = 0; j < numOfColors; j++) {
            do {
                color = ClownFish.AVAILABLE_COLORS[r.nextInt(ClownFish.AVAILABLE_COLORS.length)];
                colorExist = colorExist(colors, j, color);
            } while (colorExist);
            colors[j] = color;
        }
        List<Animal> fish = animals.get(Fish.class.getSimpleName());
        if (fish == null) {
            fish = new ArrayList<>();
        }
        fish.add(new ClownFish(age, length, colors));
        animals.put(Fish.class.getSimpleName(), fish);
    }

    private void createGoldFish(Random r) throws GeneralException {
        double length;
        int age;
        age = r.nextInt(15) + 1;
        length = set2Digits(r.nextDouble(4) + 1);
        Color color = GoldFish.AVAILABLE_COLORS[r.nextInt(GoldFish.AVAILABLE_COLORS.length)];
        List<Animal> fish = animals.get(Fish.class.getSimpleName());
        if (fish == null) {
            fish = new ArrayList<>();
        }
        fish.add(new GoldFish(age, length, color));
        animals.put(Fish.class.getSimpleName(), fish);
    }

    public String getZooTitle() {
        return name + ", " + address;
    }

    public int[] getPredatorSummary() {
        int[] numOfPredators = new int[PredatorsTypes.values().length];
        List<Animal> predators = animals.get(Predator.class.getSimpleName());
        if (predators != null && !predators.isEmpty()) {
            for (Animal animal : predators) {
                numOfPredators[PredatorsTypes.valueOf(animal.getClass().getSimpleName()).ordinal()]++;
            }
        }
        return numOfPredators;
    }

    public int getPenguinAmount() {
        Penguin leader = getPenguinsLeader();
        if (leader == null) {
            return 0;
        }
        return getNumOfPenguins(leader);
    }

    public int[] getFishSummary() {
        int[] numOfFish = new int[FishTypes.values().length];
        List<Animal> fish = animals.get(Fish.class.getSimpleName());
        if (fish != null && !fish.isEmpty()) {
            for (Animal animal : fish) {
                numOfFish[FishTypes.valueOf(animal.getClass().getSimpleName()).ordinal()]++;
            }
        }
        return numOfFish;
    }

    public Map<PredatorsTypes, List<Predator>> getPredators() {
        Map<PredatorsTypes, List<Predator>> predatorsMap = new HashMap<>();
        List<Animal> predators = animals.get(Predator.class.getSimpleName());
        if (predators != null && !predators.isEmpty()) {
            for (Animal animal : predators) {
                PredatorsTypes type = PredatorsTypes.valueOf(animal.getClass().getSimpleName());
                predatorsMap.computeIfAbsent(type, k -> new ArrayList<>());
                predatorsMap.get(type).add((Predator) animal);
                predatorsMap.put(type, predatorsMap.get(type));
            }
        }
        return predatorsMap;
    }

    public Map<FishTypes, List<Fish>> getFish() {
        Map<FishTypes, List<Fish>> fishMap = new HashMap<>();
        List<Animal> fish = animals.get(Fish.class.getSimpleName());
        if (fish != null && !fish.isEmpty()) {
            for (Animal animal : fish) {
                FishTypes type = FishTypes.valueOf(animal.getClass().getSimpleName());
                fishMap.computeIfAbsent(type, k -> new ArrayList<>());
                fishMap.get(type).add((Fish) animal);
                fishMap.put(type, fishMap.get(type));
            }
        }
        return fishMap;
    }

    public String getDominantColors() {
        int[] dominantColors = new int[Color.values().length];
        List<Animal> fish = animals.get(Fish.class.getSimpleName());
        if (fish != null && !fish.isEmpty()) {
            for (Animal animal : fish) {
                updateDominantColors(dominantColors, ((Fish) animal).getColors());
            }
        }
        Color[] colors = Color.values();
        int[] maxIndex = {-1, -1};
        updateMaxIndex(dominantColors, maxIndex);
        String res = "";
        if (maxIndex[0] != -1) {
            res += colors[maxIndex[0]];
            if (maxIndex[1] != -1) {
                res += "," + colors[maxIndex[1]];
            }
        }
        return res;
    }

    private void updateMaxIndex(int[] dominantColors, int[] maxIndex) {
        int max1 = -1, max2 = -1;
        for (int i = 0; i < dominantColors.length; i++) {
            if (dominantColors[i] > max1) {
                maxIndex[1] = maxIndex[0];
                maxIndex[0] = i;
                max2 = max1;
                max1 = dominantColors[i];
            } else if (dominantColors[i] > max2) {
                maxIndex[1] = i;
                max2 = dominantColors[i];
            }
        }
    }

    private void updateDominantColors(int[] dominantColors, Color[] colors) {
        for (Color color : colors) {
            dominantColors[color.ordinal()]++;
        }
    }

    public List<Penguin> getPenguins() throws GeneralException {
        List<Penguin> penguins = new ArrayList<>();
        Penguin penguin;

        for (int i = 0; i < animals.get(Penguin.class.getSimpleName()).size(); i++) {
            penguin = (Penguin) animals.get(Penguin.class.getSimpleName()).get(i);
            penguins.add(penguin);
        }

        return penguins;
    }

    public Map<String, Object> veterinarySummary() {
        Map<String, Object> summary = new LinkedHashMap<>();
        Map<String, Integer> byType = new LinkedHashMap<>();

        Map<Animal, List<MedicalTreatment>> clinic = this.getVeterinaryClinic();

        int total = 0;
        for (Animal animal : clinic.keySet()) {
            total++;
            String type = animal.getClass().getSimpleName();
            byType.put(type, byType.getOrDefault(type, 0) + 1);
        }

        summary.put("totalSick", total);
        summary.put("byType", byType);
        return summary;
    }

    public Map<Animal, List<MedicalTreatment>> getVeterinaryClinic() {
        return veterinaryClinic.getRecords();
    }

    public void saveData(ObjectOutputStream file) {
        try {
            file.reset();
            file.writeObject(animals);
            file.writeObject(veterinaryClinic.getRecords());
        } catch (IOException e) {
            System.out.println("Error: IOException");
        }
    }

    public void loadData(ObjectInputStream file) {
        try {
            Map<? extends String, ? extends List<Animal>> animals_temp = (Map<? extends String, ? extends List<Animal>>) file.readObject();
            Map<Animal, List<MedicalTreatment>> records_temp = (Map<Animal, List<MedicalTreatment>>) file.readObject();

            animals.clear();
            animals.putAll(animals_temp);
            veterinaryClinic.getRecords().clear();
            veterinaryClinic.getRecords().putAll(records_temp);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: Exception Activated");
        }
    }
}
