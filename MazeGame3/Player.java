import java.util.ArrayList;
import java.util.Scanner;

/*
* Nicholas Parise
* ICS4U
* Maze Game
*/
public class Player extends Entity {

	public static int CurrentRow = 0;
	public static int CurrentCol = 0;
	public static boolean Dead = false;

	public static boolean HasSword = false;
	// If player has sword he can destroy enemy if it lands on him

	public static boolean HasTorch = false;
	// if the player has torch he can see more maze

	public static boolean HasCompass = false;
	// if the player has compass he can ask for directions

	public static int SwordDurr = 3;

	public static boolean Win = false;

	public static boolean UseTorch = false;

	public static ArrayList<Integer> LastCol = new ArrayList<>();
	public static ArrayList<Integer> LastRow = new ArrayList<>();

	public static void SetPosition(int row, int col) {
		CurrentRow = row;
		CurrentCol = col;
	}

	public static int GetPositionRow() {

		return CurrentRow;
	}

	public static int GetPositionCol() {

		return CurrentCol;
	}

	public static void resetPlayer() {

		SetPosition(0, 0);
		Win = false;
		Dead = false;
		LastRow.clear();
		LastCol.clear();

	}

	// prompts the player to move
	public static void PromptMove() {

		Scanner sc = new Scanner(System.in);

		// displays avaliable directions

		System.out.println("Pos: " + GetPositionRow() + " " + GetPositionCol());
		System.out.println("End: " + Maze.ExitRow + " " + Maze.ExitCol);

		System.out.print("Do You Want To Go: ");

		if (Maze.WhereWallUp[GetPositionRow()][GetPositionCol()] == false) {
			System.out.print("Up,");
		}
		if (Maze.WhereWallRight[GetPositionRow()][GetPositionCol()] == false) {
			System.out.print("Right,");
		}
		if (Maze.WhereWallDown[GetPositionRow()][GetPositionCol()] == false) {
			System.out.print("Down,");
		}
		if (Maze.WhereWallLeft[GetPositionRow()][GetPositionCol()] == false) {
			System.out.print("Left");
		}

		System.out.print(" (");

		if (Maze.WhereWallUp[GetPositionRow()][GetPositionCol()] == false) {
			System.out.print("U,");
		}
		if (Maze.WhereWallRight[GetPositionRow()][GetPositionCol()] == false) {
			System.out.print("R,");
		}
		if (Maze.WhereWallDown[GetPositionRow()][GetPositionCol()] == false) {
			System.out.print("D,");
		}
		if (Maze.WhereWallLeft[GetPositionRow()][GetPositionCol()] == false) {
			System.out.print("L,");
		}

		System.out.print("): ");

		String Input = sc.next();

		// sc.close();

		char InputChar = Input.charAt(0);

		LastRow.add(GetPositionRow());
		LastCol.add(GetPositionCol());

		// console in
		if ((InputChar == 'U' || InputChar == 'u') && Maze.WhereWallUp[GetPositionRow()][GetPositionCol()] == false) {

			SetPosition(GetPositionRow() - 1, GetPositionCol());
		}

		if ((InputChar == 'R' || InputChar == 'r')
				&& Maze.WhereWallRight[GetPositionRow()][GetPositionCol()] == false) {

			SetPosition(GetPositionRow(), GetPositionCol() + 1);
		}

		if ((InputChar == 'D' || InputChar == 'd') && Maze.WhereWallDown[GetPositionRow()][GetPositionCol()] == false) {

			SetPosition(GetPositionRow() + 1, GetPositionCol());
		}

		if ((InputChar == 'L' || InputChar == 'l') && Maze.WhereWallLeft[GetPositionRow()][GetPositionCol()] == false) {

			SetPosition(GetPositionRow(), GetPositionCol() - 1);
		}
	}

	// Prompts the player to proform an action
	public static void PromptAction() {

		Scanner sc = new Scanner(System.in);

		if (HasSword || HasTorch || HasCompass) {

			System.out.print("Do You Want To use: ");

			if (HasSword)
				System.out.print("Sword,");

			if (HasTorch)
				System.out.print("Torch,");

			if (HasCompass)
				System.out.print("Compass");

			System.out.print("(");

			if (HasSword)
				System.out.print("S,");

			if (HasTorch)
				System.out.print("T,");

			if (HasCompass)
				System.out.print("C");

			System.out.print("): ");

			String Input = sc.next();

			char InputChar = Input.charAt(0);

			if (InputChar == 'T' || InputChar == 't' && HasTorch) {

				System.out.print("Do you want to use the torch? (Y/N): ");

				Input = sc.next();

				InputChar = Input.charAt(0);

				if (InputChar == 'T' || InputChar == 't') {

					UseTorch = true;
					System.out.print("You light the torch");
				} else {

					UseTorch = false;
					System.out.print("You put out the torch");
				}

			} else if (InputChar == 'C' || InputChar == 'c' && HasCompass) {

				Maze.ResetVar();

				// proforms pathfinding

				if (Maze.SolveMaze(Maze.ExitRow, Maze.ExitCol, GetPositionRow(), GetPositionCol())) {

					System.out.println("Pos: " + GetPositionRow() + " " + GetPositionCol());
					System.out.println("End: " + Maze.ExitRow + " " + Maze.ExitCol);

					System.out.println(Maze.EnemyPathRow.get(1) + " " + Maze.EnemyPathCol.get(1));

					for (int i = 0; i < Maze.EnemyPathCol.size(); i++) {

						System.out.println(Maze.EnemyPathRow.get(i) + " " + Maze.EnemyPathCol.get(i));
					}

					System.out.println();

					for (int i = 0; i < Maze.EnemyRowQueue.size(); i++) {

						System.out.println(Maze.EnemyRowQueue.get(i) + " " + Maze.EnemyColQueue.get(i));
					}

					int index = Maze.EnemyPathCol.size() - 2;

					System.out.print("The Compass responds: ");

					// if the path row is above or below, or if the col is above below, move to that

					if (Maze.EnemyPathCol.get(index) > GetPositionCol()) {

						System.out.println("Right");

					} else if (Maze.EnemyPathCol.get(index) < GetPositionCol()) {

						System.out.println("Left");
					} else if (Maze.EnemyPathRow.get(index) > GetPositionRow()) {

						System.out.println("Down");

					} else if (Maze.EnemyPathRow.get(index) < GetPositionRow()) {

						System.out.println("Up");

					}

				} else {

					System.out.println("The compass appears to be broken");
				}

				System.out.println();
			}

		} else {

			System.out.println("You cannot proform any actions, try finding a treasure chest");

		}

		// sc.close();

		AskPrompt();
	}

	// Prompts player
	public static void AskPrompt() {

		Scanner sc = new Scanner(System.in);

		System.out.print("Do You Want To do a Action or Move or End-Turn(A/M/E): ");

		String AME = sc.next();

		// sc.close();

		char AMEChar = AME.charAt(0);

		// action
		if (AMEChar == 'A' || AMEChar == 'a') {
			PromptAction();
		}

		// Move
		if (AMEChar == 'M' || AMEChar == 'm') {
			PromptMove();
		}

		// sc.close();

	}

}