/**This program allows to players to play connect four.  The program will allow each player 
 * to drop a game piece after viewing the game board, in a location of there choice. 
 *  After a piece is dropped the game will then either state its a draw game, who has
 * who allow the next player to take there turn.
 * Author: Sherry Gargasz
 * Date:03/04/14
 */

import java.util.Scanner;
import javax.swing.JOptionPane;

public class ConnectFour
{

	public static void main(String[] args)
	{
		char[][] gameBoard = new char[6][7];
		setUpBoard(gameBoard);
		String turn = " ";
		String player1 = "Red ";
		String player2 = "Yellow ";
		Scanner input = new Scanner(System.in);
		System.out.println("Player 1 is red and Player 2 is yellow colored disks");

		// There are only 42 turns possible on a 6x7 board
		//i is the number of turns taken
		
		for (int i = 0; i <= 41; i++)
		{
			turn = playersTurn(i, player1, player2);
			System.out.println(turn + "player's turn");
			displayBoard(gameBoard);
			System.out.println("Drop a " + turn + "disk at column(0-6) ");
			int column = input.nextInt();
			
			// Make sure the player doesn't try to put a piece in a nonexistent
			// column
			
			if (column > 6 || column < 0)
			{
				System.out.println("Invalid column choice, please try again");
				i--;
				continue;
			}
			int row = findRow(gameBoard, column);

			// Make sure the player doesn't try to put a piece in a full column.
			
			if (row == -1)
			{
				System.out.println("Invalid move, please try again");
				i--;
				continue;
			}
			gameBoard[row][column] = turn.charAt(0);
			boolean endgame = checkIfWinnergameBoard(gameBoard, i);
			if (endgame)
			{
				displayBoard(gameBoard);
				break;
			}
		}
		input.close();
	}

	public static void setUpBoard(char[][] board)
	{
		// sets the board to all spaces
		for (int row = 0; row < board.length; row++)
		{
			for (int column = 0; column < board[row].length; column++)
			{
				board[row][column] = ' ';
			}
		}

	}

	public static String playersTurn(int count, String player1, String player2)
	{
		// This method returns which players turn it is

		String player = " ";

		// If there is a remainder when dividing by 2 then its player 1's turn.
		if (count % 2 == 0)
		{
			player = player1;
		} else

		{
			player = player2;
		}
		return player;
	}

	public static int findRow(char[][] board, int column)
	{
		// Finds the first empty space in the chosen column
		int row = -1;
		for (int currentRow = board.length - 1; currentRow >= 0; currentRow--)
		{
			if (board[currentRow][column] == ' ')
			{
				row = currentRow;
				break;
			}
		}

		return row;
	}

	public static void displayBoard(char[][] board)
	{
		// This method displays the board

		for (int row = 0; row < board.length; row++)
		{
			for (int column = 0; column < board[row].length; column++)
			{
				System.out.print(board[row][column] + "| ");
			}
			System.out.println();

		}
		// this displays the bottom of the board

		for (int column = 0; column < board[0].length; column++)
		{
			System.out.print("---");

		}
		System.out.println(" ");
	}

	public static boolean checkIfWinnergameBoard(char[][] board, int count)
	{
		// This method returns if there is a winner or not

		// If the players reached 42 turns then there are no spaces left to play

		if (count == 41)
		{
			JOptionPane.showMessageDialog(null, "Game is a draw!", "No Winner",
					JOptionPane.INFORMATION_MESSAGE);
			System.out.println("The game is a draw!");
			return true;
		}
		
		// If not check the board to see if there is a winner horizontally
		
		int p = 0, r = 0;
		for (int row = 0; row < board.length; row++)
		{
			for (int column = 0; column < board[row].length; column++)
			{
				if (board[row][column] == 'Y')
				{
					p++;
					r = 0;
				} else if (board[row][column] == 'R')
				{
					r++;
					p = 0;

				} else
				{
					p = 0;
					r = 0;
				}

				// If there are 4 of either p or r then that player is the
				// winner. Put a dialog box then print on the console and
				// display
				// the board.
				
				if (p == 4)
				{
					JOptionPane.showMessageDialog(null,
							"Yellow player wins the game!", "Winner",
							JOptionPane.INFORMATION_MESSAGE);
					System.out.println("Yellow player wins the game!");
					return true;
				}

				else if (r == 4)
				{
					JOptionPane.showMessageDialog(null,
							"Red player wins the game!", "Winner",
							JOptionPane.INFORMATION_MESSAGE);
					System.out.println("Red player wins the game!");
					return true;
				}

			}

		}
		
		// If not check the board to see if there is a winner vertically
		
		p = 0;
		r = 0;
		for (int column = 0; column < board[0].length; column++)
		{
			for (int row = 0; row < board.length; row++)
			{
				if (board[row][column] == 'Y')
				{
					p++;
					r = 0;
				} else if (board[row][column] == 'R')
				{
					r++;
					p = 0;

				} else
				{
					p = 0;
					r = 0;
				}

				// If there are 4 of either p or r then that player is the
				// winner. Put a dialog box then print on the console and
				// display
				// the board.
				
				if (p == 4)
				{
					JOptionPane.showMessageDialog(null,
							"Yellow player wins the game!", "Winner",
							JOptionPane.INFORMATION_MESSAGE);
					System.out.println("Yellow player wins the game!");
					return true;
				}

				else if (r == 4)
				{
					JOptionPane.showMessageDialog(null,
							"Red player wins the game!", "Winner",
							JOptionPane.INFORMATION_MESSAGE);
					System.out.println("Red player wins the game!");
					return true;
				}

			}
			p = 0;
			r = 0;
		}

		// check if winner up diagonally
		
		for (int row = 5; row > 1; row--)
		{
			for (int column = 0; column < 4; column++)
			{
				// Check for p winning up diagonally
				
				if (board[row][column] == 'Y'
						&& board[row - 1][column + 1] == 'Y'
						&& board[row - 2][column + 2] == 'Y'
						&& board[row - 3][column + 3] == 'Y')
				{
					// If there are 4 of p then purple player is the winner. Put
					// a dialog box then print on the console and display
					// the board.
					
					JOptionPane.showMessageDialog(null,
							"Yellow player wins the game!", "Winner",
							JOptionPane.INFORMATION_MESSAGE);
					System.out.println("Yellow player  Wins!");
					return true;
				}
				// Check for r winning up diagonally
				
				else if (board[row][column] == 'R'
						&& board[row - 1][column + 1] == 'R'
						&& board[row - 2][column + 2] == 'R'
						&& board[row - 3][column + 3] == 'R')
				{
					// If there are 4 of r then red player is the winner. Put a
					// dialog box then print on the console and display
					// the board.
					
					JOptionPane.showMessageDialog(null,
							"Red player wins the game!", "Winner",
							JOptionPane.INFORMATION_MESSAGE);
					System.out.println("Red player Wins!");
					return true;
				}
			}
		}

		// check if winner down diagonally
		
		for (int row = 0; row < 3; row++)
		{
			for (int column = 0; column < 4; column++)
			{
				// Check for p winning down diagonally
				
				if (board[row][column] == 'Y'
						&& board[row + 1][column + 1] == 'Y'
						&& board[row + 2][column + 2] == 'Y'
						&& board[row + 3][column + 3] == 'Y')
				{
					// If there are 4 of p then purple player is the winner. Put
					// a dialog box then print on the console and display
					// the board.
					
					JOptionPane.showMessageDialog(null,
							"Yellow player wins the game!", "Winner",
							JOptionPane.INFORMATION_MESSAGE);
					System.out.println("Yellow player  Wins!");
					return true;
				}
				
				// Check for r winning down diagonally
				
				else if (board[row][column] == 'R'
						&& board[row + 1][column + 1] == 'R'
						&& board[row + 2][column + 2] == 'R'
						&& board[row + 3][column + 3] == 'R')
				{
					// If there are 4 of r then red player is the winner. Put a
					// dialog box then print on the console and display
					// the board.
					
					JOptionPane.showMessageDialog(null,
							"Red player wins the game!", "Winner",
							JOptionPane.INFORMATION_MESSAGE);
					System.out.println("Red player Wins!");
					return true;
				}

			}
		}

		return false;
	}

}
