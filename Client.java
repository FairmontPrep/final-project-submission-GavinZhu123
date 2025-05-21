import java.util.ArrayList;
import java.util.Arrays;

public class Client {
    static ArrayList<ArrayList<Integer>> test_array_2 = new ArrayList<>();

    public static void main(String[] args) {
        test_array_2.add(new ArrayList<>(Arrays.asList(1, 0, 0, 1, 0, 0, 0, 0, 0, 0)));
        test_array_2.add(new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0, 0, 0, 0, 0, 0)));
        test_array_2.add(new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0, 0, 1, 0, 0, 0)));
        test_array_2.add(new ArrayList<>(Arrays.asList(9, 0, 0, 1, 0, 0, 0, 0, 0, 0)));
        test_array_2.add(new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0, 0, 0, 0, 0, 0)));
        test_array_2.add(new ArrayList<>(Arrays.asList(0, 0, 1, 2, 0, 0, 0, 0, 0, 0)));
        test_array_2.add(new ArrayList<>(Arrays.asList(1, 0, 0, 1, 1, 1, 1, 1, 1, 1)));

        ArrayList<String> result = findPath(test_array_2);
        System.out.println(result);
        printPath(test_array_2, result);
    }

    public static ArrayList<String> findPath(ArrayList<ArrayList<Integer>> grid) {
        ArrayList<String> path = new ArrayList<>();
        int rows = grid.size();
        int cols = grid.get(0).size();
        boolean[][] visited = new boolean[rows][cols];
        int[] start = null;

        outer:
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid.get(i).get(j) == 1) {
                    start = new int[]{i, j};
                    break outer;
                }
            }
        }

        if (start != null) {
            dfs(grid, visited, path, start[0], start[1]);
        }
        return path;
    }

    public static boolean dfs(ArrayList<ArrayList<Integer>> grid, boolean[][] visited, ArrayList<String> path, int i, int j) {
        int rows = grid.size();
        int cols = grid.get(0).size();

        if (i < 0 || j < 0 || i >= rows || j >= cols || grid.get(i).get(j) != 1 || visited[i][j]) {
            return false;
        }

        visited[i][j] = true;
        path.add("A[" + i + "][" + j + "]");

        if (i == rows - 1 && j == cols - 1) {
            return true;
        }

        if (dfs(grid, visited, path, i + 1, j)) return true;
        if (dfs(grid, visited, path, i, j + 1)) return true;
        if (dfs(grid, visited, path, i - 1, j)) return true;
        if (dfs(grid, visited, path, i, j - 1)) return true;

        path.remove(path.size() - 1);
        return false;
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
