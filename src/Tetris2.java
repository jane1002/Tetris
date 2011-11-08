//By Pat Needham
//Looks like: *** 
//			   *

import java.awt.*;

public class Tetris2 extends ColorTetris
{
	public Tetris2(int x1, int y1, int row, int col, Color c, TetrisPanel t)
	{
		super(x1, y1, row, col, c, t);
		getTetrisPanel().enterGrid(row, col);
		getTetrisPanel().enterGrid(row + 1, col);
		getTetrisPanel().enterGrid(row + 1, col + 1);
		getTetrisPanel().enterGrid(row + 2, col);
	}
	
	public void setLocation(int x, int y)
	{
		r1.setLocation(x, y);
		r2.setLocation(x + 25, y);
		r3.setLocation(x + 25, y + 25);
		r4.setLocation(x + 50, y);
	}
	
	public void move()
	{
		setY(getY() + 25);
		setCol(getCol() + 1);
		setLocation(getX(), getY());
		
		getTetrisPanel().enterGrid(getRow(), getCol());
		getTetrisPanel().enterGrid(getRow() + 1, getCol());
		getTetrisPanel().enterGrid(getRow() + 1, getCol() + 1);
		getTetrisPanel().enterGrid(getRow() + 2, getCol());
		
		getTetrisPanel().exitGrid(getRow(), getCol() - 1);
		getTetrisPanel().exitGrid(getRow() + 1, getCol() - 1);
		getTetrisPanel().exitGrid(getRow() + 2, getCol() - 1);
	}
	
	public void moveLeft()
	{
		if (!getTetrisPanel().checkLocation(getRow() - 1, getCol()))
		{
			getTetrisPanel().enterGrid(getRow() - 1, getCol());
			getTetrisPanel().enterGrid(getRow(), getCol() + 1);
			getTetrisPanel().exitGrid(getRow() + 2, getCol());
			getTetrisPanel().exitGrid(getRow() + 1, getCol() + 1);
			super.moveLeft();
		}
	}
	
	public void moveRight()
	{
		if (!getTetrisPanel().checkLocation(getRow() + 3, getCol()))
		{
			getTetrisPanel().enterGrid(getRow() + 3, getCol());
			getTetrisPanel().enterGrid(getRow() + 2, getCol() + 1);
			getTetrisPanel().exitGrid(getRow(), getCol());
			getTetrisPanel().exitGrid(getRow() + 1, getCol() + 1);
			super.moveRight();
		}
	}
	
	public boolean nextSpotEmpty()
	{
		boolean a = getTetrisPanel().checkLocation(getRow(), getCol() + 1);
		boolean b = getTetrisPanel().checkLocation(getRow() + 1, getCol() + 2);
		boolean c = getTetrisPanel().checkLocation(getRow() + 2, getCol() + 1);
		return (!(a || b || c));
	}
	
	public ColorTetris rotate()
	{
		removeFromGrid();
		ColorTetris t = 
		new Tetris2B(getX(), getY(), getRow(), getCol(), getColor(), getTetrisPanel());
		return t;
	}
	
	public boolean canRotate()
	{
		return (!getTetrisPanel().checkLocation(getRow(), getCol() - 1)
			&& !getTetrisPanel().checkLocation(getRow(), getCol() - 2)
			&& !getTetrisPanel().checkLocation(getRow() + 1, getCol() - 2));
	}
	
	public void removeFromGrid()
	{
		getTetrisPanel().exitGrid(getRow(), getCol());
		getTetrisPanel().exitGrid(getRow() + 1, getCol());
		getTetrisPanel().exitGrid(getRow() + 1, getCol() + 1);
		getTetrisPanel().exitGrid(getRow() + 2, getCol());
		r1 = null;
		r2 = null;
		r3 = null;
		r4 = null;
	}
	
	public void addEachRectangle()
	{
		getTetrisPanel().addToRGrid(r1, getRow(), getCol());
		getTetrisPanel().addToRGrid(r2, getRow() + 1, getCol());
		getTetrisPanel().addToRGrid(r3, getRow() + 1, getCol() + 1);
		getTetrisPanel().addToRGrid(r4, getRow() + 2, getCol());
	}
}