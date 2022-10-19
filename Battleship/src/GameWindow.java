//Part of BattleshipView.java

import java.awt.*;
import javax.swing.*;

// (View) Creates the Game Window and GUI

public class GameWindow extends JFrame {
	JFrame frame;
	JPanel serverPanel;
	JPanel clientPanel;
	JButton[][] serverGrid = new JButton[10][10];
	JButton[][] clientGrid = new JButton[10][10];

  public GameWindow() {
      startUp();
  }
  
  public void startUp() {
	  frame = new JFrame("Battleship");
	  initServerGrid();
	  initClientGrid();
	  frame.setVisible(true);
	  frame.setSize(1280, 720);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void initServerGrid() {
	  
	serverPanel = new JPanel();
	serverPanel.setBounds(0, 0, 500, 500);
	serverPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    
    ImageIcon reticle = new ImageIcon("reticle.png");
    Image icon = reticle.getImage();
    Image resize = icon.getScaledInstance(20,  20,  java.awt.Image.SCALE_SMOOTH);
    reticle = new ImageIcon(resize);
    
    for (int i=0; i<10; i++) {
    	for (int j=0; j<10; j++) {
    		serverGrid[i][j] = new JButton(reticle);
    		serverGrid[i][j].setBounds(0, 0, 20, 20);
    		
    		c.gridx = i;
    		c.gridy = j;
    		serverPanel.add(serverGrid[i][j], c);
    		
    	}
    }
    frame.add(serverPanel, BorderLayout.NORTH);
  }
  
  public void initClientGrid(){
	  clientPanel = new JPanel();
	  clientPanel.setBounds(0,0, 500, 500);
	  clientPanel.setLayout(new GridBagLayout());
	  GridBagConstraints c = new GridBagConstraints();
	  c.fill = GridBagConstraints.HORIZONTAL;
	  
	  ImageIcon ocean = new ImageIcon("ocean_empty.png");
	  Image icon = ocean.getImage();
	  Image resize = icon.getScaledInstance(20,  20,  java.awt.Image.SCALE_SMOOTH);
	  ocean = new ImageIcon(resize);
	  
	  for (int i=0; i<10; i++) {
		  for (int j=0; j<10; j++) {
			  clientGrid[i][j] = new JButton(ocean);
			  clientGrid[i][j].setBounds(0, 0, 20, 20);
			  
			  c.gridx = i;
			  c.gridy = j;
			  clientPanel.add(clientGrid[i][j], c);
		  }
	  }
	  frame.add(clientPanel, BorderLayout.SOUTH);
  }

}