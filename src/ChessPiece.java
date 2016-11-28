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

public class ChessPiece 
{
	String type, color;
	int row, col;
	
	public ChessPiece(String type, String color, int row, int col)
	{
		this.type = type;
		this.color = color;
		this.row = row;
		this.col = col;
		
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getColor()
	{
		return color;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getColumn()
	{
		return col;
	}
	
	public void setRow(int row)
	{
		if (row >= 0 && row <= 7)
			this.row = row;
	}
	
	public void setColumn(int col)
	{
		if (col >= 0 && col <= 7)
			this.col = col;
	}
	
	public String toString()
	{
		return color + " " + type + " " + row + " " + col;
	}
	
}