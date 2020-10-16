/*
* Nicholas Parise
* ICS4U
* Maze Game
*/

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Maze {

	public static int[][] MazeMatrix;

	public static ArrayList<Integer> EnemyPathCol = new ArrayList<>();
	public static ArrayList<Integer> EnemyPathRow = new ArrayList<>();
	public static ArrayList<Integer> EnemyColQueue = new ArrayList<>();
	public static ArrayList<Integer> EnemyRowQueue = new ArrayList<>();

	public static int[][] EnemyMazeSolution;

	public static Boolean[][] WhereWallUp;
	public static Boolean[][] WhereWallRight;
	public static Boolean[][] WhereWallDown;
	public static Boolean[][] WhereWallLeft;

	public static int ExitRow;
	public static int ExitCol;

	// Reset Variables that allow Path finding to work

	public static void ResetVar() {

		EnemyRowQueue.clear();
		EnemyColQueue.clear();
		EnemyPathCol.clear();
		EnemyPathRow.clear();

		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 30; j++) {

				EnemyMazeSolution[i][j] = 0;
			}
		}

	}

	// return bigger number - smaller number
	public static int SmallestDistance(int Start, int Solution) {

		if (Start > Solution) {

			return Start - Solution;
		} else {
			return Solution - Start;
		}
	}

	// Recursion method of pathfinding
	// works fine but has a tendency to crash program

	/*
	 * public static boolean SolveMaze(int row, int col, int solutionRow, int
	 * solutionCol) {
	 * 
	 * 
	 * // if destination is reached, maze is solved if ((row == solutionRow) && (col
	 * == solutionCol)) { EnemyMazeSolution[row][col] = 1; EnemyPathCol.add(col);
	 * EnemyPathRow.add(row);
	 * 
	 * EnemyRowQueue.add(row); EnemyColQueue.add(col);
	 * 
	 * return true; }
	 * 
	 * // checking if we can visit in this cell or not // and to see if the row /
	 * col is within the avaliable numbers // and EnemyMazeSolution[row][col] == 0
	 * is making sure that the cell is not already visited
	 * 
	 * if (row >= 0 && col >= 0 && row < 30 && col < 30 &&
	 * EnemyMazeSolution[row][col] == 0) { // if(row >= 0 && col >= 0 && row < 30 &&
	 * col < 30) {
	 * 
	 * // if safe to visit then visit the cell
	 * 
	 * EnemyMazeSolution[row][col] = 1;
	 * 
	 * EnemyPathRow.add(row); EnemyPathCol.add(col);
	 * 
	 * EnemyRowQueue.add(row); EnemyColQueue.add(col);
	 * 
	 * // int BaseRow = SmallestDistance(row, solutionRow); // int BaseCol =
	 * SmallestDistance(col, solutionCol); // int BaseScore = (BaseRow * BaseRow) +
	 * (BaseCol * BaseCol);
	 * 
	 * int RigthRow = SmallestDistance(row, solutionRow); int RigthCol =
	 * SmallestDistance(col + 1, solutionCol); int RigthScore = (RigthRow *
	 * RigthRow) + (RigthCol * RigthCol);
	 * 
	 * int LeftRow = SmallestDistance(row, solutionRow); int LeftCol =
	 * SmallestDistance(col - 1, solutionCol); int LeftScore = (LeftRow * LeftRow) +
	 * (LeftCol * LeftCol);
	 * 
	 * int UpRow = SmallestDistance(row - 1, solutionRow); int UpCol =
	 * SmallestDistance(col, solutionCol); int UpScore = (UpRow * UpRow) + (UpCol *
	 * UpCol);
	 * 
	 * int DownRow = SmallestDistance(row + 1, solutionRow); int DownCol =
	 * SmallestDistance(col, solutionCol); int DownScore = (DownRow * DownRow) +
	 * (DownCol * DownCol);
	 * 
	 * int[] AllScores = { UpScore, LeftScore, DownScore, RigthScore }; int[]
	 * AllScoresTemp = AllScores;
	 * 
	 * Arrays.sort(AllScoresTemp);
	 * 
	 * int lowest = AllScoresTemp[0]; int SecondLowest = AllScoresTemp[1]; int
	 * ThridLowest = AllScoresTemp[2]; int Highest = AllScoresTemp[3];
	 * 
	 * boolean CanGoDown = false; boolean CanGoUp = false; boolean CanGoLeft =
	 * false; boolean CanGoRight = false;
	 * 
	 * for (int i = 0; i < 4; i++) {
	 * 
	 * if (AllScores[i] == lowest) { lowest = i; break; } }
	 * 
	 * for (int i = 0; i < 4; i++) {
	 * 
	 * if (AllScores[i] == SecondLowest) {
	 * 
	 * if (i != lowest) { SecondLowest = i; break; } } }
	 * 
	 * for (int i = 0; i < 4; i++) {
	 * 
	 * if (AllScores[i] == ThridLowest) { if (i != SecondLowest) { ThridLowest = i;
	 * break; } } }
	 * 
	 * for (int i = 0; i < 4; i++) {
	 * 
	 * if (AllScores[i] == Highest) { if (i != ThridLowest) { Highest = i; break; }
	 * } }
	 * 
	 * if ((row > 0) && EnemyMazeSolution[row - 1][col] == 0 &&
	 * WhereWallUp[row][col] == false) { CanGoUp = true; }
	 * 
	 * if ((col > 0) && EnemyMazeSolution[row][col - 1] == 0 &&
	 * WhereWallLeft[row][col] == false) { CanGoLeft = true; }
	 * 
	 * if ((row < 29) && EnemyMazeSolution[row + 1][col] == 0 &&
	 * WhereWallDown[row][col] == false) { CanGoDown = true; }
	 * 
	 * if ((col < 29) && EnemyMazeSolution[row][col + 1] == 0 &&
	 * WhereWallRight[row][col] == false) { CanGoRight = true; }
	 * 
	 * // System.out.println(CanGoUp + " " + CanGoRight + " " + CanGoDown + " "
	 * +CanGoLeft);
	 * 
	 * // "Did Go: ";
	 * 
	 * if (CanGoUp == true && lowest == 0) { // "Up "; if (SolveMaze((row - 1), col,
	 * solutionCol, solutionRow)) return true;
	 * 
	 * } else if (CanGoLeft == true && lowest == 1) { // "Left "; if (SolveMaze(row,
	 * (col - 1), solutionCol, solutionRow)) return true;
	 * 
	 * } else if (CanGoDown == true && lowest == 2) {
	 * 
	 * // "Down "; if (SolveMaze((row + 1), col, solutionCol, solutionRow)) return
	 * true;
	 * 
	 * } else if (CanGoRight == true && lowest == 3) { // "Right "; if
	 * (SolveMaze(row, (col + 1), solutionCol, solutionRow)) return true;
	 * 
	 * } else {
	 * 
	 * if (CanGoUp == true && SecondLowest == 0) { // "Up "; if (SolveMaze((row -
	 * 1), col, solutionCol, solutionRow)) return true;
	 * 
	 * } else if (CanGoLeft == true && SecondLowest == 1) {
	 * 
	 * // "Left "; if (SolveMaze(row, (col - 1), solutionCol, solutionRow)) return
	 * true;
	 * 
	 * } else if (CanGoDown == true && SecondLowest == 2) { // "Down "; if
	 * (SolveMaze((row + 1), col, solutionCol, solutionRow)) return true;
	 * 
	 * } else if (CanGoRight == true && SecondLowest == 3) {
	 * 
	 * // "Right "; if (SolveMaze(row, (col + 1), solutionCol, solutionRow)) return
	 * true;
	 * 
	 * } else {
	 * 
	 * if (CanGoUp == true && ThridLowest == 0) {
	 * 
	 * // "Up "; if (SolveMaze((row - 1), col, solutionCol, solutionRow)) return
	 * true;
	 * 
	 * } else if (CanGoLeft == true && ThridLowest == 1) { // "Left "; if
	 * (SolveMaze(row, (col - 1), solutionCol, solutionRow)) return true;
	 * 
	 * } else if (CanGoDown == true && ThridLowest == 2) {
	 * 
	 * // "Down "; if (SolveMaze((row + 1), col, solutionCol, solutionRow)) return
	 * true;
	 * 
	 * } else if (CanGoRight == true && ThridLowest == 3) { // "Right "; if
	 * (SolveMaze(row, (col + 1), solutionCol, solutionRow)) return true; } else {
	 * 
	 * if (CanGoUp == true && Highest == 0) { // "Up "; if (SolveMaze((row - 1),
	 * col, solutionCol, solutionRow)) return true;
	 * 
	 * } else if (CanGoLeft == true && Highest == 1) { // "Left "; if
	 * (SolveMaze(row, (col - 1), solutionCol, solutionRow)) return true;
	 * 
	 * } else if (CanGoDown == true && Highest == 2) {
	 * 
	 * // "Down "; if (SolveMaze((row + 1), col, solutionCol, solutionRow)) return
	 * true;
	 * 
	 * } else if (CanGoRight == true && Highest == 3) { // "Right "; if
	 * (SolveMaze(row, (col + 1), solutionCol, solutionRow)) return true;
	 * 
	 * } else {
	 * 
	 * if (CanGoDown == false && CanGoUp == false && CanGoLeft == false &&
	 * CanGoRight == true) {
	 * 
	 * // "Right "; if (SolveMaze(row, col + 1, solutionCol, solutionRow)) return
	 * true; }
	 * 
	 * if (CanGoDown == false && CanGoUp == false && CanGoLeft == true && CanGoRight
	 * == false) {
	 * 
	 * // "Left "; if (SolveMaze(row, col - 1, solutionCol, solutionRow)) return
	 * true; }
	 * 
	 * if (CanGoDown == false && CanGoUp == true && CanGoLeft == false && CanGoRight
	 * == false) {
	 * 
	 * // "Up "; if (SolveMaze(row - 1, col, solutionCol, solutionRow)) return true;
	 * }
	 * 
	 * if (CanGoDown == true && CanGoUp == false && CanGoLeft == false && CanGoRight
	 * == false) {
	 * 
	 * // "Down " if (SolveMaze(row + 1, col, solutionCol, solutionRow)) return
	 * true; } } } } } }
	 * 
	 * // backtracking
	 * 
	 * // System.out.println("Back Tracing start");
	 * 
	 * 
	 * int index = EnemyRowQueue.size() - 1;
	 * 
	 * if (index > 1) {
	 * 
	 * EnemyRowQueue.remove(index); EnemyColQueue.remove(index);
	 * 
	 * index = EnemyRowQueue.size() - 1;
	 * 
	 * row = EnemyRowQueue.get(index); col = EnemyColQueue.get(index);
	 * 
	 * // System.out.println("Back Tracing end");
	 * 
	 * if (SolveMaze(row, col, solutionCol, solutionRow)) { return true; } }
	 * 
	 * 
	 * return false; }
	 * 
	 */

	// Loop method of pathfinding
	public static boolean SolveMaze(int row, int col, int solutionRow, int solutionCol) {

		int StopCrash = 0;

		do {

			StopCrash++;

			// if destination is reached, maze is solved
			if ((row == solutionRow) && (col == solutionCol)) {
				EnemyMazeSolution[row][col] = 1;
				EnemyPathCol.add(col);
				EnemyPathRow.add(row);

				EnemyRowQueue.add(row);
				EnemyColQueue.add(col);
				// end loop
				return true;
			}

			// checking if we can visit this cell and to see if the row / col is within the
			// avaliable numbers
			// and EnemyMazeSolution[row][col] == 0 is making sure that the cell is not
			// already visited

			if (row >= 0 && col >= 0 && row < 30 && col < 30 && EnemyMazeSolution[row][col] == 0) {

				EnemyMazeSolution[row][col] = 1;
				// Marked as visited

				EnemyPathRow.add(row);
				EnemyPathCol.add(col);

				EnemyRowQueue.add(row);
				EnemyColQueue.add(col);

				// checking distance to exit from each direction

				int RigthRow = SmallestDistance(row, solutionRow);
				int RigthCol = SmallestDistance(col + 1, solutionCol);
				int RigthScore = (RigthRow * RigthRow) + (RigthCol * RigthCol);

				int LeftRow = SmallestDistance(row, solutionRow);
				int LeftCol = SmallestDistance(col - 1, solutionCol);
				int LeftScore = (LeftRow * LeftRow) + (LeftCol * LeftCol);

				int UpRow = SmallestDistance(row - 1, solutionRow);
				int UpCol = SmallestDistance(col, solutionCol);
				int UpScore = (UpRow * UpRow) + (UpCol * UpCol);

				int DownRow = SmallestDistance(row + 1, solutionRow);
				int DownCol = SmallestDistance(col, solutionCol);
				int DownScore = (DownRow * DownRow) + (DownCol * DownCol);

				// creating and sorting a list of directions

				int[] AllScores = { UpScore, LeftScore, DownScore, RigthScore };
				int[] AllScoresTemp = AllScores;

				Arrays.sort(AllScoresTemp);

				int lowest = AllScoresTemp[0];
				int SecondLowest = AllScoresTemp[1];
				int ThridLowest = AllScoresTemp[2];
				int Highest = AllScoresTemp[3];

				boolean CanGoDown = false;
				boolean CanGoUp = false;
				boolean CanGoLeft = false;
				boolean CanGoRight = false;

				for (int i = 0; i < 4; i++) {

					if (AllScores[i] == lowest) {
						lowest = i;
						break;
					}
				}

				for (int i = 0; i < 4; i++) {

					if (AllScores[i] == SecondLowest) {

						if (i != lowest) {
							SecondLowest = i;
							break;
						}
					}
				}

				for (int i = 0; i < 4; i++) {

					if (AllScores[i] == ThridLowest) {
						if (i != SecondLowest) {
							ThridLowest = i;
							break;
						}
					}
				}

				for (int i = 0; i < 4; i++) {

					if (AllScores[i] == Highest) {
						if (i != ThridLowest) {
							Highest = i;
							break;
						}
					}
				}

				// Checking avaliable moves
				if ((row > 0) && EnemyMazeSolution[row - 1][col] == 0 && WhereWallUp[row][col] == false) {
					CanGoUp = true;
				}

				if ((col > 0) && EnemyMazeSolution[row][col - 1] == 0 && WhereWallLeft[row][col] == false) {
					CanGoLeft = true;
				}

				if ((row < 29) && EnemyMazeSolution[row + 1][col] == 0 && WhereWallDown[row][col] == false) {
					CanGoDown = true;
				}

				if ((col < 29) && EnemyMazeSolution[row][col + 1] == 0 && WhereWallRight[row][col] == false) {
					CanGoRight = true;
				}

				// if can go a direction and it is the closest direction to the end goal go
				// there

				// "Did Go: ";
				if (CanGoUp == true && lowest == 0) {

					row = row - 1;

				} else if (CanGoLeft == true && lowest == 1) {

					col = col - 1;

				} else if (CanGoDown == true && lowest == 2) {

					row = row + 1;

				} else if (CanGoRight == true && lowest == 3) {

					col = col + 1;

				} else {

					// if it can't go to the closest direction then go to the second closest

					if (CanGoUp == true && SecondLowest == 0) {

						row = row - 1;

					} else if (CanGoLeft == true && SecondLowest == 1) {

						col = col - 1;

					} else if (CanGoDown == true && SecondLowest == 2) {

						row = row + 1;

					} else if (CanGoRight == true && SecondLowest == 3) {

						col = col + 1;

					} else {
						// if it can't go to the closest direction then go to the third closest

						if (CanGoUp == true && ThridLowest == 0) {

							row = row - 1;

						} else if (CanGoLeft == true && ThridLowest == 1) {

							col = col - 1;

						} else if (CanGoDown == true && ThridLowest == 2) {

							row = row + 1;

						} else if (CanGoRight == true && ThridLowest == 3) {

							col = col + 1;

						} else {

							// if it can't go to the closest direction then go to the furthest closest

							if (CanGoUp == true && Highest == 0) {

								row = row - 1;

							} else if (CanGoLeft == true && Highest == 1) {

								col = col - 1;

							} else if (CanGoDown == true && Highest == 2) {

								row = row + 1;

							} else if (CanGoRight == true && Highest == 3) {

								col = col + 1;

							} else {

								// if it can't go to any prefered direction then go in any avaliable directions

								if (CanGoRight == true) {

									col = col + 1;

								} else if (CanGoLeft == true) {

									col = col - 1;

								} else if (CanGoUp == true) {

									row = row - 1;
								} else if (CanGoDown == true) {

									row = row + 1;
								} else {
									// if it can't go in any direction
									// backtrace

									int index = EnemyRowQueue.size() - 1;

									if (index > 1) {

										EnemyRowQueue.remove(index);
										EnemyColQueue.remove(index);

										index = EnemyRowQueue.size() - 1;

										row = EnemyRowQueue.get(index);
										col = EnemyColQueue.get(index);

									} else {
										StopCrash = 10000000;
										return false;
									}

								}
							}
						}
					}
				}
			} else {

				// if out of bounds backtrace

				int index = EnemyRowQueue.size() - 1;

				if (index > 1) {

					EnemyRowQueue.remove(index);
					EnemyColQueue.remove(index);

					index = EnemyRowQueue.size() - 1;

					row = EnemyRowQueue.get(index);
					col = EnemyColQueue.get(index);

				} else {
					StopCrash = 10000000;
					return false;
				}

			}
			// Make sure the program does not go on a forever loop if it can't find the end
		} while (StopCrash < 10000);

		return false;

	}

	public static int CanGo(int row, int col) {

		// This function is a little redundant but it works for the Maze maker
		// it could be re worked to work with the path finding but it works how it is

		Random rand = new Random();

		int TryWhere = rand.nextInt(4);

		Boolean CanGoUp = false;
		Boolean CanGoLeft = false;
		Boolean CanGoDown = false;
		Boolean CanGoRight = false;

		// Checking if it can go in a direction

		if ((row > 0) && MazeMatrix[row - 1][col] != 1) {
			CanGoUp = true;
		}

		if ((col < 29) && MazeMatrix[row][col + 1] != 1) {
			CanGoRight = true;
		}

		if ((row < 29) && MazeMatrix[row + 1][col] != 1) {
			CanGoDown = true;
		}

		if ((col > 0) && MazeMatrix[row][col - 1] != 1) {
			CanGoLeft = true;
		}

		// If the random number corresponds to a direction that is avaliavle return that
		// number

		if (TryWhere == 0 && CanGoUp == true) {
			return 0;
		} else if (TryWhere == 1 && CanGoRight == true) {
			return 1;
		} else if (TryWhere == 2 && CanGoDown == true) {
			return 2;
		} else if (TryWhere == 3 && CanGoLeft == true) {
			return 3;
		} else {

			if (CanGoUp == true) {
				return 0;
			} else if (CanGoRight == true) {
				return 1;
			} else if (CanGoDown == true) {
				return 2;
			} else if (CanGoLeft == true) {
				return 3;
			}
		}

		// If it can't go anywhere return escape
		return 4;
	}

	// This makes the maze
	public static void MazeMaker() {

		// Resets maze matrix
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 30; j++) {

				MazeMatrix[i][j] = 0;
			}
		}

		int row = 0;
		int col = 0;
		int whichWay;

		ArrayList<Integer> PathCol = new ArrayList<>();
		ArrayList<Integer> PathRow = new ArrayList<>();

		ArrayList<Integer> ColQueue = new ArrayList<>();
		ArrayList<Integer> RowQueue = new ArrayList<>();

		int DontCrash = 0;

		MazeMatrix[0][0] = 1;

		do {

			whichWay = CanGo(row, col);

			// If can go in a direction go there:

			if (whichWay == 0) {
				// Up

				WhereWallDown[row - 1][col] = false;
				WhereWallUp[row][col] = false;

				MazeMatrix[row - 1][col] = 1;
				// Marks as visited

				PathCol.add(col);
				ColQueue.add(col);
				PathRow.add(row - 1);
				RowQueue.add(row - 1);

				row = row - 1;

			} else if (whichWay == 1) {
				// Right

				WhereWallLeft[row][col + 1] = false;
				WhereWallRight[row][col] = false;

				MazeMatrix[row][col + 1] = 1;

				PathCol.add(col + 1);
				ColQueue.add(col + 1);
				PathRow.add(row);
				RowQueue.add(row);

				col = col + 1;

			} else if (whichWay == 2) {
				// Down

				WhereWallUp[row + 1][col] = false;
				WhereWallDown[row][col] = false;

				MazeMatrix[row + 1][col] = 1;

				PathCol.add(col);
				ColQueue.add(col);

				PathRow.add(row + 1);
				RowQueue.add(row + 1);

				row = row + 1;

			} else if (whichWay == 3) {
				// Left

				WhereWallRight[row][col - 1] = false;
				WhereWallLeft[row][col] = false;

				MazeMatrix[row][col - 1] = 1;

				PathCol.add(col - 1);
				ColQueue.add(col - 1);
				PathRow.add(row);
				RowQueue.add(row);

				col = col - 1;

			} else if (whichWay == 4) {

				// if cant go anywhere Backtrace

				int index = RowQueue.size() - 1;

				RowQueue.remove(index);
				ColQueue.remove(index);

				index = RowQueue.size() - 1;

				if (index > 0) {

					row = RowQueue.get(index);
					col = ColQueue.get(index);

				} else {
					DontCrash = 1000;
					break;
				}
			}

			DontCrash++;

			// Make sure the program does not go on a forever loop if it can't find the end
		} while (DontCrash < 10000);

	}

	// Finds exit to maze
	public static void FindExit() {

		int LengthScore = 0;
		int TempScore = 0;
		int FinishRow = 0;
		int FinishCol = 0;

		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 30; j++) {

				// reset necessary variables
				ResetVar();

				// runs solve maze for every location in matrix
				if (SolveMaze(0, 0, i, j)) {

					TempScore = EnemyPathCol.size();

					// if the moves required is more than the previous
					if (TempScore > LengthScore) {
						LengthScore = TempScore;
						// make this the new score
						FinishRow = i;
						FinishCol = j;

					} else if (TempScore == LengthScore) {

						// if the same moves, make the row and col the furthest away

						if ((i + j) > (FinishRow + FinishCol)) {

							FinishRow = i;
							FinishCol = j;
						}
					}

					System.out.println(FinishRow + " " + FinishCol + " " + LengthScore);
				}
			}
		}

		ExitRow = FinishRow;
		ExitCol = FinishCol;

		System.out.println(ExitRow + " " + ExitCol);
	}

	// Prints entire Maze
	public static void PrintMaze(Player player, Enemy enemy, Chest chest) {

		int playerRow = player.GetPositionRow();
		int playerCol = player.GetPositionCol();

		for (int i = 0; i < 30; i++) {

			for (int k = 0; k < 3; k++) {
				for (int j = 0; j < 30; j++) {

					if (k == 0) {

						if (WhereWallUp[i][j] == true) {
							System.out.print("___");
						} else {

							System.out.print("|0|");
						}

					} else if (k == 1) {

						if (WhereWallLeft[i][j] == true) {
							System.out.print("|");
						} else {
							System.out.print("0");
						}

						if (j == ExitCol && i == ExitRow) {
							System.out.print("&");

						} else if (i == playerRow && j == playerCol) {
							System.out.print("$");
						} else if (i == chest.GetPositionRow() && j == chest.GetPositionCol()) {

							if (!chest.ChestOpened) {
								System.out.print("C");
							} else {
								System.out.print("0");
							}

						} else if (i == enemy.GetPositionRow() && j == enemy.GetPositionCol()) {

							if (!enemy.Dead) {
								System.out.print("M");
							} else {
								System.out.print("0");
							}

						} else {
							System.out.print("0");
						}

						if (WhereWallRight[i][j] == true) {
							System.out.print("|");
						} else {
							System.out.print("0");
						}

					} else {

						if (WhereWallDown[i][j] == true) {
							System.out.print("‾‾‾");
						} else {

							System.out.print("|0|");
						}
					}

				}
				System.out.println();
			}
		}
	}

	// Prints small part of maze
	public static void SelectiveMazeDraw(Player player, Enemy enemy, Chest chest) {

		int StartRow = 0;
		int StartCol = 0;

		int playerRow = player.GetPositionRow();
		int playerCol = player.GetPositionCol();

		if (playerRow > 5 && playerRow < 25) {
			StartRow = playerRow - 2;
		} else if (playerRow < 5) {
			StartRow = 0;
		} else if (playerRow > 25) {
			StartRow = 25;
		} else {

			StartRow = playerRow - 2;
		}

		if (playerCol > 5 && playerCol < 20) {
			StartCol = playerCol - 2;
		} else if (playerCol < 5) {
			StartCol = 0;
		} else if (playerCol > 20) {
			StartCol = 20;
		} else {

			StartCol = playerCol - 2;
		}

		for (int i = StartRow; i < StartRow + 5; i++) {
			for (int k = 0; k < 3; k++) {
				for (int j = StartCol; j < StartCol + 10; j++) {

					if (k == 0) {

						if (WhereWallUp[i][j] == true) {
							System.out.print("___");
						} else {

							System.out.print("|0|");
						}

					} else if (k == 1) {

						if (WhereWallLeft[i][j] == true) {
							System.out.print("|");
						} else {
							System.out.print("0");
						}

						if (j == ExitCol && i == ExitRow) {
							System.out.print("&");

						} else if (i == playerRow && j == playerCol) {
							System.out.print("$");
						} else if (i == chest.GetPositionRow() && j == chest.GetPositionCol()) {

							if (!chest.ChestOpened) {
								System.out.print("C");
							} else {
								System.out.print("0");
							}

						} else if (i == enemy.GetPositionRow() && j == enemy.GetPositionCol()) {

							if (!enemy.Dead) {
								System.out.print("M");
							} else {
								System.out.print("0");
							}

						} else {
							System.out.print("0");
						}

						if (WhereWallRight[i][j] == true) {
							System.out.print("|");
						} else {
							System.out.print("0");
						}

					} else {

						if (WhereWallDown[i][j] == true) {
							System.out.print("‾‾‾");
						} else {

							System.out.print("|0|");
						}
					}

				}
				System.out.println();
			}

		}

	}

}
