import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {

    private String fileName;
    private HashMap<String, List<String>> routeHash = new HashMap<>();
    public Integer numberOfPaths = 0;
    public static Set<String> partTwoPaths = new HashSet<>();

    public Solution(String fileName) {
        this.fileName = fileName;
        readInput();
    }

    void partTwoDFS(String currentNode, Map<String, Integer> nodesVisitsAmount, String currentPath,
            boolean someNodeIsAlreadyVisitedTwice) {
        currentPath += "," + currentNode;

        if (currentNode.equals("end")) {
            partTwoPaths.add(currentPath);
            System.out.println("Current path: " + currentPath);
            return;
        }

        else if (currentNode.equals(currentNode.toLowerCase())) { // If current cave is small
            nodesVisitsAmount.put(currentNode, nodesVisitsAmount.getOrDefault(currentNode, 0) + 1);
            int currentNodeVisits = nodesVisitsAmount.get(currentNode);

            // If some node has already been visited twice on the current path &&
            // amount of visits of current node is more than 2 - stop current node
            // processing
            if (currentNodeVisits >= 2 && someNodeIsAlreadyVisitedTwice) {
                return;
            }

            // Else it means that current node is the node that has been visited twice; mark
            // flag as true
            if (currentNodeVisits == 2 && !someNodeIsAlreadyVisitedTwice) {
                someNodeIsAlreadyVisitedTwice = true;
            }
        }

        for (String neighbour : routeHash.get(currentNode)) {
            // If neighbour isn't "start" node &&
            // (neighbour isn't visited or is a big cave ||
            // neighbour is a small cave and visited once and there is no any node visited
            // twice up to now) - move to this neighbour
            if (!neighbour.equals("start") &&
                    (nodesVisitsAmount.getOrDefault(neighbour, 0) == 0 ||
                            (nodesVisitsAmount.getOrDefault(neighbour, 1) == 1 && !someNodeIsAlreadyVisitedTwice))) {
                partTwoDFS(neighbour, new HashMap<>(nodesVisitsAmount), currentPath, someNodeIsAlreadyVisitedTwice);
            }
        }
    }

    public void recursiveFindPath(List<String> path, String current) {
        List<String> destinations = routeHash.get(current);
        if (current.equals("end")) {
            numberOfPaths += 1;
            return;
        } else {
            for (String destination : destinations) {
                if (destination.equals(destination.toLowerCase())) {

                    if (!path.contains(destination)) {
                        List<String> new_path = path.stream().collect(Collectors.toList());
                        new_path.add(destination);
                        recursiveFindPath(new_path, destination);
                    }
                } else {
                    List<String> new_path = path.stream().collect(Collectors.toList());
                    new_path.add(destination);
                    recursiveFindPath(new_path, destination);

                }

            }

        }
    }

    private static boolean isStringLowerCase(String myString) {

        char[] charArray = myString.toCharArray();
        for (int i = 0; i < charArray.length; i++) {

            if (!Character.isLowerCase(charArray[i])) {
                return false;
            }
        }
        return true;
    }

    private void readInput() {
        File file = new File(fileName);

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String node_1 = line.split("-")[0];
                String node_2 = line.split("-")[1];
                List<String> destinations_1 = routeHash.containsKey(node_1) ? routeHash.get(node_1) : new ArrayList<>();
                destinations_1.add(node_2);
                routeHash.put(node_1, destinations_1);
                List<String> destinations_2 = routeHash.containsKey(node_2) ? routeHash.get(node_2) : new ArrayList<>();
                destinations_2.add(node_1);
                routeHash.put(node_2, destinations_2);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (var entry : routeHash.entrySet()) {
            System.out.println(entry.getKey() + " Connects to" + entry.getValue());
        }

    }

}
