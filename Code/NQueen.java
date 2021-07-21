import java.util.Scanner; 
import java.util.Arrays; 
import java.util.concurrent.TimeUnit;

public class NQueen { 
	public static void main(String[] args) {   
		//long startTime = System.currentTimeMillis();
		Scanner scan = new Scanner(System.in); 
		System.out.print("Enter Size of Board, nxn: "); 
		int n = scan.nextInt();  
		scan.close();
		//System.out.println("Board is Size: "+n); 
		long startTime = System.currentTimeMillis();
		int[][] board = new int[n][n];
		int[][] rightDiagnol = new int[n][n];
		int[][] leftDiagnol = new int[n][n]; 	
		boolean[] rowCheck = new boolean[n]; 
		//Arrays.fill(rowLookUp, false);
		
		boolean[] rightDiagnolCheck = new boolean[2*n-1];  
		boolean[] leftDiagnolCheck = new boolean[2*n-1];  
		
		/*generates the slash matrixes, every queen placed has a forward slash lane of attack and a backslash...
		...lane of attack, these matrixes are made in such a way so that any two queens sharing the same diagnol  
		will have the same value, this allows for a quick isSafe check*/
		for (int r=0; r<n;r++) { 
			for (int c=0; c<n;c++) { 
				rightDiagnol[r][c]=r+c;  
				leftDiagnol[r][c]=r-c+(n-1);
			}
		} 
		/*
		printSol(slash); 
		System.out.println(); 
		printSol(backSlash);*/ 
		
	
		if (solveNQueens(board, 0, rightDiagnol,leftDiagnol,rowCheck, 
				rightDiagnolCheck, leftDiagnolCheck,n)==false) { 
			System.out.println("Solution does not exist"); 			
		} 
		
		printSolution(board); 
		
		long endTime = System.currentTimeMillis(); 
        long timeElapsed = endTime - startTime;  
        
       // printSolution(rightDiagnol); 
         
        //printSolution(leftDiagnol);
        
        System.out.println();
        System.out.println("Execution Time: "+timeElapsed+" milliseconds");
	}  
	
	
	
	public static void printSolution(int[][] board) { 
		for (int i=0; i<board.length;i++) { 
			System.out.println();
				for (int j=0;j<board.length;j++) { 
					System.out.printf("%2d ", board[i][j]);
				}
		} 
		System.out.println();
		
	} 
	
	//checks if queen placement is safe, in O(1) using optimized boolean arrays 
	public static boolean isSafe(int row, int col, int[][] rightdiagnol, int[][] leftDiagnol, 
			boolean[] rowCheck, boolean[] rightDiagnolCheck, boolean[] leftDiagnolCheck) {  
		
		if(rightDiagnolCheck[rightdiagnol[row][col]] || leftDiagnolCheck[leftDiagnol[row][col]] || rowCheck[row]) { 
			return false;			
		}
		else 
			return true;
		
	}  
	
	public static boolean solveNQueens(int[][] board, int col, int[][] rightdiagnol, int[][] leftDiagnol 
											,boolean[] rowCheck, boolean[] rightDiagnolCheck, boolean[] leftDiagnolCheck, int N) { 
		
		if (col>=N) { 
			return true;
		} 
		
		for (int k=0; k<N;k++) { 
			 /* Check if queen can be placed on
	           board[i][col] */ 
			if (isSafe(k, col, rightdiagnol, leftDiagnol, 
					rowCheck, rightDiagnolCheck, leftDiagnolCheck)) { 
				board[k][col] = 1; 
				rowCheck[k]=true; 
				rightDiagnolCheck[rightdiagnol[k][col]]=true;  
				leftDiagnolCheck[leftDiagnol[k][col]]=true;  
					if (solveNQueens(board, col+1, rightdiagnol, leftDiagnol, 
							rowCheck, rightDiagnolCheck, leftDiagnolCheck, N)) { 
						return true;
					}  
					
			board[k][col]=0; 
			rowCheck[k]=false;  
			rightDiagnolCheck[rightdiagnol[k][col]]=false; 
			leftDiagnolCheck[leftDiagnol[k][col]]=false; 
			
			
				
				
				
			}
		}
		return false;
	}
	
	

}
