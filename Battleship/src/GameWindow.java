import java.awt.*;
import javax.swing.*;

public class GameWindow extends JFrame {
	
	JFrame frame;
	JPanel oceanPanel;
	JButton[][] ocean = new JButton[10][10];
	JButton[][] target = new JButton[10][10];

  public GameWindow() {
      startUp();
	  //super("Battleship");
      //initOcean();
  }
  
  public void startUp() {
	  frame = new JFrame("Battleship");
	  initOcean();
	  frame.setVisible(true);
	  frame.setSize(1280, 720);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void initOcean() {
	  
	oceanPanel = new JPanel();
	oceanPanel.setBounds(0, 0, 500, 500);
    oceanPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    
    //oceanPanel.setBackground(Color.blue);
    
    //this.getContentPane().setLayout(new GridLayout(10, 10));
    for (int i=0; i<10; i++) {
    	for (int j=0; j<10; j++) {
    		ocean[i][j] = new JButton(" ");
    		ocean[i][j].setText(" ");
    		ocean[i][j].setBounds(20, 20);
    		ocean[i][j].setForeground(Color.red);
    		
    		c.gridx = i;
    		c.gridy = j;
    		oceanPanel.add(ocean[i][j], c);
    		
    	}
    }
    frame.add(oceanPanel, BorderLayout.NORTH);
    // test
  }

}