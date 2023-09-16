public class SudokuSolver {
    private static final int gridSize = 9; //because we have a gird of 9x9
    public static void main (String[] args) {
        int[][] board = {
                {0, 0, 0, 5, 0, 0, 4, 2, 0},
                {6, 0, 0, 0, 4, 0, 0, 0, 0},
                {0, 5, 0, 0, 0, 0, 0, 0, 3},
                {0, 0, 0, 6, 8, 0, 1, 0, 0},
                {0, 0, 2, 0, 0, 0, 0, 0, 0},
                {5, 3, 0, 0, 1, 0, 7, 6, 0},
                {0, 0, 0, 0, 0, 0, 8, 7, 0},
                {7, 2, 0, 0, 0, 1, 0, 0, 9},
                {0, 9, 0, 0, 0, 3, 0, 5, 0}
        };


        if (solveTheBoard(board)) {
            System.out.println("Solved Successfully!");
        } else {
            System.out.println("Unsolvable!");
        }

        printBoard(board);
    }
        private static void printBoard(int[][] board) {
            for (int row = 0; row < gridSize; row++) {

                if (row % 3 == 0 && row != 0) {
                    System.out.println("-----------");
                }

                int column;
                for (column = 0; column < gridSize; column++) {
                    System.out.print(board[row][column]);
                }

                if (column % 3 == 0 && column != 0) {
                    System.out.println(" | ");
                }

                System.out.println();
            }
        }




    /**
     * this method will help us to see if a number exists in
     * the row so that we won't repeat it
     * @param board
     * @param number
     * @param row
     * @return
     */
    private static boolean isNumberInTheRow(int[][] board, int number, int row){
        for (int i = 0; i < gridSize; i++) {
            if (board[row][i] == number) {
                return true;
            }
        } return false;
    }

    /**
     * this method will check every column to see if the
     * number exists
     * @param board
     * @param number
     * @param column
     * @return
     */
    private static boolean isNumberInTheColumn(int[][] board, int number, int column){
        for (int i = 0; i < gridSize; i++){
            if (board[i][column] == number) {
                return true;
            }
        } return false;
    }

    private static boolean isNumberInThe3x3(int[][]board, int number, int row, int column) {
        //we need to find the leftmost top place first so:
        int rowPlace = row - row % 3;
        int columnPlace = column - column % 3;

        for (int i = rowPlace; i < rowPlace + 3; i++) {
            for (int j = columnPlace; j < columnPlace + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        } return false;
    }

    //a method that checks all three above methods
    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInTheRow(board, number, row) &&
                !isNumberInTheColumn(board, number, column) &&
                !isNumberInThe3x3(board, number, row,column);
    }
    private static boolean solveTheBoard(int[][] board) {
        for (int row = 0; row < gridSize; row++) {
            for (int column = 0; column < gridSize; column++) {
                if (board[row][column] == 0) {
                    for (int tryNumber = 1; tryNumber < gridSize; tryNumber++) {//goes through one to 9
                                                                                // to try every number in the box
                        if(isValidPlacement(board, tryNumber, row, column)) {
                            board[row][column] = tryNumber;

                            if(solveTheBoard(board)) {
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }return false;
                }
            }
        } return true;
    }




}
