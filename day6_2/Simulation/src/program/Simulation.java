package program;





import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Simulation {

    
    private List<Integer> fishes = new ArrayList<>();
    private HashMap<Integer, Long> fishMap = new HashMap<Integer, Long>();

    public void initList() {
        for (int i = 0; i < 9; i++) {
            fishMap.put(i, (long) 0);
        }
        File file = new File("input2.txt");
        try (Scanner reader = new Scanner(file)) {
            String line  = reader.nextLine();
            String[] parts = line.split(",");

            
            for (String part: parts) {
                if (fishMap.containsKey(Integer.parseInt(part))) {
                    Long current = fishMap.get(Integer.parseInt(part));
                    fishMap.put(Integer.parseInt(part), current + (long) 1);
                }
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void runSimulation(int cycles) {
        
        for(int i = 0; i < cycles; i++) {

            HashMap<Integer, Long> new_hash = new HashMap<Integer, Long>();

            for (int j = 8; j > -1; j--) {

                Integer fishAge = j;
                Long numberFishes = fishMap.get(j);

                if (fishAge != 0) {
                    new_hash.put(fishAge - 1, numberFishes);
                } else {
                    Long current_6 = new_hash.get(6);
                    new_hash.put(6, current_6 + numberFishes);
                    new_hash.put(8, numberFishes);
                }
            }
                
            fishMap = new_hash;

        }

        Long total = (long) 0;
        for (Long ageGroup: fishMap.values()) {
            total += ageGroup;
        }
        System.out.println("The total number of fishes is: " + total);
    }

    
}
