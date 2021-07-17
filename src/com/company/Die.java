package com.company;

import java.util.Random;

public class Die {
    int numberOfSides;
    int maxValue;
    int faceUpValue;

    public Die(int numberOfSides, int maxValue) {
        this.numberOfSides = numberOfSides;
        this.maxValue = maxValue;
    }

    public int roll() {
        Random random = new Random();

        faceUpValue = random.nextInt(maxValue) + 1;
        return showResult();
    }

    public int roll(int numberOfDice) {
        Random random = new Random();

        faceUpValue = numberOfDice * (random.nextInt(maxValue) + 1);
        return showResult();
    }

    public int showResult() {
        return faceUpValue;
    }
}
