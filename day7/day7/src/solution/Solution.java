package solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;
import java.lang.Math;

public class Solution {


    private HashMap<Integer, Integer> positionMap = new HashMap<>();

    public void loadPositions(String file_name) {
        File file = new File(file_name);

        try (Scanner reader = new Scanner(file)) {
            String line = reader.nextLine();

            String[] parts = line.split(",");

            for (String part : parts) {
                Integer int_part = Integer.parseInt(part);
                positionMap.merge(int_part, 1, Integer::sum);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * 
     */
    public void solve() {
        List<Integer> keys = new ArrayList<>(positionMap.keySet());
        Integer maximum = Collections.max(keys, null); 
        Integer minimum = Collections.min(keys, null);
        
        Integer best_score = Integer.MAX_VALUE;
        Integer best_position = null;
        for (int i = minimum; i <= maximum; i++) {
            // compute the score for this position
            Integer current_score = 0;
            for(Entry<Integer, Integer> entry: positionMap.entrySet()) {
                Integer indiv_key_score = Math.abs(i - entry.getKey());
                current_score += (indiv_key_score * (indiv_key_score + 1) / 2)  * entry.getValue();
            };
            System.out.println("Position: " + i + " got score " + current_score);
            if (current_score < best_score) {
                best_score = current_score;
                best_position = i;
            }
        }

        System.out.println("Best position was: " + best_position + " at a score of: " + best_score);
    }

}