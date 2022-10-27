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
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ClientGameWindow extends GameWindow {
	
	   private ObjectOutputStream output; // output stream to server
	   private ObjectInputStream input; // input stream from server
	   private String message = ""; // message from server
	   private Socket client; // socket to communicate with server
	   private BattleshipModel windowModel;
	   private int netCordsX = -1;
	   private int netCordsY = -1;
	   
	   
	   // initialize chatServer and set up GUI
	   public ClientGameWindow(BattleshipModel bmod) throws IOException
	   {
		   super();
		   windowModel = bmod;
	   }

	   // connect to server and process messages from server
	   public void runClient(String ip) 
	   {
	      try // connect to server, get streams, process connection
	      {
	         connectToServer(ip); // create a Socket to make connection
	         getStreams(); // get the input and output streams
	         processConnection(); // process connection
	      } // end try
	      catch ( EOFException eofException ) 
	      {
	         displayMessage( "\nClient terminated connection" );
	      } // end catch
	      catch ( IOException ioException ) 
	      {
	         ioException.printStackTrace();
	      } // end catch
	      finally 
	      {
	         closeConnection(); // close connection
	      } // end finally
	   } // end method runClient

	   // connect to server
	   private void connectToServer(String chatServer) throws IOException
	   {      
	      displayMessage( "Attempting connection\n" );

	      // create Socket to make connection to server
	      client = new Socket( InetAddress.getByName( chatServer ), 12345 );

	      // display connection information
	      displayMessage( "Connected to: " + 
	         client.getInetAddress().getHostName() );
	      
	   } // end method connectToServer

	   // get streams to send and receive data
	   private void getStreams() throws IOException
	   {
	      // set up output stream for objects
	      output = new ObjectOutputStream( client.getOutputStream() );      
	      output.flush(); // flush output buffer to send header information

	      // set up input stream for objects
	      input = new ObjectInputStream( client.getInputStream() );

	      displayMessage( "\nGot I/O streams\n" );
	   } // end method getStreams

	   // process connection with server
	   private void processConnection() throws IOException
	   {
		   // update connection status
		   sendData("CLIENT CONNECTED");
		   windowModel.setGameState(31);
		   setMessage("Click on your grid to place your Carrier (Length: 5).");
		   
	      do // process messages sent from server
	      { 
	         try // read message and display it
	         {
	            message = ( String ) input.readObject(); // read new message	
	            
	            // While in waiting room
	            if (message.equals("SERVER>>> SERVER READY"))
	            {
	            	windowModel.setServerReady(true);
	            	if (windowModel.getClientReady())
	            	{
		            	windowModel.setGameState(0);
		            	setMessage("Awaiting enemy move.");
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
		            	windowModel.setGameState(1);
		            	setMessage("Your turn. Click on the enemy's grid to fire a shot.");
	            	} else if (didHit.equals("miss"))
	            	{
	            		ImageIcon shipImg = new ImageIcon("Images/miss.png");
		 				   Image icon = shipImg.getImage();
		 				   Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
		 				   shipImg = new ImageIcon(resize);
		            		clientGrid[x][y].setIcon(shipImg);
		            		setMessage("Enemy shot at " + x + ", " + y + " and missed!");
			            	windowModel.setGameState(1);
			            	setMessage("Your turn. Click on the enemy's grid to fire a shot.");
	            	} else if (didHit.equals("sunk"))
	            	{
	            		ImageIcon shipImg = new ImageIcon("Images/hit.png");
		 				   Image icon = shipImg.getImage();
		 				   Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
		 				   shipImg = new ImageIcon(resize);
		            		clientGrid[x][y].setIcon(shipImg);
		            		setMessage("Enemy shot at " + x + ", " + y + " and sunk a ship!");
			            	windowModel.setGameState(1);
			            	setMessage("Your turn. Click on the enemy's grid to fire a shot.");
	            	} else if (didHit.equals("lose"))
	            	{
	            		ImageIcon shipImg = new ImageIcon("Images/hit.png");
		 				   Image icon = shipImg.getImage();
		 				   Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
		 				   shipImg = new ImageIcon(resize);
		            		clientGrid[x][y].setIcon(shipImg);
		            		setMessage("Enemy shot at " + x + ", " + y + " and sunk your last ship!");
		            		setMessage("You lost!");
			            	windowModel.setGameState(5);
	            	}
	            	sendResult(didHit);
	            }
	            
	         // Return confirmation of hit or miss
	            if (message.equals("hit"))
	            {
	            	setMessage("Hit!");
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
	            	ImageIcon shipImg = new ImageIcon("Images/miss.png");
					   Image icon = shipImg.getImage();
					   Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
					   shipImg = new ImageIcon(resize);
				      serverGrid[netCordsX][netCordsY].setIcon(shipImg);
				      setMessage("Awaiting enemy move.");
	            }
	            if (message.equals("sunk"))
	            {
	            	setMessage("You sunk an enemy ship!");
	            	ImageIcon shipImg = new ImageIcon("Images/hit.png");
	            	Image icon = shipImg.getImage();
	            	Image resize = icon.getScaledInstance(22,  22,  java.awt.Image.SCALE_SMOOTH);
	            	shipImg = new ImageIcon(resize);
	            	serverGrid[netCordsX][netCordsY].setIcon(shipImg);
	            	setMessage("Awaiting enemy move.");
	            }
	            if (message.equals("lose"))
	            {
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

	      } while ( !message.equals( "SERVER>>> TERMINATE" ) );
	   } // end method processConnection

	   // close streams and socket
	   private void closeConnection() 
	   {
	      displayMessage( "\nClosing connection" );

	      try 
	      {
	         output.close(); // close output stream
	         input.close(); // close input stream
	         client.close(); // close socket
	      } // end try
	      catch ( IOException ioException ) 
	      {
	         ioException.printStackTrace();
	      } // end catch
	   } // end method closeConnection

	   // send message to server
	   private void sendData( String message )
	   {
	      try // send object to server
	      {
	         output.writeObject( "CLIENT>>> " + message );
	         output.flush(); // flush data to output
	         displayMessage( "\nCLIENT>>> " + message );
	      } // end try
	      catch ( IOException ioException )
	      {
	         displayMessage( "\nError writing object" );
	      } // end catch
	   } // end method sendData
	   
	   // manipulates displayArea in the event-dispatch thread
	   private void displayMessage( final String messageToDisplay )
	   {
	      System.out.println(messageToDisplay);
	   } // end method displayMessage
	   
	   private void sendHit(String coords)
	   {
	      try // send object to server
	      {
	         output.writeObject(coords);
	         output.flush(); // flush output to client
	         displayMessage( "\nCLIENT>>> " + coords );
	      } // end try
	      catch ( IOException ioException ) 
	      {
	         displayMessage( "\nError writing object" );
	      } // end catch
	   }
	   
	   public void sendResult(String hitOrMiss)
	   {
	      try // send object to server
	      {
	         output.writeObject(hitOrMiss);
	         output.flush(); // flush data to output
	         displayMessage( "\nCLIENT>>> " + hitOrMiss);
	      } // end try
	      catch ( IOException ioException )
	      {
	         displayMessage( "\nError writing object" );
	      } // end catch
	   } // end method sendData
	   
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
			    		final int x = i;
						final int y = j;
			    		clientGrid[i][j].addActionListener(new ActionListener() { 
			    			  public void actionPerformed(ActionEvent e) { 
			    				  processClickPlayerGrid(x,y);
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

		   if (windowModel.getGameState() == 2) // CONNECTION PHASE
		   {
			   // do nothing, players are connecting
			   setMessage("Waiting for players to connect.");
			   
		   } else if (windowModel.getGameState() == 0) //server's turn
		   {
			   // do nothing, it's not your turn
			   setMessage("It's not your turn!");
		   
		   } else if (windowModel.getGameState() == 1) // client's turn
		   {
			   // send shot to opponent
			   setMessage("Fired shot at " + x + ", " + y + ".");
			   netCordsX = x;
			   netCordsY = y;
			   sendHit("" + x + y);
			   windowModel.setGameState(0);
		   
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
			   		
			   		windowModel.setClientReady(true);
			   		sendData("CLIENT READY");
			   		if (windowModel.getServerReady())
			   		{
			   			windowModel.setGameState(0);
			   			setMessage("Awaiting enemy move.");
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
	   		setGridIconsShip(x,y,isVertical,5,"Images/Carrier.png");
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
	   		setGridIconsShip(x,y,isVertical,5,"Images/BShip.png");
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
	   		setGridIconsShip(x,y,isVertical,5,"Images/Cruiser.png");
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
	   		setGridIconsShip(x,y,isVertical,5,"Images/Submarine.png");
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
	   		setGridIconsShip(x,y,isVertical,5,"Images/Destroyer.png");
	   		windowModel.setGameState(4);
	   		windowModel.setClientReady(true);
	   		sendData("SERVER READY");
	   		if (windowModel.getServerReady())
	   		{
	   			windowModel.setGameState(0);
	   			setMessage("Awaiting enemy move.");
	   		} else
	   		{
	   			setMessage("Waiting for opponent to finish placing ships.");
	   		}
		   }
		}
}

