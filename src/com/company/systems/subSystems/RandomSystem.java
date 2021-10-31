package com.company.systems.subSystems;

import com.company.scenes.DrawScene;
import com.company.systems.MainSystem;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class RandomSystem extends MainSystem {
    public RandomSystem(DrawScene drawScene) {
        super(drawScene);
    }

    public Comparable[] randomDataInt(int min, int max) {
        HashMap<Integer, Integer> numbers = new HashMap<>();
        for(int i = 0; i < 10; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            if(numbers.containsKey(randomNum))
                continue;
            numbers.put(randomNum, randomNum);
        }
        return numbers.values().toArray(new Comparable[numbers.size()]);
    }

    public Comparable[] randomDataChar() {
        HashMap<Character, Character> numbers = new HashMap<>();
        for(int i = 0; i < 10; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(65, 90);
            if(numbers.containsKey((int) randomNum))
                continue;
            numbers.put((char) randomNum, (char) randomNum);
        }
        return numbers.values().toArray(new Comparable[numbers.size()]);
    }
}
