package solution;

import java.util.Map.Entry;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {

    public void solve(String file_name) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        List<String> lines = get_lines(file_name);
        List<Integer> numbers = new ArrayList<>();
        for (String line : lines) {

            String[] parts = line.split("\\|");
            System.out.println(parts[0]);
            HashMap<String, Set<Character>> solutionMap = solveString(parts[0]);
            numbers.add(get_number(parts[1], solutionMap));

        }
        Integer sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println("The result is: " + sum);
    }

    public Integer get_number(String output, HashMap<String, Set<Character>> solutionMap) {


        String[] parts = output.trim().split(" ");
        StringBuilder result = new StringBuilder();
        HashMap<Set<Character>, String> revertHash = revertHash(solutionMap);

        for (String part: parts) {
            Set<Character> setPart = convertStringToHashset(part);
            result.append(revertHash.get(setPart));
        }

        Integer number = Integer.parseInt(result.toString());

        System.out.println(number);
        return(number);
    }


    private HashMap<Set<Character>, String> revertHash(HashMap<String, Set<Character>> solutionMap) {
        HashMap<Set<Character>, String> revertHash = new HashMap<>();
        for (Entry<String, Set<Character>> entry: solutionMap.entrySet()) {
            revertHash.put(entry.getValue(), entry.getKey());
        }

        return(revertHash);
    }

    public List<String> get_lines(String file_name) {
        File file = new File(file_name);
        List<String> lines = new ArrayList<>();
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                lines.add(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return (lines);
    }

    private HashMap<String, Set<Character>> solveString(String input) {
        HashMap<String, Set<Character>> solutionMap = new HashMap<>();
        List<String> six_nine_zero = new ArrayList<>();
        List<String> five_two_three = new ArrayList<>();
        for (String part : input.split(" ")) {
            if (part.length() == 2) {
                solutionMap.put("1", convertStringToHashset(part));
            }
            if (part.length() == 3) {
                solutionMap.put("7", convertStringToHashset(part));
            }
            if (part.length() == 4) {
                solutionMap.put("4", convertStringToHashset(part));
            }
            if (part.length() == 7) {
                solutionMap.put("8", convertStringToHashset(part));
            }
            if (part.length() == 6) {
                six_nine_zero.add(part);
            }
            if (part.length() == 5) {
                five_two_three.add(part);
            }
        }
        for (String num : six_nine_zero) {
            // get nine
            Set<Character> h = convertStringToHashset(num);
            if (h.containsAll(solutionMap.get("4"))) {
                solutionMap.put("9", h);
            } else if (h.containsAll(solutionMap.get("1"))) {
                solutionMap.put("0", h);
            } else {
                solutionMap.put("6", h);
            }

        }

        for (String num : five_two_three) {
            Set<Character> h = convertStringToHashset(num);
            if (h.containsAll(solutionMap.get("1"))) {
                solutionMap.put("3", h);
            } else if (solutionMap.get("6").containsAll(h)) {
                solutionMap.put("5", h);
            } else {
                solutionMap.put("2", h);
            }
        }

        return solutionMap;

    }

    private Set<Character> convertStringToHashset(String str) {

        List<Character> chars = str.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        Set<Character> h = new HashSet<>(chars);
        return h;
    }

}