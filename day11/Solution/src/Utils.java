import java.util.HashMap;

public class Utils {


    
    public static HashMap<String, int[][]> checkHash = new HashMap<>();


    static {
        checkHash.put("normal", new int[][] { { -1, 1, 0 }, { -1, 1, 0 } });
        checkHash.put("lefttop", new int[][] { { 1, 0 }, { 1, 0 } });
        checkHash.put("top", new int[][] { { -1, 1, 0 }, { 1, 0 } });
        checkHash.put("righttop", new int[][] { { -1, 0 }, { 1, 0 } });
        checkHash.put("left", new int[][] { { 1, 0 }, { -1, 1, 0 } });
        checkHash.put("leftbottom", new int[][] { { 1, 0 }, { -1, 0 } });
        checkHash.put("bottom", new int[][] { { -1, 1, 0 }, { -1, 0 } });
        checkHash.put("rightbottom", new int[][] { { -1, 0 }, { -1, 0 } });
        checkHash.put("right", new int[][] { { -1, 0 }, { -1, 1, 0 } });
    }
}