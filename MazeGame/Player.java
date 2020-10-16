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

	public static int SwordDurr = 3;

	public static boolean HasTorch = false;
	// if the player has torch he can see more maze

	public static boolean HasCompass = true;
	// if the player has compass he can ask for directions

	public static boolean Win = false;

	
	public static boolean OnChest = false;
	

/*
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
*/
	
	
	//Ask move
	public static void PromptMove(Player player) {

		Scanner sc = new Scanner(System.in);

		// displays avaliable directions

		System.out.print("Do You Want To Go: ");

		if (Maze.WhereWallUp[player.GetPositionRow()][player.GetPositionCol()] == false) {
			System.out.print("Up,");
		}
		if (Maze.WhereWallRight[player.GetPositionRow()][player.GetPositionCol()] == false) {
			System.out.print("Right,");
		}
		if (Maze.WhereWallDown[player.GetPositionRow()][player.GetPositionCol()] == false) {
			System.out.print("Down,");
		}
		if (Maze.WhereWallLeft[player.GetPositionRow()][player.GetPositionCol()] == false) {
			System.out.print("Left");
		}

		System.out.print(" (");

		if (Maze.WhereWallUp[player.GetPositionRow()][player.GetPositionCol()] == false) {
			System.out.print("U,");
		}
		if (Maze.WhereWallRight[player.GetPositionRow()][player.GetPositionCol()] == false) {
			System.out.print("R,");
		}
		if (Maze.WhereWallDown[player.GetPositionRow()][player.GetPositionCol()] == false) {
			System.out.print("D,");
		}
		if (Maze.WhereWallLeft[player.GetPositionRow()][player.GetPositionCol()] == false) {
			System.out.print("L,");
		}

		System.out.print("): ");

		String Input = sc.next();
		
		//sc.close();

		char InputChar = Input.charAt(0);

		// displays avaliable directions 

		if ((InputChar == 'U' || InputChar == 'u')&& Maze.WhereWallUp[player.GetPositionRow()][player.GetPositionCol()] == false) {

			SetPosition(GetPositionRow()-1, GetPositionCol());
		}

		if ((InputChar == 'R' || InputChar == 'r')&& Maze.WhereWallRight[player.GetPositionRow()][player.GetPositionCol()] == false) {

			SetPosition(GetPositionRow(), GetPositionCol()+1);
		}

		if ((InputChar == 'D' || InputChar == 'd')&& Maze.WhereWallDown[player.GetPositionRow()][player.GetPositionCol()] == false) {

			SetPosition(GetPositionRow()+1, GetPositionCol());
		}

		if ((InputChar == 'L' || InputChar == 'l')&& Maze.WhereWallLeft[player.GetPositionRow()][player.GetPositionCol()] == false) {

			SetPosition(GetPositionRow(), GetPositionCol()-1);
		}
	}


	public static void PromptAction(Player player) {

		Scanner sc = new Scanner(System.in);

		if(HasSword || HasTorch || HasCompass){


		System.out.print("Do You Want To use: ");

		if(HasSword)
		System.out.print("Sword,");

		if(HasTorch)
		System.out.print("Torch,");

		if(HasCompass)
		System.out.print("Compass");
		

		
		System.out.print("(");

		if(HasSword)
		System.out.print("S,");

		if(HasTorch)
		System.out.print("T,");

		if(HasCompass)
		System.out.print("C");


		
		System.out.print("): ");
	

		String Input = sc.next();
		
		char InputChar = Input.charAt(0);

		
	
		
		
			if(InputChar == 'C'||InputChar == 'c' && HasCompass){


				Maze.ResetVar();

			//	if(Maze.SolveMaze(player.GetPositionRow(), player.GetPositionCol(), Maze.ExitRow, Maze.ExitCol)){

				if(Maze.SolveMaze(Maze.ExitRow, Maze.ExitCol,player.GetPositionRow(), player.GetPositionCol())){

				System.out.println("Pos: "+GetPositionRow()+" "+GetPositionCol());
				System.out.println("End: "+Maze.ExitRow+" "+Maze.ExitCol);


				System.out.println(Maze.EnemyPathRow.get(1)+" "+Maze.EnemyPathCol.get(1) );


				for(int i = 0; i<Maze.EnemyPathCol.size();i++){


					System.out.println(Maze.EnemyPathRow.get(i)+" "+Maze.EnemyPathCol.get(i));
				}

				System.out.println();

				for(int i = 0; i<Maze.EnemyRowQueue.size();i++){

					System.out.println(Maze.EnemyRowQueue.get(i)+" "+Maze.EnemyColQueue.get(i));
				}


				//int index = Maze.EnemyRowQueue.size()-2;

				int index = Maze.EnemyPathCol.size()-2;

				System.out.print("The Compass responds: ");

				if(Maze.EnemyPathCol.get(index) >GetPositionCol()){

					System.out.println("Right");

				}else if(Maze.EnemyPathCol.get(index) <GetPositionCol()){

					System.out.println("Left");
				}else if(Maze.EnemyPathRow.get(index) > GetPositionRow()){

					System.out.println("Down");

				}else if(Maze.EnemyPathRow.get(index) < GetPositionRow()){

					System.out.println("Up");					

				}
			
				}else{

				System.out.println("The compass appears to be broken");

				}



				System.out.println();

			}


	}else{

			System.out.println("You cannot proform any actions, try finding a treasure chest");

		}


		//sc.close();

	}

	// Prompts player
	public static void AskPrompt(Player player) {

		Scanner sc = new Scanner(System.in);
		
		System.out.print("Do You Want To do a Action or Move or End-Turn(A/M/E): ");

		String AME = sc.next();
		
	//	sc.close();

		char AMEChar = AME.charAt(0);

		//action
		if (AMEChar == 'A' || AMEChar == 'a') {
			PromptAction(player);
		}

		//Move
		if (AMEChar == 'M' || AMEChar == 'm') {
			PromptMove(player);
		}

		//sc.close();

	}

}