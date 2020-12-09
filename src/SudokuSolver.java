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

//        int[][] board = {
//                {0, 0, 5, 0, 0, 0, 9, 0, 4},
//                {0, 3, 0, 6, 0, 0, 7, 0, 0},
//                {1, 0, 0, 4, 0, 9, 0, 8, 0},
//                {3, 0, 0, 0, 1, 0, 0, 9, 2},
//                {0, 4, 1, 0, 5, 0, 6, 7, 0},
//                {7, 9, 0, 0, 4, 0, 0, 0, 8},
//                {0, 6, 0, 5, 0, 1, 0, 0, 7},
//                {0, 0, 9, 0, 0, 2, 0, 1, 0},
//                {5, 0, 3, 0, 0, 0, 2, 0, 0},
//        };

//        int[][] board = {
//                {6, 1, 0, 7, 2, 0, 0, 5, 0},
//                {0, 0, 0, 0, 0, 5, 1, 9, 7},
//                {3, 7, 0, 0, 0, 1, 2, 0, 0},
//                {0, 0, 0, 0, 0, 9, 4, 7, 0},
//                {7, 0, 1, 0, 0, 0, 9, 0, 5},
//                {0, 4, 8, 6, 0, 0, 0, 0, 0},
//                {0, 0, 7, 4, 0, 0, 0, 1, 9},
//                {4, 9, 6, 5, 0, 0, 0, 0, 0},
//                {0, 2, 0, 0, 7, 6, 0, 4, 8},
//        };

        int[][] board = {
                {0, 0, 6, 0, 0, 5, 3, 0, 2},
                {0, 0, 0, 0, 0, 4, 7, 0, 0},
                {9, 0, 0, 3, 0, 0, 8, 6, 0},
                {0, 0, 0, 0, 0, 3, 0, 7, 0},
                {0, 0, 3, 0, 0, 0, 5, 0, 0},
                {0, 7, 0, 5, 0, 0, 0, 0, 0},
                {0, 2, 4, 0, 0, 7, 0, 0, 9},
                {0, 0, 1, 9, 0, 0, 0, 0, 0},
                {3, 0, 9, 8, 0, 0, 2, 0, 0},
        };

        solve(board);
    }

    public static void solve(int[][] board) {
        ArrayList<Integer[]> allEmptyPositions = findAllEmpty(board);
        HashMap<Integer, ArrayList<Integer>> impossibleNumbers = new HashMap<>();
        for(int i=0; i<allEmptyPositions.size(); i++) {
            impossibleNumbers.putIfAbsent(i, new ArrayList<>());
        }

        int key = 0;
        while(true) {
            System.out.println(key);
            Integer[] currentPosition = allEmptyPositions.get(key);
            int nextMove = findValidMove(board, currentPosition, impossibleNumbers.get(key));

            if(nextMove != -1) {
                key++;
                board[currentPosition[0]][currentPosition[1]] = nextMove;
            } else {
                impossibleNumbers.get(key).clear();
                key--;
                Integer[] previousPosition = allEmptyPositions.get(key);
                impossibleNumbers.get(key).add(board[previousPosition[0]][previousPosition[1]]);
                board[previousPosition[0]][previousPosition[1]] = 0;
            }

            if(key == allEmptyPositions.size())
                break;
        }

        prettifySudoku(board);
    }

    public static int findValidMove(int[][] board, Integer[] currentPosition, ArrayList<Integer> impossibleNumbers) {
        for(int number=1; number<=board.length; number++) {
            if(isValidMove(board, currentPosition, number, impossibleNumbers))
                return number;
        }

        return -1;
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

    public static boolean checkGrid(int[][] board, Integer[] currentPosition, int number, ArrayList<Integer> impossibleNumbers) {
        Integer[] newPosition = {currentPosition[0],currentPosition[1]};
        for(int i=0; i<newPosition.length; i++) {
            switch(newPosition[i] % 3) {
                case 0:
                    newPosition[i] += 1;
                    break;
                case 2:
                    newPosition[i] -= 1;
                    break;
            }
        }

        for(int i=newPosition[0]-1; i<=newPosition[0]+1; i++) {
            for(int j=newPosition[1]-1; j<=newPosition[1]+1; j++) {
                if(board[i][j] == number)
                    return false;
            }
        }
        return true;
    }

    public static boolean isValidMove(int[][] board, Integer[] currentPosition, int number, ArrayList<Integer> impossibleNumbers) {
        boolean row, column, grid;

        if(impossibleNumbers.contains(number))
            return false;

        row = checkRow(board, currentPosition, number, impossibleNumbers);
        column = checkColumn(board, currentPosition, number, impossibleNumbers);
        System.out.println("----------");
        grid = checkGrid(board, currentPosition, number, impossibleNumbers);

        return row && grid && column;
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