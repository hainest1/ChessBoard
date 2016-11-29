/*
	Author: Thomas Haines
	Course: CSC260.002
	Date: 11/26/2016
	Assignment: #9
	Instructor: Fox
*/

/*
	Description: 
	Creates a chessboard in the form of a 2D array of ChessPiece objects. 
	Loops until the user chooses to exit, allowing for certain legal moves
	to be performed. Makes use of the mutators and accessors in the chessPiece
	class to maneuver the board based on user input
	Legal moves are determined both by checking the range of input and by
	verifying the start location is occupied and the end location is null. 
	The game will save and load to and from disk automatically, the default
	file is chessboard.txt. 

*/

import java.util.Scanner;
import java.io.*;

public class ChessGame 
{
	public static void main(String[] args) throws IOException
	{
		ChessPiece[][] chessBoard = new ChessPiece[8][8];	//2D array of ChessPiece objects to represent the board
		Scanner in = new Scanner(System.in);
		int startRow, startCol, endRow, endCol;	//Start, end locations
		
		//Set up the board and start the game
		inputFromDisk(chessBoard);
		System.out.println("Game Start. Input values are 1-8, Enter 0 for starting row to quit.");
		outputChessBoard(chessBoard);
		startRow = inputMove(in, "Starting", "row");	//Input move
		
		//Loop until the player chooses to save & exit
		while (startRow >= 1)
		{
			startCol = inputMove(in, "Starting", "col");
			endRow = inputMove(in, "Ending", "row");
			endCol = inputMove(in, "Ending", "col");
			
			move(startRow, startCol, endRow, endCol, chessBoard);	//Perform the move based on given input
			outputChessBoard(chessBoard);
			
			startRow = inputMove(in, "Starting", "row");	//Input move to start next turn and check for 0 (quit)
			
		}
		
		saveToDisk(chessBoard);	//Save the updated board to chessboard.txt
		System.out.println("Board saved to chessboard.txt");
		
	}
	
	//Takes data from a formatted (saved) file and recreates the game state
	public static void inputFromDisk(ChessPiece[][] board) throws IOException
	{
		Scanner input = new Scanner(new File("chessboard.txt"));	//File scanner
		
		//Loop until no more data is available
		while (input.hasNext())
		{
			//Input each item in order
			String color = input.next();
			String type = input.next();
			int row = Integer.parseInt(input.next());
			int col = Integer.parseInt(input.next());
			
			board[row][col] = new ChessPiece(color, type, row, col);	//Create a chesspiece object based on file input
			
		}
		input.close();	//Close the scanner
		
	}
	
	//Takes input from the user to use for a move
	public static int inputMove(Scanner in, String order, String dimension)
	{
		System.out.print("" + order + " " + dimension + ": ");
		int value = in.nextInt();
		
		//Data verify the input, value can be 0 for "Starting row" to exit
		if ((value >= 0 && value <= 8 && order.equals("Starting") && dimension.equals("row")) || (value >= 1 && value <= 8))
			return value;
		else return 1;	//returns 1 if input is invalid
		
	}
	
	//Performs a move based on the given start and end coordinates
	public static void move(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board)
	{
		//Subtract 1 from input to match array indices
		startRow -= 1;
		startCol -= 1;
		endRow -= 1;
		endCol -= 1;
		
		//Check that the end space is empty and that a piece exists at the start space
		if(board[startRow][startCol]!= null && board[endRow][endCol] == null) 
		{
			//Perform the move
			board[endRow][endCol] = board[startRow][startCol];
			board[endRow][endCol].setRow(endRow);
			board[endRow][endCol].setColumn(endCol);
			board[startRow][startCol] = null;
		
		}
		else System.out.println("Illegal move. Turn forfeited.");
		
	}
	
	//Output a nicely formatted board for the user to view
	public static void outputChessBoard(ChessPiece[][] board)
	{
		System.out.println();
		
		//Iterate through each row and column in the array
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[i].length; j++)
			{
				//Print a blank space if this location is null
				if (board[i][j] == null)
					System.out.print("  --");
				else
				{
					System.out.print("  " + Character.toString(board[i][j].getColor().toUpperCase().charAt(0)) + Character.toString(board[i][j].getType().toUpperCase().charAt(0)));	//Format the piece for the board
					
				}
			}
			System.out.println();	//Start new row
			
		}
		System.out.println();
		
	}
	
	//Use a printwriter to save the board in a format that can later be re-read by the program to resume the game
	public static void saveToDisk(ChessPiece[][] board) throws IOException
	{
		PrintWriter pw = new PrintWriter(new File("chessboard.txt"));	//PW object
		
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[i].length; j++)
			{
				//Output all non-null array values using the ChessPiece class toString function
				if (board[i][j] != null)
					pw.println(board[i][j].toString());
					
			}
		}
		pw.close();	//Close the PW
		
	}
}

/* Output:
	Game Start. Input values are 1-8, Enter 0 for starting row to quit.
	
	  WR  WH  WB  WQ  WK  WB  --  WR
	  --  --  WP  --  --  WP  WP  WP
	  --  WP  --  WP  --  --  --  WH
	  WP  --  --  --  WP  --  --  --
	  BQ  --  BP  --  BP  BB  --  --
	  --  --  --  BP  --  --  --  --
	  BP  BP  --  --  --  BP  BP  BP
	  BR  BH  --  --  BK  BH  BB  BR
	
	Starting row: 1
	Starting col: 1
	Ending row: 6
	Ending col: 8
	
	  --  WH  WB  WQ  WK  WB  --  WR
	  --  --  WP  --  --  WP  WP  WP
	  --  WP  --  WP  --  --  --  WH
	  WP  --  --  --  WP  --  --  --
	  BQ  --  BP  --  BP  BB  --  --
	  --  --  --  BP  --  --  --  WR
	  BP  BP  --  --  --  BP  BP  BP
	  BR  BH  --  --  BK  BH  BB  BR
	
	Starting row: 8
	Starting col: 8
	Ending row: 1
	Ending col: 1
	
	  BR  WH  WB  WQ  WK  WB  --  WR
	  --  --  WP  --  --  WP  WP  WP
	  --  WP  --  WP  --  --  --  WH
	  WP  --  --  --  WP  --  --  --
	  BQ  --  BP  --  BP  BB  --  --
	  --  --  --  BP  --  --  --  WR
	  BP  BP  --  --  --  BP  BP  BP
	  BR  BH  --  --  BK  BH  BB  --
	
	Starting row: 8
	Starting col: 1
	Ending row: 7
	Ending col: 3
	
	  BR  WH  WB  WQ  WK  WB  --  WR
	  --  --  WP  --  --  WP  WP  WP
	  --  WP  --  WP  --  --  --  WH
	  WP  --  --  --  WP  --  --  --
	  BQ  --  BP  --  BP  BB  --  --
	  --  --  --  BP  --  --  --  WR
	  BP  BP  BR  --  --  BP  BP  BP
	  --  BH  --  --  BK  BH  BB  --
	
	Starting row: 0
	Board saved to chessboard.txt
	
	Chessboard.txt file contents:
	Black Rook 0 0
	White Horse 0 1
	White Bishop 0 2
	White Queen 0 3
	White King 0 4
	White Bishop 0 5
	White Rook 0 7
	White Pawn 1 2
	White Pawn 1 5
	White Pawn 1 6
	White Pawn 1 7
	White Pawn 2 1
	White Pawn 2 3
	White Horse 2 7
	White Pawn 3 0
	White Pawn 3 4
	Black Queen 4 0
	Black Pawn 4 2
	Black Pawn 4 4
	Black Bishop 4 5
	Black Pawn 5 3
	White Rook 5 7
	Black Pawn 6 0
	Black Pawn 6 1
	Black Rook 6 2
	Black Pawn 6 5
	Black Pawn 6 6
	Black Pawn 6 7
	Black Horse 7 1
	Black King 7 4
	Black Horse 7 5
	Black Bishop 7 6

	
 */
