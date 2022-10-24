import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

// (View) Provides the GUI and displays information to the user.

public class BattleshipView 
{
	private ServerGameWindow sGameWindow;
	private ClientGameWindow cGameWindow;
	private BattleshipModel b_model;
	
	BattleshipView(BattleshipModel model) throws IOException
	{
		b_model = model;
		System.out.println("Enter an IP to join an existing game, or type -1 to host your own!");
		Scanner inp = new Scanner(System.in);
		String input = inp.nextLine();
		
		if (input.equals("-1"))
		{
			sGameWindow = new ServerGameWindow();
			b_model.runSetup();
			sGameWindow.runServer();
		} else
		{
			cGameWindow = new ClientGameWindow();
			b_model.runSetup();
			cGameWindow.runClient(input);
		}
		inp.close();
	}
}
