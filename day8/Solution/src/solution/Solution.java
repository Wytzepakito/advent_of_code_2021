package solution;

import java.util.Map.Entry;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {


    private Set<Character> convertStringToHashset(String str) {

        List<Character> chars = str.chars().mapToObj(e -> (char)e).collect(Collectors.toList());
        Set<Character> h = new HashSet<>(chars);
        return h;
    }

    private void solveString(String input) {
        HashMap<Integer, Set<Character>> solutionMap = new HashMap<>();
        List<String> six_nine_zero = new ArrayList<>();
        List<String> five_two_three = new ArrayList<>();
        for (String part: input.split(" ")) {
            if (part.length() == 2) {
                solutionMap.put(1, convertStringToHashset(part));
            }
            if (part.length() == 3) {
                solutionMap.put(7, convertStringToHashset(part));
            }
            if (part.length() == 4) {
                solutionMap.put(4, convertStringToHashset(part));
            }
            if (part.length() == 7) {
                solutionMap.put(8, convertStringToHashset(part));
            }
            if (part.length() == 6) {
                six_nine_zero.add(part);
            }
            if (part.length() == 5) {
                five_two_three.add(part);
            }
        }
        for (String num: six_nine_zero) {
            // get nine
            Set<Character> h = convertStringToHashset(num);
            if (h.containsAll( solutionMap.get(4))) {
                solutionMap.put(9, h);
            } else if (h.containsAll(solutionMap.get(1))) {
                solutionMap.put(0, h);
            } else {
                solutionMap.put(6, h);
            }
            
        }

        for (String num: five_two_three) {
            Set<Character> h = convertStringToHashset(num);
            System.out.println(h);
            if (h.containsAll(solutionMap.get(1)) ) { 
                solutionMap.put(3, h);
            } else if ( solutionMap.get(6).containsAll(h) ) {
                solutionMap.put(5, h);
            } else { 
                solutionMap.put(2, h);
            }
            }
        
        for ( Entry<Integer, Set<Character>> entry : solutionMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " has value: " + entry.getValue());
        }

        }

    public void count_nums(String file_name) {
        File file = new File(file_name);
        Integer count = 0;
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split("\\|");
                solveString(parts[0]);
                String[] output_parts = parts[1].split(" ");

                for (String part : output_parts) {
                    if (part.length() == 2 || part.length() == 3 || part.length() == 4 || part.length() == 7) {
                        count += 1;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}