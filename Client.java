import java.util.ArrayList;
import java.util.Arrays;

public class Client {
    static ArrayList<ArrayList<Integer>> test_array_2 = new ArrayList<>();
    static ArrayList<String> path = new ArrayList<>();
    static boolean[][] visited;
    static int[] start;
    static boolean pathFound = false;

    public static void main(String[] args) {
        test_array_2.add(new ArrayList<>(Arrays.asList(0, 0, 0, 1, 0, 0, 0, 0)));
        test_array_2.add(new ArrayList<>(Arrays.asList(0, 0, 0, 1, 0, 0, 0, 0)));
        test_array_2.add(new ArrayList<>(Arrays.asList(1, 1, 1, 0, 1, 0, 0, 0)));
        test_array_2.add(new ArrayList<>(Arrays.asList(0, 0, 0, 3, 1, 1, 1, 1)));
        test_array_2.add(new ArrayList<>(Arrays.asList(0, 0, 1, 1, 1, 0, 0, 0)));
        test_array_2.add(new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0, 0, 4, 0)));

        visited = new boolean[test_array_2.size()][test_array_2.get(0).size()];

        start = findStartingPoint(test_array_2);
        if (start != null) {
            dfs(test_array_2, start[0], start[1], -1, -1);
        }

        System.out.println(path);
        printPath(test_array_2, path);
    }

    public static int[] findStartingPoint(ArrayList<ArrayList<Integer>> grid) {
        int rows = grid.size();
        int cols = grid.get(0).size();

        for (int j = 0; j < cols; j++) {
            if (grid.get(0).get(j) == 1) return new int[]{0, j};
            if (grid.get(rows - 1).get(j) == 1) return new int[]{rows - 1, j};
        }
        for (int i = 0; i < rows; i++) {
            if (grid.get(i).get(0) == 1) return new int[]{i, 0};
            if (grid.get(i).get(cols - 1) == 1) return new int[]{i, cols - 1};
        }
        return null;
    }

    public static void dfs(ArrayList<ArrayList<Integer>> grid, int i, int j, int prevI, int prevJ) {
        int rows = grid.size();
        int cols = grid.get(0).size();

        if (i < 0 || j < 0 || i >= rows || j >= cols || pathFound) return;
        if (grid.get(i).get(j) != 1 || visited[i][j]) return;

        visited[i][j] = true;
        path.add("A[" + i + "][" + j + "]");

        // If we've reached a 1 on the right wall (not the starting point), stop
        if (j == cols - 1 && !(i == start[0] && j == start[1])) {
            pathFound = true;
            return;
        }

        int[][] directions = {
            {1, 0},  // down
            {-1, 0}, // up
            {0, 1},  // right
            {0, -1}  // left
        };

        for (int[] dir : directions) {
            int newI = i + dir[0];
            int newJ = j + dir[1];

            if (newI == prevI && newJ == prevJ) continue;
            if (isValidMove(grid, newI, newJ)) {
                dfs(grid, newI, newJ, i, j);
                if (pathFound) return;
            }
        }

        // Backtrack if dead end
        if (!pathFound) path.remove(path.size() - 1);
    }

    public static boolean isValidMove(ArrayList<ArrayList<Integer>> grid, int i, int j) {
        int rows = grid.size();
        int cols = grid.get(0).size();

        if (i < 0 || j < 0 || i >= rows || j >= cols) return false;
        if (grid.get(i).get(j) != 1 || visited[i][j]) return false;

        return true;
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
