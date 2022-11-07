//Part of BattleshipView.java

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.TransferHandler;
import javax.swing.text.DefaultCaret;

// (View) Creates the Game Window and GUI

@SuppressWarnings("serial")
public abstract class GameWindow extends JFrame {
	JFrame frame;
	JPanel primaryPanel;
	JPanel serverPanel;
	JPanel clientPanel;
	JPanel serverShips;
	JPanel clientShips;
	protected JLabel enemyCarrierLabel;
	protected JLabel enemyBShipLabel;
	protected JLabel enemyCruiserLabel;
	protected JLabel enemySubmarineLabel;
	protected JLabel enemyDestroyerLabel;
	protected JLabel playerCarrierLabel;
	protected JLabel playerBShipLabel;
	protected JLabel playerCruiserLabel;
	protected JLabel playerSubmarineLabel;
	protected JLabel playerDestroyerLabel;
	protected ImgResize imgResize;
	JButton[][] serverGrid = new JButton[10][10];
	JButton[][] clientGrid = new JButton[10][10];
	JTextArea messages;
	protected boolean isVertical = false;
	JButton auto;

  public GameWindow() throws IOException{
      startUp();
  }
  
  public void startUp() throws IOException{
	  frame = new JFrame("Battleship");
	  primaryPanel = new JPanel();
	  primaryPanel.setLayout(new GridLayout(2, 2));
	  imgResize = new ImgResize();
	  initEnemyGrid();
	  initEnemyShips();
	  initPlayerGrid();
	  initPlayerShips();
	  frame.add(primaryPanel);
	  frame.setVisible(true);
	  frame.setSize(1280, 720);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

 public abstract void initEnemyGrid();
 public abstract void initPlayerGrid();
  
  public void initEnemyShips() throws IOException{
	  serverShips = new JPanel();
	  serverShips.setLayout(new GridLayout(3, 1));
	  serverShips.setBorder(BorderFactory.createLineBorder(Color.red));
	  
	  JTextArea enemyRemaining = new JTextArea("Enemy ships remaining:");
	  enemyRemaining.setEditable(false);
	  serverShips.add(enemyRemaining);
	  
	  JPanel topShips = new JPanel();
	  topShips.setLayout(new GridLayout(1, 3));
	  JPanel bottomShips = new JPanel();
	  bottomShips.setLayout(new GridLayout(1, 2));
	  	  
	  BufferedImage carrier = ImageIO.read(new File("Images/Carrier.png"));
	  ImageIcon carrierIcon = imgResize.resize(carrier, 200, 75);
	  enemyCarrierLabel = new JLabel(carrierIcon);
	  
	  BufferedImage bship = ImageIO.read(new File("Images/BShip.png"));
	  ImageIcon bshipIcon = imgResize.resize(bship, 200, 75);
	  enemyBShipLabel = new JLabel(bshipIcon);
	  
	  BufferedImage cruiser = ImageIO.read(new File("Images/Cruiser.png"));
	  ImageIcon cruiserIcon = imgResize.resize(cruiser, 200, 75);
	  enemyCruiserLabel = new JLabel(cruiserIcon);
	  
	  BufferedImage submarine = ImageIO.read(new File("Images/Submarine.png"));
	  ImageIcon submarineIcon = imgResize.resize(submarine, 200, 75);
	  enemySubmarineLabel = new JLabel(submarineIcon);
	  
	  BufferedImage destroyer = ImageIO.read(new File("Images/Destroyer.png"));
	  ImageIcon destroyerIcon = imgResize.resize(destroyer, 200, 75);
	  enemyDestroyerLabel = new JLabel(destroyerIcon);

	  topShips.add(enemyCarrierLabel);
	  topShips.add(enemyBShipLabel);
	  topShips.add(enemyCruiserLabel);
	  bottomShips.add(enemySubmarineLabel);
	  bottomShips.add(enemyDestroyerLabel);
	  serverShips.add(topShips);
	  serverShips.add(bottomShips);
	  
	  primaryPanel.add(serverShips);
  }
  
  public void initPlayerShips() throws IOException {
	  clientShips = new JPanel();
	  clientShips.setLayout(new GridLayout(3, 1));
	  clientShips.setBorder(BorderFactory.createLineBorder(Color.blue));
	  
	  JPanel info = new JPanel();
	  info.setLayout(new GridLayout(1, 3));
	  JButton toggle = new JButton("Toggle (Currently Horizontal)");
	  
	  toggle.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
			  if (isVertical)
			  {
				  isVertical = false;
				  toggle.setText("Toggle (Currently Horizontal)");
			  } else
			  {
				  isVertical = true;
				  toggle.setText("Toggle (Currently Vertical)");
			  }
		  } 
		} );
	  
	  info.add(toggle);
	  auto = new JButton("Auto Place Ships");
	  auto.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
			  autoPlace();
		  } 
		} );
	  
	  info.add(auto);
	  
	  messages = new JTextArea();
	  DefaultCaret caret = (DefaultCaret)messages.getCaret();
	  caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	  messages.setEditable(false);
	  messages.setLineWrap(true);
	  messages.setWrapStyleWord(true);
	  
	  JScrollPane scroll = new JScrollPane(messages);
	  scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	  scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	  info.add(scroll);
	  
	  clientShips.add(info);
	  
	  JPanel topShips = new JPanel();
	  topShips.setLayout(new GridLayout(1, 3));
	  JPanel bottomShips = new JPanel();
	  bottomShips.setLayout(new GridLayout(1, 2));
	 
	  BufferedImage carrier = ImageIO.read(new File("Images/Carrier.png"));
	  ImageIcon carrierIcon = imgResize.resize(carrier, 200, 75);
	  playerCarrierLabel = new JLabel(carrierIcon);
	  
	  BufferedImage bship = ImageIO.read(new File("Images/BShip.png"));
	  ImageIcon bshipIcon = imgResize.resize(bship, 200, 75);
	  playerBShipLabel = new JLabel(bshipIcon);
	  
	  BufferedImage cruiser = ImageIO.read(new File("Images/Cruiser.png"));
	  ImageIcon cruiserIcon = imgResize.resize(cruiser, 200, 75);
	  playerCruiserLabel = new JLabel(cruiserIcon);
	  
	  BufferedImage submarine = ImageIO.read(new File("Images/Submarine.png"));
	  ImageIcon submarineIcon = imgResize.resize(submarine, 200, 75);
	  playerSubmarineLabel = new JLabel(submarineIcon);
	  
	  BufferedImage destroyer = ImageIO.read(new File("Images/Destroyer.png"));
	  ImageIcon destroyerIcon = imgResize.resize(destroyer, 200, 75);
	  playerDestroyerLabel = new JLabel(destroyerIcon);
	  
	  topShips.add(playerCarrierLabel);
	  topShips.add(playerBShipLabel);
	  topShips.add(playerCruiserLabel);
	  bottomShips.add(playerSubmarineLabel);
	  bottomShips.add(playerDestroyerLabel);
	  clientShips.add(topShips);
	  clientShips.add(bottomShips);
	  
	  primaryPanel.add(clientShips);
  }
  
  public void setMessage(String m)
  {
	  messages.append("- " + m + "\n");
  }
 
  public abstract void autoPlace();
  
}