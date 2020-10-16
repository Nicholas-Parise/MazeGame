/*
* Nicholas Parise
* ICS4U
* Maze Game
*/

public class Enemy extends Entity{
	
	
	public static int CurrentRow = 0;
	public static int CurrentCol = 0;
	public static boolean Dead = false;
	
int TurnTillSpawn;
int SpawnRow;
int SpawnCol;
int Health = 2;

private static Boolean Spawn = true;


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


public static Boolean SpawnMonster(int Turn){

    if(Spawn && Turn >5 && Dead){

        SetPosition(0, 0);
        Spawn = false;
        Dead = false;
        return true;
    }
    return false;

}


public static void NextPosition(Player player){

 
    Maze.ResetVar();

    if(Maze.SolveMaze(player.GetPositionRow(), player.GetPositionCol(),GetPositionRow(), GetPositionCol())){

        int NextRow = 0;
        int NextCol = 0;

        int index = Maze.EnemyRowQueue.size()-2;

        NextCol = Maze.EnemyColQueue.get(index);
        NextRow = Maze.EnemyRowQueue.get(index);


        SetPosition(NextRow, NextCol);
    }



}


}