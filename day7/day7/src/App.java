import solution.Solution;

public class App {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.loadPositions("input2.txt");
        solution.solve();
    }
}
