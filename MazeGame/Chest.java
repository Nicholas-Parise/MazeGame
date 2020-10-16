import java.util.Random;

/*
* Nicholas Parise
* ICS4U
* Maze Game
*/
public class Chest extends Entity {

	private static String CurrentLoot;

	int SpawnRow;
	int SpawnCol;

	public static int CurrentRow = 0;
	public static int CurrentCol = 0;
	public static boolean Dead = false;
	public static boolean ChestOpened = false;

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

	public static void PlaceTreasure() {

		Random rand = new Random();

		int TryRow = 0;
		int TryCol = 0;

		do {

			/*
			 * Maze.EnemyRowQueue.clear(); Maze.EnemyColQueue.clear();
			 * Maze.EnemyPathCol.clear(); Maze.EnemyPathRow.clear();
			 * 
			 * for (int k = 0; k < 30; k++) { for (int l = 0; l < 30; l++) {
			 * 
			 * Maze.EnemyMazeSolution[k][l] = 0; } }
			 */

			Maze.ResetVar();

			TryRow = rand.nextInt(10) + 2;
			TryCol = rand.nextInt(10) + 2;

		} while (Maze.SolveMaze(0, 0, TryRow, TryCol));

		SetPosition(TryRow, TryCol);
		System.out.println("Lalasdasdalsfgjdshfoigjhsdfoigdafoig " + TryRow + " " + TryCol);
	}

	public static String CurrentTreasure() {

		return CurrentLoot;
	}

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