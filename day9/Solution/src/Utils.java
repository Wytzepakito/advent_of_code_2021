import java.util.HashMap;

public class Utils {


    
    public static HashMap<String, int[][]> checkHash = new HashMap<>();


    public Utils() {
        makeOffsetHash();
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
}