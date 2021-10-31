package com.company.systems.subSystems;

import com.company.scenes.DrawScene;
import com.company.systems.MainSystem;

import java.util.HashMap;
import java.util.Random;

/**
 * Denne klassen håndterer tilfeldig data for treet
 */
public class RandomSystem extends MainSystem {
    public RandomSystem(DrawScene drawScene) {
        super(drawScene);
    }

    /**-
     * Lager en array med tilfeldig int data
     * @param min minste tall
     * @param max største tall
     * @return En Comparable array med data
     */
    public Comparable[] randomDataInt(int min, int max) {
        HashMap<Integer, Integer> numbers = new HashMap<>();
        Random r = new Random();
        for(int i = 0; i < 10; i++) {
            int randomNum = r.nextInt((max - min) + 1) + min;
            if(numbers.containsKey(randomNum)) {
                i--;
                continue;
            }
            numbers.put(randomNum, randomNum);
        }
        return numbers.values().toArray(new Comparable[numbers.size()]);
    }

    /**
     * Lager en arrat med tilfeldig char data
     * @return En Comparable array med data
     */
    public Comparable[] randomDataChar() {
        HashMap<Character, Character> numbers = new HashMap<>();
        Random r = new Random();
        for(int i = 0; i < 10; i++) {
            int randomNum = r.nextInt((90 - 65) + 1) + 65;
            if(numbers.containsKey((int) randomNum)) {
                i--;
                continue;
            }
            numbers.put((char) randomNum, (char) randomNum);
        }
        return numbers.values().toArray(new Comparable[numbers.size()]);
    }
}
