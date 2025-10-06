package com.example.zoo.ass3.models;

import com.example.zoo.ass3.exceptions.ColorExistException;
import com.example.zoo.ass3.exceptions.ColorNotExistException;
import com.example.zoo.ass3.exceptions.GeneralException;
import com.example.zoo.ass3.general.enums.Color;
import com.example.zoo.ass3.general.enums.Pattern;

import java.util.Arrays;

import static com.example.zoo.ass3.general.DataUtils.colorExist;

public class ClownFish extends Fish {
    public static final int LIFE_EXPECTANCY = 8;
    private final static double EAT = 2f;
    public static final Color[] AVAILABLE_COLORS = {Color.WHITE, Color.ORANGE, Color.BLACK};
    private Color[] colors;

    public ClownFish(int age, double length, Color[] colors) throws GeneralException {
        super(age, length, Pattern.STRIPES);
        setColors(colors);
    }

    public void setColors(Color[] colors) throws GeneralException {
        this.colors = new Color[0];
        for (Color color : colors) {
            if (!colorExist(AVAILABLE_COLORS, AVAILABLE_COLORS.length, color)) {
                throw new ColorNotExistException();
            }
            if (colorExist(this.colors, this.colors.length, color)) {
                throw new ColorExistException();
            }
            this.colors = Arrays.copyOf(this.colors, this.colors.length + 1);
            this.colors[this.colors.length - 1] = color;
        }
    }

    public Color[] getColors() {
        return colors;
    }

    @Override
    public double feed() {
        return EAT;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", colors: [");
        for (int i = 0; i < colors.length; i++) {
            sb.append(colors[i]);
            if (i < colors.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
