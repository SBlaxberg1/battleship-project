//Part of BattleshipView.java

import java.awt.*;
import javax.swing.*;

// (View) Creates the Game Window and GUI

public class GameWindow extends JFrame {
	JFrame frame;
	JPanel primaryPanel;
	JPanel serverPanel;
	JPanel clientPanel;
	JPanel serverShips;
	JPanel clientShips;
	JButton[][] serverGrid = new JButton[10][10];
	JButton[][] clientGrid = new JButton[10][10];

  public GameWindow() {
      startUp();
  }
  
  public void startUp() {
	  frame = new JFrame("Battleship");
	  primaryPanel = new JPanel();
	  primaryPanel.setLayout(new GridLayout(2, 2));
	  initServerGrid();
	  initServerShips();
	  initClientGrid();
	  initClientShips();
	  frame.add(primaryPanel);
	  frame.setVisible(true);
	  frame.setSize(1280, 720);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void initServerGrid() {
	  
	serverPanel = new JPanel();
	serverPanel.setBounds(0, 0, 500, 500);
	serverPanel.setLayout(new GridBagLayout());
	serverPanel.setBorder(BorderFactory.createLineBorder(Color.red));
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    
    ImageIcon reticle = new ImageIcon("reticle.png");
    Image icon = reticle.getImage();
    Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
    reticle = new ImageIcon(resize);
    
    for (int i=0; i<10; i++) {
    	for (int j=0; j<10; j++) {
    		serverGrid[i][j] = new JButton(reticle);
    		serverGrid[i][j].setBounds(0, 0, 22, 22);
    		
    		c.gridx = i;
    		c.gridy = j;
    		serverPanel.add(serverGrid[i][j], c);
    		
    	}
    }
    primaryPanel.add(serverPanel);
  }
  
  public void initClientGrid(){
	  clientPanel = new JPanel();
	  clientPanel.setBounds(0,0, 500, 500);
	  clientPanel.setLayout(new GridBagLayout());
	  clientPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
	  GridBagConstraints c = new GridBagConstraints();
	  c.fill = GridBagConstraints.HORIZONTAL;
	  
	  ImageIcon ocean = new ImageIcon("ocean_empty.png");
	  Image icon = ocean.getImage();
	  Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
	  ocean = new ImageIcon(resize);
	  
	  for (int i=0; i<10; i++) {
		  for (int j=0; j<10; j++) {
			  clientGrid[i][j] = new JButton(ocean);
			  clientGrid[i][j].setBounds(0, 0, 22, 22);
			  
			  c.gridx = i;
			  c.gridy = j;
			  clientPanel.add(clientGrid[i][j], c);
		  }
	  }
	  primaryPanel.add(clientPanel);
  }
  
  public void initServerShips() {
	  serverShips = new JPanel();
	  serverShips.setLayout(new GridLayout(3, 1));
	  serverShips.setBorder(BorderFactory.createLineBorder(Color.red));
	  
	  JTextArea enemyRemaining = new JTextArea("Enemy ships remaining:");
	  serverShips.add(enemyRemaining);
	  
	  JPanel topShips = new JPanel();
	  topShips.setLayout(new GridLayout(1, 3));
	  JPanel bottomShips = new JPanel();
	  bottomShips.setLayout(new GridLayout(1, 2));
	  JTextArea carrier = new JTextArea("Carrier");
	  JTextArea bship = new JTextArea("Battleship");
	  JTextArea cruiser = new JTextArea("Cruiser");
	  JTextArea submarine = new JTextArea("Submarine");
	  JTextArea destroyer = new JTextArea("Destroyer");
	  topShips.add(carrier);
	  topShips.add(bship);
	  topShips.add(cruiser);
	  bottomShips.add(submarine);
	  bottomShips.add(destroyer);
	  serverShips.add(topShips);
	  serverShips.add(bottomShips);
	  
	  
	  primaryPanel.add(serverShips);
  }
  
  public void initClientShips() {
	  clientShips = new JPanel();
	  clientShips.setLayout(new GridLayout(3, 1));
	  clientShips.setBorder(BorderFactory.createLineBorder(Color.blue));
	  
	  JTextArea friendlyRemaining = new JTextArea("Friendly ships remaining:");
	  clientShips.add(friendlyRemaining);
	  
	  JPanel topShips = new JPanel();
	  topShips.setLayout(new GridLayout(1, 3));
	  JPanel bottomShips = new JPanel();
	  bottomShips.setLayout(new GridLayout(1, 2));
	  JTextArea carrier = new JTextArea("Carrier");
	  JTextArea bship = new JTextArea("Battleship");
	  JTextArea cruiser = new JTextArea("Cruiser");
	  JTextArea submarine = new JTextArea("Submarine");
	  JTextArea destroyer = new JTextArea("Destroyer");
	  topShips.add(carrier);
	  topShips.add(bship);
	  topShips.add(cruiser);
	  bottomShips.add(submarine);
	  bottomShips.add(destroyer);
	  clientShips.add(topShips);
	  clientShips.add(bottomShips);
	  
	  primaryPanel.add(clientShips);
  }

}