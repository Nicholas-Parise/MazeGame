/*
* Nicholas Parise
* ICS4U
* Maze Game
*/

public class GameLogic{
	
	public int Level = 0;

	//Checks if player is on a chest
	public static void CheckChest(Chest chest, Player player) {
	
		if(chest.GetPositionCol() == player.GetPositionCol() && chest.GetPositionRow() == player.GetPositionRow()){

			chest.GiveTreasure(player);

			System.out.println("You found a " + chest.CurrentTreasure() + " in a chest");
		}
	}
	
	//checks if player is on an exit
	public static void CheckExit(Player player) {
	
		if(Maze.ExitCol == player.GetPositionCol() && Maze.ExitRow == player.GetPositionRow()){

			player.Win = true;
		}else{

			player.Win = false;
		}
	}	
	
	//checks if player is on enemy
	public static Boolean CheckDead(Player player, Enemy enemy) {

		if((!enemy.Dead) && enemy.GetPositionCol() == player.GetPositionCol() && enemy.GetPositionRow() == player.GetPositionRow()){

			player.Dead = true;
			return true;
		}
		return false;
	}

	
	

}