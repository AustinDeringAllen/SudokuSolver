import java.util.ArrayList;

public class SudokuSolver {
    public static void main(String[] args) {
        int[][] board = {
                {0, 6, 4, 0, 9, 0, 3, 7, 0},
                {8, 0, 0, 0, 0, 1, 6, 0, 0},
                {5, 0, 9, 0, 0, 0, 0, 2, 0},
                {4, 0, 0, 1, 8, 9, 7, 6, 0},
                {3, 0, 0, 0, 0, 0, 0, 0, 2},
                {0, 8, 6, 2, 5, 3, 0, 0, 4},
                {0, 3, 0, 0, 0, 0, 5, 0, 7},
                {0, 0, 7, 9, 0, 0, 0, 0, 6},
                {0, 5, 2, 0, 4, 0, 1, 3, 0},
        };

        ArrayList<Integer[]> emptyPositions = findEmpty(board);
        for(Integer[] position : emptyPositions) {
            System.out.println(position[0] + " , " + position[1]);
        }

        prettifySudoku(board);
    }

    public static ArrayList<Integer[]> findEmpty(int[][] board) {
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

    public static boolean isValid(int[][] board, Integer[] position, int number) {
        for(int i=0; i<9; i++) {
            if(board[position[0]][i] == number) {
                return false;
            }
            if(board[i][position[1]] == number) {
                return false;
            }
        }
        return true;
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