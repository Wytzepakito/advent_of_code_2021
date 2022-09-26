import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution("input4.txt");
        String start = "start";
        HashMap<String, Integer> startHash = new HashMap<>();
        startHash.put("visitedSmallTwice", 0);
        solution.partTwoDFS(start, startHash, "", false);
        System.out.println("Found; " + solution.partTwoPaths.size() + " number of paths.");
    }
}
