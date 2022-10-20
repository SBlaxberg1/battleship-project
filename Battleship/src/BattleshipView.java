import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

// (View) Provides the GUI and displays information to the user.

public class BattleshipView 
{
	private GameWindow gameWindow;
	private BattleshipModel b_model;
	
	BattleshipView(BattleshipModel model) throws IOException
	{
		b_model = model;
		System.out.println("-1 for host, ip for join");
		Scanner inp = new Scanner(System.in);
		String input = inp.nextLine();
		
		//gameWindow = new GameWindow();
		if (input.equals("-1"))
		{
			Server application = new Server(); // create server
			application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			application.runServer(); // run server application
		} else
		{
			Client application = new Client(input); // use args to connect
		    application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		    application.runClient(); // run client application
		}
	}
}
