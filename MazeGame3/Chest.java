import java.util.Random;

/*
* Nicholas Parise
* ICS4U
* Maze Game
*/

//child class
//this handles the chest
public class Chest extends Entity {

	private static String CurrentLoot;
	public static boolean ChestOpened = false;


	public static int CurrentRow = 0;
	public static int CurrentCol = 0;

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

	public static void resetChest() {

		SetPosition(1, 1);
		ChestOpened = false;
		CurrentLoot = "";
	}

	// place's the treasure at a random position
	public static void PlaceTreasure() {

		Random rand = new Random();

		int TryRow = 0;
		int TryCol = 0;

		int ForeverStop = 0;

		do {
			// picks random place for treasure chest
			ForeverStop++;

			Maze.ResetVar();

			TryRow = rand.nextInt(8) + 1;
			TryCol = rand.nextInt(8) + 1;

		} while (!Maze.SolveMaze(0, 0, TryRow, TryCol) && ForeverStop<100);

		SetPosition(TryRow, TryCol);

	}

	// returns current treasure
	public static String CurrentTreasure() {

		return CurrentLoot;
	}

	// Gives treausre to the player
	public static void GiveTreasure(Player player) {

		if (!ChestOpened) {
			if (!player.HasCompass) {

				CurrentLoot = "Compass";
				player.HasCompass = true;

			} else if (!player.HasTorch) {

				CurrentLoot = "Torch";
				player.HasTorch = true;

			} else {

				CurrentLoot = "Sword";
				player.HasSword = true;
				player.SwordDurr = 3;
			}

			ChestOpened = true;
		}

	}
}