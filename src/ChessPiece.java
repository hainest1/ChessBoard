/*
	Author: Thomas Haines
	Course: CSC260.002
	Date: 11/26/2016
	Assignment: #9
	Instructor: Fox
*/

/*
	Description: 
	Class to represent a single chess piece. Data includes type, 
	color, and location information. One constructor is available
	that takes color, type, row, and column as parameters.
	Mutators for row and column are available, as well as accessors
	for each piece of data.
	
*/

public class ChessPiece 
{
	//Class data
	String type, color;
	int row, col;
	
	//Default constructor
	public ChessPiece(String color, String type, int row, int col)
	{
		this.color = color;
		this.type = type;
		this.row = row;
		this.col = col;
		
	}
	
	//Accessor methods
	public String getType() { return type; }
	public String getColor() { return color; }
	public int getRow() { return row; }
	public int getColumn() { return col; }
	
	//Row mutator method
	public void setRow(int row)
	{
		if (row >= 0 && row <= 7) this.row = row;	//Data verify
	}
	
	//Column mutator method
	public void setColumn(int col)
	{
		if (col >= 0 && col <= 7)	//Data verify
			this.col = col;	
	}
	
	//Returns information about the piece
	public String toString()
	{
		return color + " " + type + " " + row + " " + col;
	}
}