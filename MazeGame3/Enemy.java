/*
* Nicholas Parise
* ICS4U
* Maze Game
*/
//This class controls the monster
public class Enemy extends Entity {

    public static int CurrentRow = 0;
    public static int CurrentCol = 0;
    public static boolean Dead = false;

    int TurnTillSpawn;
    int SpawnRow;
    int SpawnCol;

    private static Boolean Spawn = true;
    private static int BadPath = 0;

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

    // resets nessisary variables to spawn
    public static void resetMonster() {

        Spawn = true;
        BadPath = 0;
        SetPosition(0, 0);
        Dead = true;
    }

    // Spawns monster when the turn threshold is passed
    public static void SpawnMonster(int Turn) {

        if (Spawn && Turn > 5 && Dead) {

            SetPosition(0, 0);
            Spawn = false;
            Dead = false;
            System.out.println("The Monster has spawned");

        }

    }

    // uses pathfinding to move the monster closer to the player
    public static void NextPosition(Player player) {

        Maze.ResetVar();

        int index = 0;

        if (Maze.SolveMaze(player.GetPositionRow(), player.GetPositionCol(), GetPositionRow(), GetPositionCol())) {

            // if could find player

            int NextRow = 0;
            int NextCol = 0;

            index = Maze.EnemyRowQueue.size() - 2;

            if (index > 0) {
                NextCol = Maze.EnemyColQueue.get(index);
                NextRow = Maze.EnemyRowQueue.get(index);
                System.out.println("Enemy Moved");
                SetPosition(NextRow, NextCol);
            } else {
                index = Maze.EnemyRowQueue.size() - 1;

                if (index > 0) {
                    NextCol = Maze.EnemyColQueue.get(index);
                    NextRow = Maze.EnemyRowQueue.get(index);
                    System.out.println("Enemy Moved");
                    SetPosition(NextRow, NextCol);
                }else{
            
                    index = Maze.EnemyRowQueue.size();

                    NextCol = Maze.EnemyColQueue.get(index);
                    NextRow = Maze.EnemyRowQueue.get(index);
                    System.out.println("Enemy Moved");
                    SetPosition(NextRow, NextCol);
                    System.out.println("Enemy Moved");
                }
            }

        // move towards player
        } else {

            if (BadPath < 5) {

                // if could not solve maze
                BadPath++;
                System.out.println("Enemy did not move");
            } else {

                index = player.LastRow.size() - 2;

                if (index > 0) {

                    // if the pathfinding can't find the player than the monster will teleport behind him

                    SetPosition(player.LastRow.get(index), player.LastCol.get(index));
                    BadPath = 0;

                    System.out.println("The Monster teleported to your previous position");
                } else {
                    System.out.println("Enemy did not move");
                }

            }

        }

    }

}