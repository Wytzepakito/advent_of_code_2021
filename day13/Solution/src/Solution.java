import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Solution {
    private String fileName;
    private HashSet<List<Integer>> grid = new HashSet<>();
    private List<String> folds = new ArrayList<>();
    private Integer maxX = Integer.MIN_VALUE;
    private Integer maxY = Integer.MIN_VALUE;

    public Solution(String fileName) {
        this.fileName = fileName;
        readInput();
    }

    public Integer getLengthPoints() {
        return(grid.size());
    }
    public void doAllFolds() {
        for (int i = 0; i < folds.size(); i++) {
            String fold = folds.get(i);
            System.out.println(fold);
            String direction = fold.split("=")[0];
            Integer axis = Integer.parseInt(fold.split("=")[1]);

            if (direction.equals("y")) {
                doVerticalFold(axis);
                maxY = axis;
            } else {
                doHorizontalFold(axis);
                maxX = axis;
            }

    }
    printGrid();
    System.out.println();
}   

    public void printGrid() {

        for (int y = 0; y < maxY; y++) {

            for (int x = 0; x < maxX; x++) {
                List<Integer> point = Arrays.asList(x, y);
                if (grid.contains(point)) {
                    System.out.print("# ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public void doNFolds(Integer n) {
        for (int i = 0; i < n; i++) {
            String fold = folds.get(i);
            System.out.println(fold);
            String direction = fold.split("=")[0];
            Integer axis = Integer.parseInt(fold.split("=")[1]);

            if (direction.equals("y")) {
                doVerticalFold(axis);
            } else {
                doHorizontalFold(axis);
            }
    }
}

    private void doHorizontalFold(Integer axis) {
        HashSet<List<Integer>> newGrid = new HashSet<>();

        for (List<Integer> point: grid) {
            if (point.get(0) >= axis) {
                point.set(0, 2 * axis - point.get(0));
            }
            newGrid.add(point);
        }
        grid = newGrid;
    }

    private void doVerticalFold(Integer axis) {
        HashSet<List<Integer>> newGrid = new HashSet<>();

        for (List<Integer> point: grid) {
            if (point.get(1) >= axis) {
                point.set(1, 2 * axis - point.get(1));
            } 
            newGrid.add(point);
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
                    Integer x = Integer.parseInt(line.split(",")[0]);
                    Integer y = Integer.parseInt(line.split(",")[1]);
                    if (x > maxX) {
                        maxX = x + 5;
                    }
                    if (y > maxY) {
                        maxY = y + 5;
                    }
                    point.add(x);
                    point.add(y);


                    grid.add(point);
                } 

            }
        } catch ( FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
