import java.util.ArrayList;
import java.util.Arrays;

public class Client {
    static ArrayList<ArrayList<Integer>> test_array_2 = new ArrayList<>();
    static ArrayList<String> path = new ArrayList<>();
    static boolean[][] visited;
    static boolean pathFound = false;
    static String startingWall = "";

    public static void main(String[] args) {
        test_array_2.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0)));
        test_array_2.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0)));
        test_array_2.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 0, 0, 0)));
        test_array_2.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 1, 1, 1, 1)));
        test_array_2.add(new ArrayList<>(Arrays.asList(0, 0, 1, 1, 1, 0, 0, 0)));
        test_array_2.add(new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0, 4, 0, 0)));

        visited = new boolean[test_array_2.size()][test_array_2.get(0).size()];

        int[] start = findStartingPoint(test_array_2);
        if (start != null) {
            setStartingWall(start[0], start[1], test_array_2.size(), test_array_2.get(0).size());
            dfs(test_array_2, start[0], start[1]);
        }

        System.out.println(path);
        printPath(test_array_2, path);
    }

    public static int[] findStartingPoint(ArrayList<ArrayList<Integer>> grid) {
        int rows = grid.size();
        int cols = grid.get(0).size();

        for (int j = 0; j < cols; j++) {
            if (grid.get(0).get(j) == 1) return new int[]{0, j};               // top
            if (grid.get(rows - 1).get(j) == 1) return new int[]{rows - 1, j}; // bottom
        }
        for (int i = 0; i < rows; i++) {
            if (grid.get(i).get(0) == 1) return new int[]{i, 0};               // left
            if (grid.get(i).get(cols - 1) == 1) return new int[]{i, cols - 1}; // right
        }
        return null;
    }

    public static void setStartingWall(int i, int j, int rows, int cols) {
        if (i == 0) startingWall = "top";
        else if (i == rows - 1) startingWall = "bottom";
        else if (j == 0) startingWall = "left";
        else if (j == cols - 1) startingWall = "right";
    }

    public static void dfs(ArrayList<ArrayList<Integer>> grid, int i, int j) {
        if (pathFound) return;

        int rows = grid.size();
        int cols = grid.get(0).size();

        if (i < 0 || j < 0 || i >= rows || j >= cols) return;
        if (grid.get(i).get(j) != 1 || visited[i][j]) return;

        visited[i][j] = true;
        path.add("A[" + i + "][" + j + "]");

        if (isInvalidEnd(i, j, rows, cols)) {
            path.remove(path.size() - 1);
            visited[i][j] = false;
            return;
        }

        if (isValidEnd(i, j, rows, cols)) {
            pathFound = true;
            return;
        }

        dfs(grid, i + 1, j); // down
        dfs(grid, i - 1, j); // up
        dfs(grid, i, j + 1); // right
        dfs(grid, i, j - 1); // left

        if (!pathFound) {
            path.remove(path.size() - 1);
            visited[i][j] = false;
        }
    }

    public static boolean isValidEnd(int i, int j, int rows, int cols) {
        if (startingWall.equals("left") && i == rows - 1) return true;
        if (startingWall.equals("top") && j == 0) return true;
        if (startingWall.equals("right") && i == rows - 1) return true;
        if (startingWall.equals("bottom") && j == 0) return true;
        return false;
    }

    public static boolean isInvalidEnd(int i, int j, int rows, int cols) {
        return (startingWall.equals("top") && i == rows - 1) ||
               (startingWall.equals("bottom") && i == 0) ||
               (startingWall.equals("left") && j == cols - 1) ||
               (startingWall.equals("right") && j == 0);
    }

    public static void printPath(ArrayList<ArrayList<Integer>> grid, ArrayList<String> path) {
        int rows = grid.size();
        int cols = grid.get(0).size();
        int[][] out = new int[rows][cols];

        for (String coord : path) {
            int r = Integer.parseInt(coord.substring(coord.indexOf("[") + 1, coord.indexOf("]")));
            int c = Integer.parseInt(coord.substring(coord.lastIndexOf("[") + 1, coord.lastIndexOf("]")));
            out[r][c] = 1;
        }

        for (int[] row : out) {
            System.out.println(Arrays.toString(row));
        }
    }
}
