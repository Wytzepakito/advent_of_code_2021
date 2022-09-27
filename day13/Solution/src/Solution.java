import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Solution {
    private String fileName;
    private HashSet<List<Integer>> grid = new HashSet<>();
    private List<String> folds = new ArrayList<>();

    public Solution(String fileName) {
        this.fileName = fileName;
        readInput();
    }

    public Integer getLengthPoints() {
        return(grid.size());
    }

    public void doNFolds(Integer n) {
        System.out.println("Before folding: ");
        for (List<Integer> point: grid) {
            System.out.println(point);
        }
        for (int i = 0; i < n; i++) {
            String fold = folds.get(i);
            System.out.println(fold);
            String direction = fold.split("=")[0];
            Integer axis = Integer.parseInt(fold.split("=")[1]);

            if (direction.equals("y")) {
                doVerticalFold(axis);
            } else {

            }
    }
        System.out.println("After folding: ");
        for (List<Integer> point: grid) {
            System.out.println(point);
        }
}

    private void doVerticalFold(Integer axis) {
        HashSet<List<Integer>> newGrid = new HashSet<>();

        for (List<Integer> point: grid) {
            if (point.get(1) >= axis) {
                point.set(1, 2 * axis - point.get(1));
            } else {
                newGrid.add(point);
            }
        }

        grid = newGrid;

    }





    private void readInput() {
        File file = new File(fileName);

        try( Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.startsWith("fold along")) {
                    folds.add(line.split(" ")[2]);
                } else if(line.length() == 0 ){
                    // pass
                } else if (Character.isDigit(line.charAt(0))) {

                    List<Integer> point = new ArrayList<>();
                    point.add(Integer.parseInt(line.split(",")[0]));
                    point.add(Integer.parseInt(line.split(",")[1]));
                    grid.add(point);
                } 

            }
        } catch ( FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
