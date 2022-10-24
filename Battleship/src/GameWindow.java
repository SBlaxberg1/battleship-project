//Part of BattleshipView.java

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

// (View) Creates the Game Window and GUI

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	JFrame frame;
	JPanel primaryPanel;
	JPanel serverPanel;
	JPanel clientPanel;
	JPanel serverShips;
	JPanel clientShips;
	JButton[][] serverGrid = new JButton[10][10];
	JButton[][] clientGrid = new JButton[10][10];

  public GameWindow() throws IOException{
      startUp();
  }
  
  public void startUp() throws IOException{
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

 public void initServerGrid()
 {
	 
 }
  
  public void initClientGrid()
  {
	  
  }
  
  public void initServerShips() throws IOException{
	  serverShips = new JPanel();
	  serverShips.setLayout(new GridLayout(3, 1));
	  serverShips.setBorder(BorderFactory.createLineBorder(Color.red));
	  
	  JTextArea enemyRemaining = new JTextArea("Enemy ships remaining:");
	  serverShips.add(enemyRemaining);
	  
	  JPanel topShips = new JPanel();
	  topShips.setLayout(new GridLayout(1, 3));
	  JPanel bottomShips = new JPanel();
	  bottomShips.setLayout(new GridLayout(1, 2));
	  
	  BufferedImage carrier = ImageIO.read(new File("Images/Carrier.png"));
	  ImageIcon carrierIcon = new ImageIcon(carrier);
	  Image icon1 = carrierIcon.getImage();
	  Image resize1 = icon1.getScaledInstance(200,  75,  java.awt.Image.SCALE_SMOOTH);
	  carrierIcon = new ImageIcon(resize1);
	  JLabel carrierLabel = new JLabel(carrierIcon);
	  
	  BufferedImage bship = ImageIO.read(new File("Images/BShip.png"));
	  ImageIcon bshipIcon = new ImageIcon(bship);
	  Image icon2 = bshipIcon.getImage();
	  Image resize2 = icon2.getScaledInstance(200,  75,  java.awt.Image.SCALE_SMOOTH);
	  bshipIcon = new ImageIcon(resize2);
	  JLabel bshipLabel = new JLabel(bshipIcon);
	  
	  BufferedImage cruiser = ImageIO.read(new File("Images/Cruiser.png"));
	  ImageIcon cruiserIcon = new ImageIcon(cruiser);
	  Image icon3 = cruiserIcon.getImage();
	  Image resize3 = icon3.getScaledInstance(200,  75,  java.awt.Image.SCALE_SMOOTH);
	  cruiserIcon = new ImageIcon(resize3);
	  JLabel cruiserLabel = new JLabel(cruiserIcon);
	  
	  BufferedImage submarine = ImageIO.read(new File("Images/Submarine.png"));
	  ImageIcon subIcon = new ImageIcon(submarine);
	  Image icon4 = subIcon.getImage();
	  Image resize4 = icon4.getScaledInstance(200,  75,  java.awt.Image.SCALE_SMOOTH);
	  subIcon = new ImageIcon(resize4);
	  JLabel subLabel = new JLabel(subIcon);
	  
	  BufferedImage destroyer = ImageIO.read(new File("Images/Destroyer.png"));
	  ImageIcon destroyerIcon = new ImageIcon(destroyer);
	  Image icon5 = destroyerIcon.getImage();
	  Image resize5 = icon5.getScaledInstance(200,  75,  java.awt.Image.SCALE_SMOOTH);
	  destroyerIcon = new ImageIcon(resize5);
	  JLabel destroyerLabel = new JLabel(destroyerIcon);

	  topShips.add(carrierLabel);
	  topShips.add(bshipLabel);
	  topShips.add(cruiserLabel);
	  bottomShips.add(subLabel);
	  bottomShips.add(destroyerLabel);
	  serverShips.add(topShips);
	  serverShips.add(bottomShips);
	  
	  primaryPanel.add(serverShips);
  }
  
  public void initClientShips() throws IOException {
	  clientShips = new JPanel();
	  clientShips.setLayout(new GridLayout(3, 1));
	  clientShips.setBorder(BorderFactory.createLineBorder(Color.blue));
	  
	  JTextArea friendlyRemaining = new JTextArea("Friendly ships remaining:");
	  clientShips.add(friendlyRemaining);
	  
	  JPanel topShips = new JPanel();
	  topShips.setLayout(new GridLayout(1, 3));
	  JPanel bottomShips = new JPanel();
	  bottomShips.setLayout(new GridLayout(1, 2));
	 
	  BufferedImage carrier = ImageIO.read(new File("Images/Carrier.png"));
	  ImageIcon carrierIcon = new ImageIcon(carrier);
	  Image icon1 = carrierIcon.getImage();
	  Image resize1 = icon1.getScaledInstance(200,  75,  java.awt.Image.SCALE_SMOOTH);
	  carrierIcon = new ImageIcon(resize1);
	  JLabel carrierLabel = new JLabel(carrierIcon);
	  
	  BufferedImage bship = ImageIO.read(new File("Images/BShip.png"));
	  ImageIcon bshipIcon = new ImageIcon(bship);
	  Image icon2 = bshipIcon.getImage();
	  Image resize2 = icon2.getScaledInstance(200,  75,  java.awt.Image.SCALE_SMOOTH);
	  bshipIcon = new ImageIcon(resize2);
	  JLabel bshipLabel = new JLabel(bshipIcon);
	  
	  BufferedImage cruiser = ImageIO.read(new File("Images/Cruiser.png"));
	  ImageIcon cruiserIcon = new ImageIcon(cruiser);
	  Image icon3 = cruiserIcon.getImage();
	  Image resize3 = icon3.getScaledInstance(200,  75,  java.awt.Image.SCALE_SMOOTH);
	  cruiserIcon = new ImageIcon(resize3);
	  JLabel cruiserLabel = new JLabel(cruiserIcon);
	  
	  BufferedImage submarine = ImageIO.read(new File("Images/Submarine.png"));
	  ImageIcon subIcon = new ImageIcon(submarine);
	  Image icon4 = subIcon.getImage();
	  Image resize4 = icon4.getScaledInstance(200,  75,  java.awt.Image.SCALE_SMOOTH);
	  subIcon = new ImageIcon(resize4);
	  JLabel subLabel = new JLabel(subIcon);
	  
	  BufferedImage destroyer = ImageIO.read(new File("Images/Destroyer.png"));
	  ImageIcon destroyerIcon = new ImageIcon(destroyer);
	  Image icon5 = destroyerIcon.getImage();
	  Image resize5 = icon5.getScaledInstance(200,  75,  java.awt.Image.SCALE_SMOOTH);
	  destroyerIcon = new ImageIcon(resize5);
	  JLabel destroyerLabel = new JLabel(destroyerIcon);
	  
	  topShips.add(carrierLabel);
	  topShips.add(bshipLabel);
	  topShips.add(cruiserLabel);
	  bottomShips.add(subLabel);
	  bottomShips.add(destroyerLabel);
	  clientShips.add(topShips);
	  clientShips.add(bottomShips);
	  
	  primaryPanel.add(clientShips);
  }
  
}