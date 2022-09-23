import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
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
        fillScores();
        fillTranslations();
        readInput();
    }

    public void solve() {
        List<Long> scores = new ArrayList<>();
        for (String line: lines) {
            Long score = solveLine(line);
            if (score > 0) {
                scores.add(score);
            }
        }
        Collections.sort(scores);

        Integer index = scores.size() / 2;

        System.out.println("middle score is: " + scores.get(index));
    }

    private Long solveLine(String line) {

        Stack<Character> stack = new Stack<>();

        for (char mychar: line.toCharArray()) {
            Character myChar = Character.valueOf(mychar);

            if ( translations.containsKey(myChar)) {
                stack.push(myChar);
            } else { 
                if (translations.get(stack.peek()) == myChar) {
                    stack.pop();
                } else { 
                    return (long) (0);
                }
            }
        }
        Long score = (long) 0;
        if (!stack.empty()) {
            while (!stack.empty()) {
                Character myChar = stack.pop();
                score *= 5;
                score += scoreMap.get(translations.get(myChar));
            }

            return(score);
        }
        
        return (long) 0;

        
    }

    private void fillTranslations() {
        translations.put('(', ')');
        translations.put('[', ']');
        translations.put('{', '}');
        translations.put('<', '>');
    }

    private void fillScores() {
        scoreMap.put(')', 1);
        scoreMap.put(']', 2);
        scoreMap.put('}', 3);
        scoreMap.put('>', 4);
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
