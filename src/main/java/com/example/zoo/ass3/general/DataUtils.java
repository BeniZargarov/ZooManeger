package com.example.zoo.ass3.general;

import com.example.zoo.ass3.general.enums.*;

public class DataUtils {
    public static Gender getGender(String gender) {
        for (Gender g : Gender.values()) {
            if (g.name().equals(gender.trim().toUpperCase())) {
                return g;
            }
        }
        return null;
    }

    public static Pattern getPattern(String pattern) {
        for (Pattern p : Pattern.values()) {
            if (p.name().equals(pattern.trim().toUpperCase())) {
                return p;
            }
        }
        return null;
    }

    public static Color getColor(String color) {
        for (Color c : Color.values()) {
            if (c.name().equals(color.trim().toUpperCase())) {
                return c;
            }
        }
        return null;
    }

    public static PredatorsTypes getPredatorType(String predatorTypeStr) {
        for (PredatorsTypes predatorsType : PredatorsTypes.values()) {
            if (predatorsType.name().equals(predatorTypeStr.trim())) {
                return predatorsType;
            }
        }
        return null;
    }

    public static FishTypes getFishTypes(String fishTypesStr) {
        for (FishTypes fishType : FishTypes.values()) {
            if (fishType.name().equals(fishTypesStr.trim())) {
                return fishType;
            }
        }
        return null;
    }

    public static boolean colorExist(Color[] colors, int numOfColors, Color color) {
        for (int i = 0; i < numOfColors; i++) {
            if (colors[i].equals(color)) {
                return true;
            }
        }
        return false;
    }

    public static double set2Digits(double num) {
        return (int) (num * 100) / 100.0;
    }

    public static boolean isValidMinimumPhysicalSize(double num){
        return num >= 0;
    }
}
