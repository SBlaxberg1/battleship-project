import javax.swing.*;

public class GameWindow extends JFrame 
{

  public GameWindow()
    {
        super("Battleship");
        init();
    }

  public void init() 
  {
	  	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	  	this.setSize(1270, 720);
	  
	  	//Testing
	  	Grid g1 = new Grid();
	  	this.add(g1);
		this.setVisible(true);
  }

}