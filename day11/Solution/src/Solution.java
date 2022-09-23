import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    String fileName;
    List<List<Integer>> grid = new ArrayList<>();
    Integer numberOfFlashes = 0;

    public Solution(String fileName) {
        this.fileName = fileName;
        readInput();
    }

    private void readInput() {
        File file = new File(fileName);

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                List<Integer> row = new ArrayList<>();
                for (String myChar : reader.nextLine().split("")) {
                    row.add(Integer.parseInt(myChar));
                }
                grid.add(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void takeSteps(Integer steps) {
        System.out.println("Before steps: ");
        printGrid();
        for (int i = 0; i < steps; i++) {
            doStep();
            handleExplosions();
            Integer oneUpstep = i + 1;
            System.out.println("After step " + oneUpstep + ":");
            // printGrid();
        }

        System.out.println("Number of flashes: " + numberOfFlashes);
    }

    public void getAllFlash() {
        Integer steps = 0;
        while(checkAllFlash() == false) {
            doStep();
            handleExplosions();
            steps += 1;
            System.out.println("Did step " + steps);
        }
    }

    public void doStep() {
        for (int y = 0; y < grid.size(); y++) {
            List<Integer> row = grid.get(y);
            for (int x = 0; x < row.size(); x++) {
                Integer val = grid.get(y).get(x);
                grid.get(y).set(x, val + 1);
            }
        }
    }

    private Boolean checkAllFlash() {
        Boolean all_flash = true;
        for (int y = 0; y < grid.size(); y++) {
            List<Integer> row = grid.get(y);
            for (int x = 0; x < row.size(); x++) {
                Integer val = grid.get(y).get(x);
                if (val != 0) {
                    all_flash = false;
                }
            }
        }
        return(all_flash);
    }

    public void handleExplosions() {
        Boolean newTen = true;
        while (newTen) {
            newTen = false;
            for (int y = 0; y < grid.size(); y++) {
                List<Integer> row = grid.get(y);
                for (int x = 0; x < row.size(); x++) {
                    Integer val = grid.get(y).get(x);

                    if (val == 10) {
                        numberOfFlashes++;
                        int[][] xs_ys = getExplodeOffsets(x, y);
                        int[] xs = xs_ys[0];
                        int[] ys = xs_ys[1];

                        for (int x_offset : xs) {
                            for (int y_offset : ys) {
                                Integer explodeVal = grid.get(y + y_offset).get(x + x_offset);
                                if (explodeVal != 10 && explodeVal != 0) {
                                    grid.get(y + y_offset).set(x + x_offset, explodeVal + 1);
                                }
                                if (explodeVal + 1 == 10) {
                                    newTen = true;
                                }
                            }
                        }

                        grid.get(y).set(x, 0);
                    }
                }
            }
        }

    }

    private int[][] getExplodeOffsets(Integer x, Integer y) {
        List<Integer> row = grid.get(0);

        if (x == 0) {
            if (y == 0) {
                return (Utils.checkHash.get("lefttop"));
            } else if (y == grid.size() - 1) {
                return (Utils.checkHash.get("leftbottom"));
            } else {
                return (Utils.checkHash.get("left"));
            }
        } else if (x == row.size() - 1) {
            if (y == 0) {
                return (Utils.checkHash.get("righttop"));
            } else if (y == grid.size() - 1) {
                return (Utils.checkHash.get("rightbottom"));
            } else {
                return (Utils.checkHash.get("right"));
            }
        } else if (y == 0) {
            return (Utils.checkHash.get("top"));
        } else if (y == grid.size() - 1) {
            return (Utils.checkHash.get("bottom"));
        } else {
            return (Utils.checkHash.get("normal"));
        }

    }

    public void printGrid() {
        for (List<Integer> row : grid) {

            for (Integer num : row) {
                System.out.print(num);
            }
            System.out.println();
        }
        System.out.println();
    }

}
