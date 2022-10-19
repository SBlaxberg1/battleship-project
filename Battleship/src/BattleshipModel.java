import java.io.IOException;
import java.util.Scanner;

// (Model) A data model containing all information used in the game.

public class BattleshipModel 
{

	private Grid ServerGrid;
	private Grid ClientGrid;
	private GameState state;
	
	BattleshipModel()
	{
		ServerGrid = new Grid();
		ClientGrid = new Grid();
		state = new GameState(2); // starts the game in Setup Mode
		//TODO: Move to separate class
				Scanner inp = new Scanner(System.in);  // Create a Scanner object
			    System.out.println("Enter 0 to be the Server, or 1 to be the client");
			    int stateInput = inp.nextInt();  // Read user input
			    Player localPlayer;
			    if (stateInput == 0)
			    {
			    	localPlayer = new PlayerServer();
			    } 
			    else if (stateInput == 1)
			    {
			   		localPlayer = new PlayerClient();
			   	}
			    else
			    {
			    	System.out.println("Invalid input. See ya");
			    }
	}
	
}
