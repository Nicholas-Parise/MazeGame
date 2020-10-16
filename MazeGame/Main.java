/*
* Nicholas Parise
* ICS4U
* Maze Game
*/

//Dont go all out, just do something small

import javax.swing.*;
import java.awt.*;

public class Main extends Canvas {

  public static void main(String[] args) {

    Player player = new Player();
    Enemy enemy = new Enemy();
    Chest chest = new Chest();

    int Turn = 0;

    // Vehicle vehicle = car; // upcasting
    // Car car2 = (Car)vechile; //downcasting

    Maze.MazeMatrix = new int[30][30];
    Maze.EnemyMazeSolution = new int[30][30];

    Maze.WhereWallUp = new Boolean[30][30];
    Maze.WhereWallRight = new Boolean[30][30];
    Maze.WhereWallDown = new Boolean[30][30];
    Maze.WhereWallLeft = new Boolean[30][30];

    do {

      player.SetPosition(0, 0);
      enemy.SetPosition(0, 0);
      chest.SetPosition(0, 1);

      player.Win = false;
      player.Dead = false;
      player.HasCompass = false;
      player.HasSword = false;
      player.HasTorch = false;

      enemy.Dead = true;
      enemy.Health = 3;
      chest.ChestOpened = false;

      for (int i = 0; i < 30; i++) {
        for (int j = 0; j < 30; j++) {

          Maze.MazeMatrix[i][j] = 0;
          Maze.EnemyMazeSolution[i][j] = 0;

          Maze.WhereWallUp[i][j] = true;
          Maze.WhereWallRight[i][j] = true;
          Maze.WhereWallDown[i][j] = true;
          Maze.WhereWallLeft[i][j] = true;
        }
      }

      Maze.MazeMaker();
      Maze.FindExit();
      // chest.PlaceTreasure();

      Turn = 0;

      do {

        // Full maze
         Maze.PrintMaze(player);

        // Draws a small manageable portion of maze
       // Maze.SelectiveMazeDraw(player.GetPositionRow(), player.GetPositionCol(), enemy, chest);

        Maze.EnemyPathCol.clear();
        Maze.EnemyPathRow.clear();
        Maze.EnemyRowQueue.clear();
        Maze.EnemyColQueue.clear();

        for (int k = 0; k < 30; k++) {
          for (int l = 0; l < 30; l++) {

            Maze.EnemyMazeSolution[k][l] = 0;
          }
        }

        if (Maze.SolveMaze(player.GetPositionRow(), player.GetPositionCol(), enemy.GetPositionRow(),
            enemy.GetPositionCol())) {

          System.out.println("True");
        } else {

          System.out.println("False");
          // return;
        }

        GameLogic.CheckExit(player);

        if (GameLogic.CheckChest(chest, player)) {
          System.out.println("You found a " + chest.CurrentTreasure() + " in a chest");
        }

        if (GameLogic.CheckDead(player, enemy)) {

          if (Turn > 5) {
            enemy.NextPosition(player);
          }

        }

        if (enemy.SpawnMonster(Turn)) {
          System.out.println("The Monster has spawned");
        }

        Turn++;

        System.out.println("enemy " + enemy.GetPositionRow() + " " + enemy.GetPositionCol());

        player.AskPrompt(player);

      } while (!player.Dead && !player.Win);

      if (player.Dead) {

        System.out.println("The monster has killed you");
      } else {
        System.out.println("You have found the exit");
      }

    } while (player.Win);

    System.out.println("You Have Died");

    /*
     * JFrame frame = new JFrame("My Drawing"); Canvas canvas = new Main();
     * canvas.setSize(400, 400); frame.add(canvas); frame.pack();
     * frame.setVisible(true);
     */

  }

  public void paint(Graphics g) {

    g.setColor(Color.BLUE);
    g.fillOval(0, 100, 200, 200);

    g.setColor(Color.ORANGE);
    g.fillOval(200, 100, 200, 200);

    // StdDraw[][] squares;

    // StdDraw.setScale(0,4);

    int[][] grid = new int[4][4];

    for (int x = 0; x < grid.length; x++) {
      for (int y = 0; y < grid.length; y++) {
        grid[x][y] = 255;
      }
    }

    for (int x = 0; x < grid.length; x++) {
      for (int y = 0; y < grid.length; y++) {
        // StdDraw.square(x, y, 1);
      }
    }

  }

}