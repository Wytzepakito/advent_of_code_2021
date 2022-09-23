import java.io.File;
import java.io.FileNotFoundException;
import java.lang.constant.DynamicCallSiteDesc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
    private String file_name;
    private List<String> lines = new ArrayList<String>();
    private List<List<Integer>> grid = new ArrayList<>();
    private List<List<Integer>> lowPoints = new ArrayList<>();

    public Solution(String file_name) {
        this.file_name = file_name;
        read_input();
        parse_input();
    }

    private void read_input() {
        File file = new File(file_name);

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                lines.add(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void parse_input() {
        for (String line : lines) {
            List<Integer> row = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                row.add(Character.getNumericValue(line.charAt(i)));
            }
            grid.add(row);
        }
    }

    public void solveBasins() {
        List<Integer> tempPoint = new ArrayList<>();
        tempPoint.add(2);
        tempPoint.add(2);
        List<List<Integer>> dummyPoints = new ArrayList<>();
        dummyPoints.add(tempPoint);
        for (List<Integer> point: dummyPoints) {
            Basin basin = new Basin(point);
            basin.fillBasin(grid);
            System.out.println("Basin has size: " + basin.getBasinSize());
            basin.printBasin(grid);
        }
    }


    public void solve() {
        for (int y = 0; y < grid.size(); y++) {
            List<Integer> row = grid.get(y);

            for (int x = 0; x < row.size(); x++) {
                if (y == 0) {
                    if (x == 0) {
                        check(x, y, "lefttop");
                    } else if (x == row.size() - 1) {
                        check(x, y, "righttop");
                    } else {
                        check(x, y, "top");
                    }
                } else if (y == grid.size() - 1) {
                    if (x == 0) {
                        check(x, y, "leftbottom");
                    } else if (x == row.size() - 1) {
                        check(x, y, "rightbottom");
                    } else {
                        check(x, y, "bottom");
                    }
                } else if (x == 0) {
                    check(x, y, "left");
                } else if (x == row.size() - 1) {
                    check(x, y, "right");
                } else {
                    check(x, y, "normal");
                }

            }
        }


        Integer sum = 0;
        for (List<Integer> point: lowPoints) {
            System.out.println("low point:" + point + "with value" + grid.get(point.get(1)).get(point.get(0)));
            sum += grid.get(point.get(1)).get(point.get(0)) + 1;
        }
        System.out.println("Sum was: " + sum);
    }

    private void check(Integer x, Integer y, String location) {
        
        System.out.println("location: " + location);
        System.out.println("x: " + x + " y: " + y);
        Utils utils = new Utils();
        int[][] xs_ys = utils.checkHash.get(location);
        int[] xs = xs_ys[0];
        int[] ys = xs_ys[1];

        System.out.println("xs: " + Arrays.toString(xs));
        System.out.println("ys: " + Arrays.toString(ys));

        Integer value = grid.get(y).get(x);

        List<Boolean> boolArray = new ArrayList<>();

        for (int x_offset : xs) {
            Integer checkValue = grid.get(y).get(x + x_offset);
            if (checkValue > value) {
                boolArray.add(true);
            } else {
                boolArray.add(false);
            }
        }

        for (int y_offset : ys) {
            Integer checkValue = grid.get(y + y_offset).get(x);
            if (checkValue > value) {
                boolArray.add(true);
            } else {
                boolArray.add(false);
            }
        }

        if (!boolArray.contains(false)) {
            List<Integer> lowPoint = Arrays.asList(x, y);
            lowPoints.add(lowPoint);
        }
    }
}
