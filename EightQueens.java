/**
* @author Victor Bieniek
* CSC 201
* Professor Tanes Kanchanawanchai
* EightQueens
* This class displays a random solution to the eight queens puzzle in which
* eight queens must be placed on a chess board in such a way that no queen can
* attack any other queen
*/

import java.util.Random;

public class EightQueens
{
	public static void main(String[] args)
	{
		char[][] board = new char[8][8];
		EightQueens eq = new EightQueens();

		//fill board with spaces
		eq.clearBoard(board);

		eq.solveEightQueens(board);

		//print board with final answer
		for(char[] row : board)
		{
			for(char square : row)
			{
				System.out.print("|" + square);
			}
			System.out.println("|");
		}
	}

	private void solveEightQueens(char[][] board)
	{
		int counter = 0;
		int x = randomIntInRange(0,7);
		int y;

		//1st queen
		board[0][x] = 'Q';

		//for 7 remaining queens
		for(int i = 1; i < 8; i++)
		{
			x = randomIntInRange(0,7);
			y = i;

			if(isValidSpot(board, x, y))
			{
				board[y][x] = 'Q';
				counter = 0;
			}
			else
			{
				counter++;
				if(counter > 500)
				{
					//if it has to try too many times just start over with new random positions
					clearBoard(board);
					board[randomIntInRange(0,7)][randomIntInRange(0,7)] = 'Q';
					i = 0;
				}
				else
				{
					i--;
				}
			}
		}
	}//end solve

	private boolean isValidSpot(char[][] board, int x, int y)
	{
		//check that location is not out of bounds
		if(x > 7 || y > 7 || x < 0 || y < 0) return false;

		//check if space is taken
		if(board[y][x] == 'Q') return false;

		//check row(x)
		for(int i = 0; i < board[y].length; i++)
		{
			if(board[y][i] == 'Q') return false; 
		}
		//check col(y)
		for(int i = 0; i < board.length; i++)
		{
			if(board[i][x] == 'Q') return false;
		}
		//check diagonal up
		if(x != 7 || y != 7)//avoids array index out of bounds error
		{
			for(int i = x+1, j = y+1;i < 8 && j < 8; i++,j++)
			{
				if(board[j][i] == 'Q') return false;
			}
		}
		if(x != 0 || y != 0)//avoids array index out of bounds error
		{
			//check diagonal down
			for(int i = x-1, j = y-1; i > -1 && j > -1; i--, j--)
			{
				if(board[j][i] == 'Q') return false;
			}
		}
		//check other diagonal up([+y][-x])
		if(x != 0 || y != 7)//avoids array index out of bounds error
		{
			for(int i = x-1, j = y+1; i > -1 && j < 8; i--, j++)
			{
				if(board[j][i] == 'Q') return false;
			}
		}
		//check other diagonal down ([-y][+x])
		if(y != 0 || x != 7)//avoids array index out of bounds error
		{
			for(int i = x+1, j = y-1; i < 8 && j > -1; i++, j--)
			{
				if(board[j][i] == 'Q') return false;
			}
		}

		return true;
	}

	private void clearBoard(char[][] board)
	{
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[0].length; j++)
			{
				board[i][j] = ' ';
			}
		}
	}

	private int randomIntInRange(int min, int max)
	{
		Random rand = new Random();
		return rand.nextInt(max - min + 1) + min;
	}
}//end class