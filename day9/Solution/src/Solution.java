import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {
    private String file_name;
    private List<String> lines = new ArrayList<String>();
    private List<List<Integer>> grid = new ArrayList<>();
    public Solution(String file_name) {
        this.file_name = file_name;
        read_input();
        parse_input();
    }

    private void read_input() {
       File file = new File(file_name);
       
       try (Scanner reader = new Scanner(file)) {
        while ( reader.hasNextLine()) {
            lines.add(reader.nextLine());
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    }

    private void parse_input() {
        for (String line: lines) {
            List<Integer> row = line.chars().mapToObj(c -> (Integer) c).collect(Collectors.toList());
            grid.add(row);
        }
    }
    public void solve() {
        for ( List<Integer> row: grid) {
            System.out.println("Row was: " + row);
        }
    }

}