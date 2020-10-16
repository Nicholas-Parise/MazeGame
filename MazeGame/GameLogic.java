/*
* Nicholas Parise
* ICS4U
* Maze Game
*/

public class GameLogic{
	
	public int Level = 0;

	
	public static Boolean CheckChest(Chest chest, Player player) {
	
		if(chest.GetPositionCol() == player.GetPositionCol() && chest.GetPositionRow() == player.GetPositionRow()){

			chest.GiveTreasure(player);

		return true;
		}
		
		return false;
	}
	
	
	public static void CheckExit(Player player) {
	
		if(Maze.ExitCol == player.GetPositionCol() && Maze.ExitRow == player.GetPositionRow()){

			player.Win = true;
			System.out.println("True True True");
		}else{

			player.Win = false;
			System.out.println("False False False");
		}

	}	
	
	
	public static Boolean CheckDead(Player player, Enemy enemy) {

		if((!enemy.Dead) && enemy.GetPositionCol() == player.GetPositionCol() && enemy.GetPositionRow() == player.GetPositionRow()){

			player.Dead = true;
			return true;
		}
		return false;
	}

	
	

}