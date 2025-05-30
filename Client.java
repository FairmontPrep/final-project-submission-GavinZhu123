import java.util.ArrayList;
import java.util.Arrays;

public class Client {
    static ArrayList<ArrayList<Integer>> test_array_2 = new ArrayList<>();

    public static void main(String[] args) {
        test_array_2.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 1, 0, 0, 0, 0, 0)));
        test_array_2.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 1, 0, 0, 0, 0, 0)));
        test_array_2.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 0, 0, 0, 0, 0)));
        test_array_2.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 3, 1, 1, 1, 1, 1)));
        test_array_2.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 1, 0, 0, 0, 0, 0)));
        test_array_2.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 1, 0, 0, 0, 0, 0)));
        test_array_2.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 1, 1, 1, 1, 1, 1)));

        ArrayList<String> result = findPath(test_array_2);
        System.out.println(result);
        printPath(test_array_2, result);
    }

    public static ArrayList<String> findPath(ArrayList<ArrayList<Integer>> grid) {
        ArrayList<String> path = new ArrayList<>();
        int rows = grid.size();
        int cols = grid.get(0).size();

        int startRow = -1, startCol = -1;
        outer:
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid.get(i).get(j) == 1) {
                    startRow = i;
                    startCol = j;
                    break outer;
                }
            }
        }

        if (startRow == -1) return path;

        int i = startRow;
        while (i < rows && grid.get(i).get(startCol) == 1) {
            path.add("A[" + i + "][" + startCol + "]");
            i++;
        }

        int j = startCol - 1;
        i--;
        while (j >= 0 && grid.get(i).get(j) == 1) {
            path.add("A[" + i + "][" + j + "]");
            j--;
        }

        return path;
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
