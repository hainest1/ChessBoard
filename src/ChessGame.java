/*
	Author: Thomas Haines
	Course: CSC260.002
	Date: 11/26/2016
	Assignment: #9
	Instructor: Fox
*/

/*
	Description: 
	
*/

import java.util.Scanner;
import java.io.*;

public class ChessGame 
{
	public static void main(String[] args) throws IOException
	{
		ChessPiece[][] chessBoard = new ChessPiece[8][8];
		Scanner in = new Scanner(System.in);
		int startRow, startCol, endRow, endCol;
		
		inputFromDisk(chessBoard);
		System.out.println("Game Start. Input values are 1-8, Enter 0 for starting row to quit.");
		startRow = inputMove(in, "Starting", "row");
		
		while (startRow >= 1)
		{
			startCol = inputMove(in, "Starting", "col");
			endRow = inputMove(in, "Ending", "row");
			endCol = inputMove(in, "Ending", "col");
			move(startRow, startCol, endRow, endCol, chessBoard);
			outputChessBoard(chessBoard);
			
		}
		
		//saveToDisk(chessBoard);
		
	}
	
	public static void inputFromDisk(ChessPiece[][] board) throws IOException
	{
		Scanner input = new Scanner(new File("chessboard.txt"));
		
		while (input.hasNextLine())
		{
			String piece = input.next();
			String[] pieceInfo = piece.split(piece);
			String color = pieceInfo[0];
			String type = pieceInfo[1];
			int row = pieceInfo[2];
			int col = pieceInfo[3];
			
			board[row][col] = new ChessPiece(color, type, row, col);
			
		}
		
		input.close();
		
	}
	
	public static int inputMove(Scanner in, String order, String dimension)
	{
		System.out.print("" + order + " " + dimension + ": ");
		int value = in.nextInt();
		
		if (value >= 0 && value <= 7 && order.equals("Starting") || value >= 1 && value <= 7)
			return value;
		else return 0;
		
	}
	
	public static void move(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board)
	{
		//Subtract 1 from input to match array indices
		startRow -= 1;
		startCol -= 1;
		endRow -= 1;
		endCol -= 1;
		
		if(board[startRow][startCol]!= null && board[endRow][endCol] == null) 
		{
			board[endRow][endCol] = board[startRow][startCol];
			board[endRow][endCol].setRow(endRow);
			board[endRow][endCol].setColumn(endCol);
			board[startRow][startCol] = null;
		
		}
		else System.out.println("Illegal move. Turn forfeited.");
	}
	
	public static void outputChessBoard(ChessPiece[][] board)
	{
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[i].length; j++)
			{
				if (board[i][j] == null)
					System.out.print("-- ");
				else
				{
					String piece = "" + Character.toString(board[i][j].getColor().toUpperCase().charAt(0))
						+ Character.toString(board[i][j].getType().toUpperCase().charAt(0)) + " ";
					
				}
			}
			
			System.out.println();
			
		}
	}
	
	public static void saveToDisk(String[][] board) throws IOException
	{
		
	}
	
}















