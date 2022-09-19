package day6;

import java.util.*;
public class Simulation {

    
    private List<Integer> fishes = new ArrayList<>();

    public void initList() {
        fishes.add(3);
        fishes.add(4);
        fishes.add(3);
        fishes.add(1);
        fishes.add(2);
    }

    public void runSimulation(int cycles) {
        
        for(int i = 0; i < cycles; i++) {

            for(int j = 0; i < fishes.size(); i++) {
                Integer newFishAge = fishes.get(j) - 1;
                fishes.set(i, newFishAge);
            }

            System.out.println(fishes.toString());
        }
    }

    
}
