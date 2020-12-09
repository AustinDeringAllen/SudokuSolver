import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SudokuSolver {
    public static void main(String[] args) {
//        int[][] board = {
//                {0, 6, 4, 0, 9, 0, 3, 7, 0},
//                {8, 0, 0, 0, 0, 1, 6, 0, 0},
//                {5, 0, 9, 0, 0, 0, 0, 2, 0},
//                {4, 0, 0, 1, 8, 9, 7, 6, 0},
//                {3, 0, 0, 0, 0, 0, 0, 0, 2},
//                {0, 8, 6, 2, 5, 3, 0, 0, 4},
//                {0, 3, 0, 0, 0, 0, 5, 0, 7},
//                {0, 0, 7, 9, 0, 0, 0, 0, 6},
//                {0, 5, 2, 0, 4, 0, 1, 3, 0},
//        };

        int[][] board = {
                {0, 0, 5, 0, 0, 0, 9, 0, 4},
                {0, 3, 0, 6, 0, 0, 7, 0, 0},
                {1, 0, 0, 4, 0, 9, 0, 8, 0},
                {3, 0, 0, 0, 1, 0, 0, 9, 2},
                {0, 4, 1, 0, 5, 0, 6, 7, 0},
                {7, 9, 0, 0, 4, 0, 0, 0, 8},
                {0, 6, 0, 5, 0, 1, 0, 0, 7},
                {0, 0, 9, 0, 0, 2, 0, 1, 0},
                {5, 0, 3, 0, 0, 0, 2, 0, 0},
        };

//        System.out.println(0%3); // + 1
//        System.out.println(1%3); // N/A
//        System.out.println(2%3); // - 1
//
//        System.out.println(3%3); // + 1
//        System.out.println(4%3); // N/A
//        System.out.println(5%3); // - 1
//
//        System.out.println(6%3); // + 1
//        System.out.println(7%3); // N/A
//        System.out.println(8%3); // - 1

//        Integer[] position = {3,2};
//
//        System.out.println(checkRow(board, position, 1, new ArrayList<>()));
//        System.out.println(checkColumn(board, position, 1, new ArrayList<>()));
//        System.out.println(checkGrid(board, position, 2, new ArrayList<>()));

        solve(board);
    }

    public static void solve(int[][] board) {
        ArrayList<Integer[]> allEmptyPositions = findAllEmpty(board);
        prettifySudoku(board);
    }

    public static boolean checkRow(int[][] board, Integer[] currentPosition, int number, ArrayList<Integer> impossibleNumbers) {
        for(int i=0; i<board.length; i++) {
            if(board[currentPosition[0]][i] == number)
                return false;
        }
        return true;
    }

    public static boolean checkColumn(int[][] board, Integer[] currentPosition, int number, ArrayList<Integer> impossibleNumbers) {
        for(int i=0; i<board.length; i++) {
            if(board[i][currentPosition[1]] == number)
                return false;
        }
        return true;
    }

    public static int[] findEmpty(int[][] board) {
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board.length; j++) {
                if(board[i][j] == 0) {
                    return new int[] {i,j};
                }
            }
        }
        return null;
    }

    public static ArrayList<Integer[]> findAllEmpty(int[][] board) {
        ArrayList<Integer[]> emptyPositions = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    emptyPositions.add(new Integer[]{i, j});
                }
            }
        }
        return emptyPositions;
    }

    public static void prettifySudoku(int[][] board) {
        System.out.println("-----------");
        for(int[] row : board) {
            for(int i=0; i<row.length; i++) {
                if (i == row.length - 1)
                    System.out.print(row[i] + "\n");
                else
                    System.out.print(row[i] + " , ");
            }
        }
    }
}