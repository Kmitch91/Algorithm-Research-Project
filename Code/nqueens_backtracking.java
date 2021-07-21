// Backtracking

import java.util.Scanner;

public class nqueens_backtracking {

    // Number of queens
    Scanner scan = new Scanner(System.in);
    final int N = scan.nextInt();

    // driver program
    public static void main(String args[])
    {
        long startTime = System.nanoTime();
        System.out.print("Enter number of queens: ");
        nqueens_backtracking Queen = new nqueens_backtracking();
        Queen.solveProblem();
        long endTime   = System.nanoTime();
        long totalTime = (endTime - startTime) / 1000000;
        System.out.println(totalTime + " milliseconds");
    }

    // Prints board
    void printSoln(int board[][])
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + board[i][j] + " ");
            System.out.println();
        }
    }

    // Function to make sure there is an open spot for a queen
    boolean checkBoard(int board[][], int row, int col)
    {
        int i, j;

        // Check current row on left side
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        // Check the upper diagonal on left side
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        // Check the lower diagonal on left side
        for (i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    boolean Queen(int board[][], int col)
    {
        // If all queens are placed then return true
        if (col >= N)
            return true;

        // Function checks a column and tries to place a queen in all rows one by one
        for (int i = 0; i < N; i++) {

            if (checkBoard(board, i, col)) {
                // Place this queen in board[i][col]
                board[i][col] = 1;

                // recursion to place rest of the queens
                if (Queen(board, col + 1) == true)
                    return true;

                // Backtracks
                board[i][col] = 0;
            }
        }

        // Return false if queen cannot fit in any row in the column
        return false;
    }

   // Function to solve the n queens problem
    boolean solveProblem()
    {
        // board
        int board[][] = new int [N][N];

        if (Queen(board, 0) == false) {
            System.out.print("Solution does not exist");
            return false;
        }

        printSoln(board);
        return true;
    }


}
