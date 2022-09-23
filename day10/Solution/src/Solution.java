import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
    private String file_name;
    private List<String> lines = new ArrayList<>();
    private HashMap<Character, Character> translations = new HashMap<>();
    private HashMap<Character, Integer> scoreMap = new HashMap<>();

    public Solution(String file_name) {
        this.file_name = file_name;
        readInput();
    }

    public void solve() {
        Integer sum = 0;
        for (String line: lines) {
            sum +=solveLine(line);
        }
    }

    private Integer solveLine(String line) {

        Stack<Character> stack = new Stack<>();

        for (char mychar: line.toCharArray()) {
            Character myChar = Character.valueOf(mychar);

            if ( translations.containsKey(myChar)) {
                stack.push(myChar);
            } else { 
                if (translations.get(stack.peek()) == myChar) {
                    stack.pop();
                } else { 
                    System.out.println("Illegal line with char: " + myChar);
                    return(scoreMap.get(myChar));
                }
            }
        }
        return 0;

        
    }

    private void fillTranslations() {
        translations.put('(', ')');
        translations.put('[', ']');
        translations.put('{', '}');
        translations.put('<', '>');
    }

    private void fillScores() {
        scoreMap.put(')', 3);
        scoreMap.put(']', 57);
        scoreMap.put('}', 1197);
        scoreMap.put('>', 25137);
    }


    private void readInput() {
            File file = new File(file_name);

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                lines.add(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    
}
