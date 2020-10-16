/*
* Nicholas Parise
* ICS4U
* Maze Game
*/

public class Entity{

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



}