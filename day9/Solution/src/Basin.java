import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Basin {
    private List<List<Integer>> points = new ArrayList<>();
    private List<Integer> checked = new ArrayList<>();

    public Basin(List<Integer> lowestPoint) {
        points.add(lowestPoint);
    }

    public void fillBasin(List<List<Integer>> grid) {

        while (checked.size() != points.size()) {
            Integer index = getFirstUnchecked();
            List<Integer> currentPoint = points.get(index);
            String pointsString = getLocationString(currentPoint, grid);
            List<List<Integer>> pointsToCheck = getPointsToCheck(currentPoint, pointsString);
            List<List<Integer>> pointsToCheckFiltered = removePointsInBasin(pointsToCheck);
            Integer currentPointValue = grid.get(currentPoint.get(1)).get(currentPoint.get(0));
            addPointsToBasin(pointsToCheckFiltered, grid, currentPointValue);
            checked.add(index);
            
        }


    }

    public void printBasin(List<List<Integer>> grid) {
        
        for (int y = 0; y < grid.size(); y++) {
            List<Integer> row = grid.get(y);
            for (int x = 0; x < row.size(); x++) {
                List<Integer> printPoint = Arrays.asList(x, y);
                Integer value = grid.get(y).get(x);
                Boolean printColor = false;
                for (List<Integer> point: points) {
                    if (point.get(0) == x && point.get(1) == y) {
                        printColor = true;
                    }
                }
                if (printColor) {
                    System.out.print(ConsoleColors.GREEN_BOLD + value );
                } else {
                    System.out.print(ConsoleColors.RESET + value);
                }
            }
            System.out.println();
        }
                
    }

    private void addPointsToBasin(List<List<Integer>> pointsToCheck, List<List<Integer>> grid, Integer currentPointValue) {

        for (List<Integer> point: pointsToCheck) {
             Integer pointValue = grid.get(point.get(1)).get(point.get(0));

             if (pointValue < 9) {
                if (pointValue > currentPointValue) {
                    points.add(point);
                }
            }
        }
    }

    private List<List<Integer>> removePointsInBasin(List<List<Integer>> pointsToCheck) {
        List<List<Integer>> newPoints = new ArrayList<>();

        for (List<Integer> pointToCheck: pointsToCheck) {
            Boolean inBasin = false;
            for (List<Integer> pointInBasin: points) {
                if (pointToCheck.get(0) == pointInBasin.get(0) && pointToCheck.get(1) == pointInBasin.get(1)) {
                    inBasin = true;
                }
            }
            if (!inBasin) {
                newPoints.add(pointToCheck);
            }
        }
        return(newPoints);
    }

    private List<List<Integer>> getPointsToCheck(List<Integer> point, String pointsString) {
        Utils utils = new Utils();
        int[][] xs_ys = utils.checkHash.get(pointsString);
        int[] xs = xs_ys[0];
        int[] ys = xs_ys[1];
        List<List<Integer>> pointsToCheck = new ArrayList<>();
        for (int x_offset : xs) {
            List<Integer> newPoint = Arrays.asList(point.get(0) + x_offset, point.get(1));
            pointsToCheck.add(newPoint);
        }

        for (int y_offset : ys) {
            List<Integer> newPoint = Arrays.asList(point.get(0), point.get(1) + y_offset);
            pointsToCheck.add(newPoint);
            }
        return(pointsToCheck);
        }

    private String getLocationString(List<Integer> point, List<List<Integer>> grid) {
        List<Integer> row = grid.get(0);
        if (point.get(0) == 0) {
            if (point.get(1) == 0) {
                return ("lefttop");
            } else if (point.get(1) == grid.size() - 1) {
                return ("leftbottom");
            } else {
                return ("left");
            }
        } else if (point.get(0) == row.size() - 1) {
            if (point.get(1) == 0) {
                return ("righttop");
            } else if (point.get(1) == grid.size() - 1) {
                return ("rightbottom");
            } else {
                return ("right");
            }
        } else if (point.get(1) == 0) {
            return ("top");
        } else if (point.get(1) == grid.size() - 1) {
            return ("bottom");
        } else {
            return ("normal");
        }
    }

    private Integer getFirstUnchecked() {
        if (checked.size() == 0) {
            return 0;
        }
        for (int i = 0; i < points.size(); i++) {
            if (i >= checked.size()) {
                return i;
            }
        }
        return null;
    }
    public Integer getBasinSize() {
        return points.size();
    }
}
