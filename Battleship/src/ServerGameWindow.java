import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ServerGameWindow extends GameWindow {
	
	   private ObjectOutputStream output; // output stream to client
	   private ObjectInputStream input; // input stream from client
	   private ServerSocket server; // server socket
	   private Socket connection; // connection to client
	   private int counter = 1; // counter of number of connections
	   private BattleshipModel windowModel;
	   private int netCordsX = -1;
	   private int netCordsY = -1;

	   public ServerGameWindow(BattleshipModel bmod) throws IOException
	   {
		   super();
		   windowModel = bmod;
	   }
	   
	   // set up and run server 
	   public void runServer()
	   {
	      try // set up server to receive connections; process connections
	      {
	         server = new ServerSocket( 12345, 100 ); // create ServerSocket

	         while ( true ) 
	         {
	            try 
	            {
	               waitForConnection(); // wait for a connection
	               getStreams(); // get input & output streams
	               processConnection(); // process connection
	            } // end try
	            catch ( EOFException eofException ) 
	            {
	               displayMessage( "\nServer terminated connection" );
	            } // end catch
	            finally 
	            {
	               closeConnection(); //  close connection
	               counter++;
	            } // end finally
	         } // end while
	      } // end try
	      catch ( IOException ioException ) 
	      {
	         ioException.printStackTrace();
	      } // end catch
	   } // end method runServer

	   
	   // wait for connection to arrive, then display connection info
	   private void waitForConnection() throws IOException
	   {
	      displayMessage( "Waiting for connection\n" );
	      connection = server.accept(); // allow server to accept connection            
	      displayMessage( "Connection " + counter + " received from: " +
	         connection.getInetAddress().getHostName() );
	   }

	   // get streams to send and receive data
	   private void getStreams() throws IOException
	   {
	      // set up output stream for objects
	      output = new ObjectOutputStream( connection.getOutputStream() );
	      output.flush(); // flush output buffer to send header information

	      // set up input stream for objects
	      input = new ObjectInputStream( connection.getInputStream() );

	      displayMessage( "\nGot I/O streams\n" );
	   } // end method getStreams

	   // process connection with client
	   private void processConnection() throws IOException
	   {
	      String message = "Connection successful";
	      sendData( message ); // send connection successful message
	      
	      if (windowModel.getClientConnected() == true)
	      {
	    	  windowModel.setGameState(31);
	      }
	      
	      do // process messages sent from client
	      { 
	         try // read message and display it
	         {
	            message = ( String ) input.readObject(); // read new message
	            
	            // Handling for checking if both players have connected.
	            if (message.equals("CLIENT>>> CLIENT CONNECTED"))
	            {
	            	windowModel.setClientConnected(true);
	            	windowModel.setGameState(31);
	            	setMessage("Click on your grid to place your Carrier (Length: 5).");
	            }
	            if (message.equals("CLIENT>>> CLIENT READY"))
	            {
            		windowModel.setClientReady(true);
	            	if(windowModel.getServerReady())
	            	{
	            		windowModel.setGameState(0);
	            		AudioPlayer audio = new AudioPlayer();
	            		audio.play("./Sounds/gameStart.wav");
	            		setMessage("Click on your enemy's grid to fire a shot.");
	            	}
	            }
	            
	         // Receive shot
	            if (message.length() == 2)
	            {
	            	int coords = Integer.parseInt(message);
	            	int x = coords / 10;
	            	int y = coords % 10;
	            	String didHit = windowModel.receiveShot(x, y);
	            	if (didHit.equals("hit"))
	            	{
	            		ImageIcon shipImg = new ImageIcon("Images/hit.png");
	 				   Image icon = shipImg.getImage();
	 				   Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
	 				   shipImg = new ImageIcon(resize);
	            		clientGrid[x][y].setIcon(shipImg);
	            		setMessage("Enemy shot at " + x + ", " + y + " and hit!");
	            		AudioPlayer audio = new AudioPlayer();
	            		audio.play("./Sounds/hit.wav");
		            	windowModel.setGameState(0);
		            	setMessage("Your turn. Click on the enemy's grid to fire a shot.");
	            	} else if (didHit.equals("miss"))
	            	{
	            		ImageIcon shipImg = new ImageIcon("Images/miss.png");
		 				   Image icon = shipImg.getImage();
		 				   Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
		 				   shipImg = new ImageIcon(resize);
		            		clientGrid[x][y].setIcon(shipImg);
		            		setMessage("Enemy shot at " + x + ", " + y + " and missed!");
		            		AudioPlayer audio = new AudioPlayer();
		            		audio.play("./Sounds/miss.wav");
			            	windowModel.setGameState(0);
			            	setMessage("Your turn. Click on the enemy's grid to fire a shot.");
	            	} else if (didHit.equals("sunkCarrier"))
	            	{
	            		ImageIcon shipImg = new ImageIcon("Images/hit.png");
		 				   Image icon = shipImg.getImage();
		 				   Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
		 				   shipImg = new ImageIcon(resize);
		            		clientGrid[x][y].setIcon(shipImg);
		            		setMessage("Enemy shot at " + x + ", " + y + " and sunk your carrier!");
		            		AudioPlayer audio = new AudioPlayer();
		            		audio.play("./Sounds/sunk.wav");
		            		playerCarrierLabel.setEnabled(false);
			            	windowModel.setGameState(0);
			            	setMessage("Your turn. Click on the enemy's grid to fire a shot.");
	            	} else if (didHit.equals("sunkBattleship"))
	            	{
	            		ImageIcon shipImg = new ImageIcon("Images/hit.png");
		 				Image icon = shipImg.getImage();
		 				Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
		 				shipImg = new ImageIcon(resize);
		            	clientGrid[x][y].setIcon(shipImg);
		            	setMessage("Enemy shot at " + x + ", " + y + " and sunk your battleship!");
		            	AudioPlayer audio = new AudioPlayer();
	            		audio.play("./Sounds/sunk.wav");
	            		playerBShipLabel.setEnabled(false);
			            windowModel.setGameState(0);
			            setMessage("Your turn. Click on the enemy's grid to fire a shot.");
	            	} else if (didHit.equals("sunkCruiser"))
	            	{
	            		ImageIcon shipImg = new ImageIcon("Images/hit.png");
		 				Image icon = shipImg.getImage();
		 				Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
		 				shipImg = new ImageIcon(resize);
		            	clientGrid[x][y].setIcon(shipImg);
		            	setMessage("Enemy shot at " + x + ", " + y + " and sunk your cruiser!");
		            	AudioPlayer audio = new AudioPlayer();
	            		audio.play("./Sounds/sunk.wav");
	            		playerCruiserLabel.setEnabled(false);
			            windowModel.setGameState(0);
			            setMessage("Your turn. Click on the enemy's grid to fire a shot.");
	            	} else if (didHit.equals("sunkSubmarine"))
	            	{
	            		ImageIcon shipImg = new ImageIcon("Images/hit.png");
		 				Image icon = shipImg.getImage();
		 				Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
		 				shipImg = new ImageIcon(resize);
		            	clientGrid[x][y].setIcon(shipImg);
		            	setMessage("Enemy shot at " + x + ", " + y + " and sunk your submarine!");
		            	AudioPlayer audio = new AudioPlayer();
	            		audio.play("./Sounds/sunk.wav");
	            		playerSubmarineLabel.setEnabled(false);
			            windowModel.setGameState(0);
			            setMessage("Your turn. Click on the enemy's grid to fire a shot.");
	            	} else if (didHit.equals("sunkDestroyer"))
	            	{
	            		ImageIcon shipImg = new ImageIcon("Images/hit.png");
		 				Image icon = shipImg.getImage();
		 				Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
		 				shipImg = new ImageIcon(resize);
		            	clientGrid[x][y].setIcon(shipImg);
		            	setMessage("Enemy shot at " + x + ", " + y + " and sunk your destroyer!");
		            	AudioPlayer audio = new AudioPlayer();
	            		audio.play("./Sounds/sunk.wav");
	            		playerDestroyerLabel.setEnabled(false);
			            windowModel.setGameState(0);
			            setMessage("Your turn. Click on the enemy's grid to fire a shot.");
	            	} else if (didHit.equals("lose"))
	            	{
	            		ImageIcon shipImg = new ImageIcon("Images/hit.png");
		 				   Image icon = shipImg.getImage();
		 				   Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
		 				   shipImg = new ImageIcon(resize);
		            		clientGrid[x][y].setIcon(shipImg);
		            		setMessage("Enemy shot at " + x + ", " + y + " and sunk your last ship!");
		            		AudioPlayer audio = new AudioPlayer();
		            		audio.play("./Sounds/loss.wav");
		            		setMessage("You lost!");
			            	windowModel.setGameState(5);
	            	}
	            	sendResult(didHit);
	            }
	            
	            // Return confirmation of hit or miss
	            if (message.equals("hit"))
	            {
	            	setMessage("Hit!");
	            	AudioPlayer audio = new AudioPlayer();
            		audio.play("./Sounds/hit.wav");
	            	ImageIcon shipImg = new ImageIcon("Images/hit.png");
					   Image icon = shipImg.getImage();
					   Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
					   shipImg = new ImageIcon(resize);
				      serverGrid[netCordsX][netCordsY].setIcon(shipImg);
				      setMessage("Awaiting enemy move.");
	            }
	            if (message.equals("miss"))
	            {
	            	setMessage("Miss!");
	            	AudioPlayer audio = new AudioPlayer();
            		audio.play("./Sounds/miss.wav");
	            	ImageIcon shipImg = new ImageIcon("Images/miss.png");
					   Image icon = shipImg.getImage();
					   Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
					   shipImg = new ImageIcon(resize);
				      serverGrid[netCordsX][netCordsY].setIcon(shipImg);
				      setMessage("Awaiting enemy move.");
	            }
	            if (message.equals("sunkCarrier"))
	            {
	            	AudioPlayer audio = new AudioPlayer();
            		audio.play("./Sounds/sunk.wav");
	            	setMessage("You sunk your opponent's carrier!");
	            	enemyCarrierLabel.setEnabled(false);
	            	ImageIcon shipImg = new ImageIcon("Images/hit.png");
	            	Image icon = shipImg.getImage();
	            	Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
	            	shipImg = new ImageIcon(resize);
	            	serverGrid[netCordsX][netCordsY].setIcon(shipImg);
	            	setMessage("Awaiting enemy move.");
	            }
		        if (message.equals("sunkBattleship"))
		            {
		        		AudioPlayer audio = new AudioPlayer();
		        		audio.play("./Sounds/sunk.wav");
		            	setMessage("You sunk your opponent's battleship!");
		            	enemyBShipLabel.setEnabled(false);
		            	ImageIcon shipImg = new ImageIcon("Images/hit.png");
		            	Image icon = shipImg.getImage();
		            	Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
		            	shipImg = new ImageIcon(resize);
		            	serverGrid[netCordsX][netCordsY].setIcon(shipImg);
		            	setMessage("Awaiting enemy move.");
	            }
		        if (message.equals("sunkCruiser"))
	            {
		        	AudioPlayer audio = new AudioPlayer();
            		audio.play("./Sounds/sunk.wav");
	            	setMessage("You sunk your opponent's cruiser!");
	            	enemyCruiserLabel.setEnabled(false);
	            	ImageIcon shipImg = new ImageIcon("Images/hit.png");
	            	Image icon = shipImg.getImage();
	            	Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
	            	shipImg = new ImageIcon(resize);
	            	serverGrid[netCordsX][netCordsY].setIcon(shipImg);
	            	setMessage("Awaiting enemy move.");
	            }
		        if (message.equals("sunkSubmarine"))
	            {
		        	AudioPlayer audio = new AudioPlayer();
            		audio.play("./Sounds/sunk.wav");
	            	setMessage("You sunk your opponent's submarine!");
	            	enemySubmarineLabel.setEnabled(false);
	            	ImageIcon shipImg = new ImageIcon("Images/hit.png");
	            	Image icon = shipImg.getImage();
	            	Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
	            	shipImg = new ImageIcon(resize);
	            	serverGrid[netCordsX][netCordsY].setIcon(shipImg);
	            	setMessage("Awaiting enemy move.");
	            }
		        if (message.equals("sunkDestroyer"))
	            {
		        	AudioPlayer audio = new AudioPlayer();
            		audio.play("./Sounds/sunk.wav");
            		enemyDestroyerLabel.setEnabled(false);
	            	setMessage("You sunk your opponent's destroyer!");
	            	ImageIcon shipImg = new ImageIcon("Images/hit.png");
	            	Image icon = shipImg.getImage();
	            	Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
	            	shipImg = new ImageIcon(resize);
	            	serverGrid[netCordsX][netCordsY].setIcon(shipImg);
	            	setMessage("Awaiting enemy move.");
	            }
	            if (message.equals("lose"))
	            {
	            	AudioPlayer audio = new AudioPlayer();
            		audio.play("./Sounds/won.wav");
	            	setMessage("You defeated the enemy and have won the game!");
	            	ImageIcon shipImg = new ImageIcon("Images/hit.png");
	            	Image icon = shipImg.getImage();
	            	Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
	            	shipImg = new ImageIcon(resize);
	            	serverGrid[netCordsX][netCordsY].setIcon(shipImg);
	            	windowModel.setGameState(5);
	            }
	            
	            displayMessage( "\n" + message ); // display message
	         } // end try
	         catch ( ClassNotFoundException classNotFoundException ) 
	         {
	            displayMessage( "\nUnknown object type received" );
	         } // end catch

	      } while ( !message.equals( "CLIENT>>> TERMINATE" ) );
	   } // end method processConnection

	   // close streams and socket
	   private void closeConnection() 
	   {
	      displayMessage( "\nTerminating connection\n" );

	      try 
	      {
	         output.close(); // close output stream
	         input.close(); // close input stream
	         connection.close(); // close socket
	      } // end try
	      catch ( IOException ioException ) 
	      {
	         ioException.printStackTrace();
	      } // end catch
	   } // end method closeConnection

	   // send message to client
	   private void sendData( String message )
	   {
	      try // send object to client
	      {
	         output.writeObject( "SERVER>>> " + message );
	         output.flush(); // flush output to client
	         displayMessage( "\nSERVER>>> " + message );
	      } // end try
	      catch ( IOException ioException ) 
	      {
	         displayMessage( "\nError writing object" );
	      } // end catch
	   }

	   private void sendHit(String coords)
	   {
	      try // send object to client
	      {
	         output.writeObject(coords);
	         output.flush(); // flush output to client
	         displayMessage( "\nSERVER>>> " + coords );
	      } // end try
	      catch ( IOException ioException ) 
	      {
	         displayMessage( "\nError writing object" );
	      } // end catch
	   }
	   
	   public void sendResult(String hitOrMiss)
	   {
	      try // send object to client
	      {
	         output.writeObject(hitOrMiss);
	         output.flush(); // flush data to output
	         displayMessage( "\nSERVER>>> " + hitOrMiss);
	      } // end try
	      catch ( IOException ioException )
	      {
	         displayMessage( "\nError writing object" );
	      } // end catch
	   } // end method sendData
	   
	   // manipulates displayArea in the event-dispatch thread
	   private void displayMessage( final String messageToDisplay )
	   {
	      //System.out.println(messageToDisplay);
	   } // end method displayMessage
	   
	   public void initEnemyGrid() {
			  
			serverPanel = new JPanel();
			serverPanel.setBounds(0, 0, 500, 500);
			serverPanel.setLayout(new GridBagLayout());
			serverPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		    GridBagConstraints c = new GridBagConstraints();
		    c.fill = GridBagConstraints.HORIZONTAL;
		    
		    ImageIcon reticle = new ImageIcon("Images/reticle.png");
		    Image icon = reticle.getImage();
		    Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
		    reticle = new ImageIcon(resize);
		    
		    for (int i=0; i<10; i++) {
		    	for (int j=0; j<10; j++) {
		    		serverGrid[i][j] = new JButton(reticle);
		    		serverGrid[i][j].setBounds(0, 0, 22, 22);
		    		final int x = i;
					final int y = j;
		    		serverGrid[i][j].addActionListener(new ActionListener() { 
		    			  public void actionPerformed(ActionEvent e) { 
		    				  processClickEnemyGrid(x,y);
		    			  } 
		    			} );
		    		
		    		c.gridx = i;
		    		c.gridy = j;
		    		serverPanel.add(serverGrid[i][j], c);
		    		
		    	}
		    }
		    primaryPanel.add(serverPanel);
	   }
	   
	   public void initPlayerGrid(){
			  clientPanel = new JPanel();
			  clientPanel.setBounds(0,0, 500, 500);
			  clientPanel.setLayout(new GridBagLayout());
			  clientPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
			  GridBagConstraints c = new GridBagConstraints();
			  c.fill = GridBagConstraints.HORIZONTAL;
			  
			  ImageIcon ocean = new ImageIcon("Images/ocean_empty.png");
			  Image icon = ocean.getImage();
			  Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
			  ocean = new ImageIcon(resize);
			  
			  for (int i=0; i<10; i++) {
				  for (int j=0; j<10; j++) {
					  clientGrid[i][j] = new JButton(ocean);
					  clientGrid[i][j].setBounds(0, 0, 22, 22);
					  final int a = i;
					  final int b = j;
					  clientGrid[i][j].addActionListener(new ActionListener() { 
			    			  public void actionPerformed(ActionEvent e) { 
			    				  processClickPlayerGrid(a,b);
			    			  } 
			    			} );
			    		
					  c.gridx = i;
					  c.gridy = j;
					  clientPanel.add(clientGrid[i][j], c);
				  }
			  }
			  primaryPanel.add(clientPanel);
		  }
	   
	   private void processClickEnemyGrid(int x, int y)
	   {
		   // SETUP PHASE
		   if (windowModel.getGameState() == 2)
		   {
			   // do nothing, game is setting up
			   setMessage("Waiting for players to connect.");
			   
		   } else if (windowModel.getGameState() == 1) //client turn
		   {
			   // do nothing, it's not your turn
			   setMessage("It's not your turn!");
		   
		   } else if (windowModel.getGameState() == 0) // server turn
		   {
			   // send shot to opponent
			   serverGrid[x][y].setEnabled(false);
			   setMessage("Fired shot at " + x + ", " + y + ".");
			   netCordsX = x;
			   netCordsY = y;
			   sendHit("" + x + y);
			   windowModel.setGameState(1);
				   
		   } else
		   {
			   // do nothing
			   setMessage("It's not your turn!");
		   }
			   
	   }
	   
	   private void processClickPlayerGrid(int x, int y)
	   {
		   boolean invalidPlacement = true;
		   if (windowModel.getGameState() == 2)  // SETUP PHASE
		   {
			   // do nothing, players are connecting
			   setMessage("Waiting for players to connect.");
			   
		   }
		   else if (windowModel.getGameState() == 31) //setup - placing carrier
		   {
			   // place carrier
			    invalidPlacement = windowModel.getSetup().placeCarrier(x, y, isVertical);
			   	if (!invalidPlacement)
			   	{
			   		setMessage("You placed your Carrier at " + x + "," + y + ". "
			   				+ "Click on your grid to place your Battleship (Length: 4).");
			   		setGridIconsShip(x,y,isVertical,5,"Images/Carrier.png");
			   		windowModel.setGameState(32);
			   	} else
			   	{
			   		setMessage("Invalid Placement. Please try again.");
			   	}
			   	
		   } else if (windowModel.getGameState() == 32) //setup - placing battleship
		   {
			   invalidPlacement = windowModel.getSetup().placeBShip(x, y, isVertical);
			   	if (!invalidPlacement)
			   	{
			   		setMessage("You placed your Battleship at " + x + "," + y + ". "
			   				+ "Click on your grid to place your Cruiser. (Length: 3)");
			   		setGridIconsShip(x,y,isVertical,4,"Images/BShip.png");
			   		windowModel.setGameState(33);
			   	} else
			   	{
			   		setMessage("Invalid Placement. Please try again.");
			   	}
			   
		   } else if (windowModel.getGameState() == 33) //setup - placing cruiser
		   {
			   invalidPlacement = windowModel.getSetup().placeCruiser(x, y, isVertical);
			   	if (!invalidPlacement)
			   	{
			   		setMessage("You placed your Cruiser at " + x + "," + y + ". "
			   				+ "Click on your grid to place your Submarine. (Length: 3)");
			   		setGridIconsShip(x,y,isVertical,3,"Images/Cruiser.png");
			   		windowModel.setGameState(34);
			   	} else
			   	{
			   		setMessage("Invalid Placement. Please try again.");
			   	}
			   
		   } else if (windowModel.getGameState() == 34) //setup - placing submarine
		   {
			   invalidPlacement = windowModel.getSetup().placeSubmarine(x, y, isVertical);
			   	if (!invalidPlacement)
			   	{
			   		setMessage("You placed your Submarine at " + x + "," + y + ". "
			   				+ "Click on your grid to place your Destroyer. (Length: 2)");
			   		setGridIconsShip(x,y,isVertical,3,"Images/Submarine.png");
			   		windowModel.setGameState(35);
			   	} else
			   	{
			   		setMessage("Invalid Placement. Please try again.");
			   	}
			   
		   } else if (windowModel.getGameState() == 35) //setup - placing destroyer
		   {
			   invalidPlacement = windowModel.getSetup().placeDestroyer(x, y, isVertical);
			   	if (!invalidPlacement)
			   	{
			   		setMessage("You placed your Destroyer at " + x + "," + y + ".");
			   		setGridIconsShip(x,y,isVertical,2,"Images/Destroyer.png");
			   		windowModel.setGameState(4);
			   		
			   		windowModel.setServerReady(true);
			   		sendData("SERVER READY");
			   		if (windowModel.getClientReady())
			   		{
			   			windowModel.setGameState(0);
			   			setMessage("Click on your enemy's grid to fire a shot.");
			   			AudioPlayer audio = new AudioPlayer();
	            		audio.play("./Sounds/gameStart.wav");
			   		} else
			   		{
			   			setMessage("Waiting for opponent to finish placing ships.");
			   		}
			   		
			   	} else
			   	{
			   		setMessage("Invalid Placement. Please try again.");
			   	}
		   
		   } else
		   {
			   //nothing
			   setMessage("It is not currently the setup phase.");
		   }
	   }
	   
	   
	   public void setGridIconsShip(int x, int y, boolean h, int l, String imgPath)
	   {
		   ImageIcon shipImg = new ImageIcon(imgPath);
		   Image icon = shipImg.getImage();
		   Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
		   shipImg = new ImageIcon(resize);
		   
		   if (h == true) {
				for (int i = y; i < y + l; i++) {
					clientGrid[x][i].setIcon(shipImg);
				}
			} else {
				for (int i = x; i < x + l; i++) {
					clientGrid[i][y].setIcon(shipImg);
				}
			}
	   }
	   
	   public void autoPlace() {
		   if (windowModel.getGameState() == 31)
		   {
		    auto.setEnabled(false);
			Random rand = new Random();
			boolean orient;
			boolean invalid;
			int x;
			int y;
			int h;
			
			do {
			x = rand.nextInt(10);
			y = rand.nextInt(10);
			h = rand.nextInt(2);
			if (h == 0)
				orient = true;
			else
				orient = false;
			
			invalid = windowModel.getSetup().placeCarrier(x, y, orient);
			
			}while (invalid);
			setMessage("Autoplace placed your Carrier at " + x + "," + y + ".");
	   		setGridIconsShip(x,y,orient,5,"Images/Carrier.png");
	   		windowModel.setGameState(32);
			
			do {
				x = rand.nextInt(10);
				y = rand.nextInt(10);
				h = rand.nextInt(2);
				if (h == 0)
					orient = true;
				else
					orient = false;
				
				invalid = windowModel.getSetup().placeBShip(x, y, orient);
				
				}while (invalid);
			setMessage("Autoplace placed your Battleship at " + x + "," + y + ".");
	   		setGridIconsShip(x,y,orient,4,"Images/BShip.png");
	   		windowModel.setGameState(33);
			
			do {
				x = rand.nextInt(10);
				y = rand.nextInt(10);
				h = rand.nextInt(2);
				if (h == 0)
					orient = true;
				else
					orient = false;
				
				invalid = windowModel.getSetup().placeCruiser(x, y, orient);
				
				}while (invalid);
			setMessage("Autoplace placed your Cruiser at " + x + "," + y + ".");
	   		setGridIconsShip(x,y,orient,3,"Images/Cruiser.png");
	   		windowModel.setGameState(34);			
			
			do {
				x = rand.nextInt(10);
				y = rand.nextInt(10);
				h = rand.nextInt(2);
				if (h == 0)
					orient = true;
				else
					orient = false;
				
				invalid = windowModel.getSetup().placeSubmarine(x, y, orient);
				
				}while (invalid);
			setMessage("Autoplace placed your Submarine at " + x + "," + y + ".");
	   		setGridIconsShip(x,y,orient,3,"Images/Submarine.png");
	   		windowModel.setGameState(35);			
			
			do {
				x = rand.nextInt(10);
				y = rand.nextInt(10);
				h = rand.nextInt(2);
				if (h == 0)
					orient = true;
				else
					orient = false;
				
				invalid = windowModel.getSetup().placeDestroyer(x, y, orient);
				
				}while (invalid);
			setMessage("Autoplace placed your Destroyer at " + x + "," + y + ".");
	   		setGridIconsShip(x,y,orient,2,"Images/Destroyer.png");
	   		windowModel.setGameState(4);
	   		windowModel.setServerReady(true);
	   		sendData("SERVER READY");
	   		if (windowModel.getClientReady())
	   		{
	   			windowModel.setGameState(0);
	   			setMessage("Click on your enemy's grid to fire a shot.");
	   			AudioPlayer audio = new AudioPlayer();
        		audio.play("./Sounds/gameStart.wav");
	   		} else
	   		{
	   			setMessage("Waiting for opponent to finish placing ships.");
	   		}
	   }
	}
}