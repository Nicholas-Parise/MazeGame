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

  public static int SmallestDistance(int Start, int Solution) {

    int Number;

    if (Start > Solution) {

      Number = Start - Solution;

    } else {

      Number = Solution - Start;
    }

    return Number;
  }

  public static boolean SolveMaze(int row, int col, int solutionRow, int solutionCol) {

    // if destination is reached, maze is solved
    // destination is the last cell(maze[SIZE-1][SIZE-1])
    if ((row == solutionRow) && (col == solutionCol)) {
      EnemyMazeSolution[row][col] = 1;
      EnemyPathCol.add(col);
      EnemyPathRow.add(row);

      System.out.println("Made it");

      return true;
    }

    System.out.println("Did Not Make it");

    // checking if we can visit in this cell or not
    // the indices of the cell must be in (0,SIZE-1)
    // and solution[row][c] == 0 is making sure that the cell is not already visited
    // maze[row][c] == 0 is making sure that the cell is not blocked

    if (row >= 0 && col >= 0 && row < 30 && col < 30 && EnemyMazeSolution[row][col] == 0) {
      // if(r>=0 && c>=0 && r<SIZEY && c<SIZEX) {

      // if safe to visit then visit the cell
      EnemyMazeSolution[row][col] = 1;

      EnemyPathCol.add(col);
      EnemyPathRow.add(row);

      //int BaseRow = SmallestDistance(row, solutionRow);
      //int BaseCol = SmallestDistance(col, solutionCol);
      //int BaseScore = (BaseRow * BaseRow) + (BaseCol * BaseCol);

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

      System.out.println(CanGoUp + " " + CanGoRight + " " + CanGoDown + " " + CanGoLeft);

      // "Did Go: ";

      if (CanGoUp == true && lowest == 0) {
        // "Up ";
        if (SolveMaze((row - 1), col, solutionCol, solutionRow))
          return true;

      } else if (CanGoLeft == true && lowest == 1) {
        // "Left ";
        if (SolveMaze(row, (col - 1), solutionCol, solutionRow))
          return true;

      } else if (CanGoDown == true && lowest == 2) {

        // "Down ";
        if (SolveMaze((row + 1), col, solutionCol, solutionRow))
          return true;

      } else if (CanGoRight == true && lowest == 3) {
        // "Right ";
        if (SolveMaze(row, (col + 1), solutionCol, solutionRow))
          return true;

      } else {

        if (CanGoUp == true && SecondLowest == 0) {
          // "Up ";
          if (SolveMaze((row - 1), col, solutionCol, solutionRow))
            return true;

        } else if (CanGoLeft == true && SecondLowest == 1) {

          // "Left "<<c-1;
          if (SolveMaze(row, (col - 1), solutionCol, solutionRow))
            return true;

        } else if (CanGoDown == true && SecondLowest == 2) {
          // "Down ";
          if (SolveMaze((row + 1), col, solutionCol, solutionRow))
            return true;

        } else if (CanGoRight == true && SecondLowest == 3) {

          // "Right "<<c+1;
          if (SolveMaze(row, (col + 1), solutionCol, solutionRow))
            return true;

        } else {

          if (CanGoUp == true && ThridLowest == 0) {

            // "Up ";
            if (SolveMaze((row - 1), col, solutionCol, solutionRow))
              return true;

          } else if (CanGoLeft == true && ThridLowest == 1) {
            // "Left "<<c-1;
            if (SolveMaze(row, (col - 1), solutionCol, solutionRow))
              return true;

          } else if (CanGoDown == true && ThridLowest == 2) {

            // "Down ";
            if (SolveMaze((row + 1), col, solutionCol, solutionRow))
              return true;

          } else if (CanGoRight == true && ThridLowest == 3) {
            // "Right "<<c+1;
            if (SolveMaze(row, (col + 1), solutionCol, solutionRow))
              return true;
          } else {

            if (CanGoUp == true && Highest == 0) {
              // "Up ";
              if (SolveMaze((row - 1), col, solutionCol, solutionRow))
                return true;

            } else if (CanGoLeft == true && Highest == 1) {
              // "Left ";
              if (SolveMaze(row, (col - 1), solutionCol, solutionRow))
                return true;

            } else if (CanGoDown == true && Highest == 2) {

              // "Down ";
              if (SolveMaze((row + 1), col, solutionCol, solutionRow))
                return true;

            } else if (CanGoRight == true && Highest == 3) {
              // "Right ";
              if (SolveMaze(row, (col + 1), solutionCol, solutionRow))
                return true;

            } else {

              if (CanGoDown == false && CanGoUp == false && CanGoLeft == false && CanGoRight == true) {

                // "Right ";
                if (SolveMaze(row, col + 1, solutionCol, solutionRow))
                  return true;
              }

              if (CanGoDown == false && CanGoUp == false && CanGoLeft == true && CanGoRight == false) {

                // "Left ";
                if (SolveMaze(row, col - 1, solutionCol, solutionRow))
                  return true;
              }

              if (CanGoDown == false && CanGoUp == true && CanGoLeft == false && CanGoRight == false) {

                // "Up ";
                if (SolveMaze(row - 1, col, solutionCol, solutionRow))
                  return true;
              }

              if (CanGoDown == true && CanGoUp == false && CanGoLeft == false && CanGoRight == false) {

                // "Down "
                if (SolveMaze(row + 1, col, solutionCol, solutionRow))
                  return true;
              }
            }
          }
        }
      }
    }
    // backtracking
    // Not ready yet
    /*
     * int index = EnemyRowQueue.size() - 1;
     * 
     * EnemyRowQueue.remove(index); EnemyColQueue.remove(index);
     * 
     * index = EnemyRowQueue.size() - 1;
     * 
     * row = EnemyRowQueue.get(index); col = EnemyColQueue.get(index);
     */

    return false;

  }

  public static int CanGo(int row, int col) {

    Random rand = new Random();

    int TryWhere = rand.nextInt(4);

    // System.out.println("------------------------------------------------------------------------------------");

    // System.out.println();
    // System.out.println(TryWhere);

    Boolean CanGoUp = false;
    Boolean CanGoLeft = false;
    Boolean CanGoDown = false;
    Boolean CanGoRight = false;

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

    // System.out.println(CanGoUp + " " + CanGoLeft + " " + CanGoDown + " " +
    // CanGoRight);

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

    return 4;
  }

  public static void MazeMaker() {

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

    int JUSTWORK = 0;

    MazeMatrix[0][0] = 1;

    do {

      whichWay = CanGo(row, col);

      // System.out.println(whichWay);

      if (whichWay == 0) {
        // Up

        WhereWallDown[row - 1][col] = false;
        WhereWallUp[row][col] = false;

        MazeMatrix[row - 1][col] = 1;

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

        int index = RowQueue.size() - 1;

        RowQueue.remove(index);
        ColQueue.remove(index);

        index = RowQueue.size() - 1;

        // System.out.println("asdasdasdasdasd " + RowQueue.get(index) + " " +
        // ColQueue.get(index) + " " + index);

        if (index > 0) {

          row = RowQueue.get(index);
          col = ColQueue.get(index);

        } else {
          JUSTWORK = 1000;
          break;
        }

      }
      /*
       * if(!STOPINPUT.equals("N")){ System.out.println(row + " " + col);
       * System.out.println();
       * 
       * 
       * for (int i = 0; i < 30; i++) { for (int j = 0; j < 30; j++) {
       * System.out.print(MazeMatrix[i][j]+" "); } System.out.println(); }
       * 
       * 
       * System.out.println(); STOPINPUT = System.console().readLine();
       * System.out.println(); }
       */

      JUSTWORK++;

    } while (JUSTWORK < 10000);
    // }while(row!=0 && col !=0);

  }

  public static void PrintMaze() {

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

            System.out.print("0");

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

    // System.out.println(row+" "+col);
  }

}
