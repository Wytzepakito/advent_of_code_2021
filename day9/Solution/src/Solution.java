import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {
    private String file_name;
    private List<String> lines = new ArrayList<String>();
    private List<List<Integer>> grid = new ArrayList<>();
    private HashMap<String, int[][]> checkHash = new HashMap<>();

    public Solution(String file_name) {
        this.file_name = file_name;
        read_input();
        parse_input();
        makeOffsetHash();
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

    private void makeOffsetHash() {
        // left side is x, right side is y
        checkHash.put("normal", new int[][] { { -1, 1 }, { -1, 1 } });
        checkHash.put("lefttop", new int[][] { { 1 }, { 1 } });
        checkHash.put("top", new int[][] { { -1, 1 }, { 1 } });
        checkHash.put("righttop", new int[][] { { -1 }, { 1 } });
        checkHash.put("left", new int[][] { { 1 }, { -1, 1 } });
        checkHash.put("leftbottom", new int[][] { { 1 }, { -1 } });
        checkHash.put("bottom", new int[][] { { -1, 1 }, { -1 } });
        checkHash.put("rightbottom", new int[][] { { -1 }, { -1 } });
        checkHash.put("right", new int[][] { { -1 }, { -1, 1 } });
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
                        check(x, y, "upper");
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
                    check(x, y, "right");
                } else if (x == row.size() - 1) {
                    check(x, y, "left");
                } else {
                    check(x, y, "normal");
                }

            }
        }
    }

    private void check(Integer x, Integer y, String location) {

        int[][] xs_ys = checkHash.get(location);
        int[] xs = xs_ys[0];
        int[] ys = xs_ys[1];

        Integer value = grid.get(y).get(x);

        List<Boolean> boolArray = new ArrayList<>();

        for (int x_offset : xs) {
            Integer checkValue = grid.get(y).get(x - x_offset);
            if (checkValue < value) {
                boolArray.add(true);
            } else {
                boolArray.add(false);
            }
        }

        for (int y_offset : ys) {
            Integer checkValue = grid.get(y - y_offset).get(x);
            if (checkValue < value) {
                boolArray.add(true);
            } else {
                boolArray.add(false);
            }
        }
    }
}
