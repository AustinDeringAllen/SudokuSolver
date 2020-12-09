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

        solve(board);
    }

    public static void solve(int[][] board) {
        ArrayList<Integer[]> allEmpty = findAllEmpty(board);
        findValid(board, allEmpty);
    }

    public static void findValid(int[][] board, ArrayList<Integer[]> allEmpty) {
        for(int i=0; i<allEmpty.size(); i++) {
            for(int j=0; j<board.length; j++) {
                if(isValid(board, allEmpty.get(i), j))
                    board[allEmpty.get(i)[0]][allEmpty.get(i)[1]] = j;
            }
        }
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

    public static boolean isValid(int[][] board, Integer[] position, int number, int[] oldNumbers) {
        // Pass in an array of integers for old number;
        for(int i=0; i<board.length; i++) {
            if(i == oldNumbers[i])
                continue;

            if(board[position[0]][i] == number)
                return false;
            if(board[i][position[1]] == number)
                return false;
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