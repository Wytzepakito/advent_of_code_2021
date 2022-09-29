public class App {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution("input2.txt");
        
        System.out.println("After 1 fold there were: " + solution.getLengthPoints() + " points left.");
        solution.doAllFolds();
        System.out.println("After 1 fold there were: " + solution.getLengthPoints() + " points left.");
    }
}
