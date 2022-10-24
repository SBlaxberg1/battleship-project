// (Model) A data model containing all information used in the game.
import java.util.Scanner;

public class BattleshipModel 
{

	private Setup begin;

	
	BattleshipModel()
	{
		begin = new Setup();
	}
	
	public void runSetup()
	{
		begin.startGame();
	}
	
}
