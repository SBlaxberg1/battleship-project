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

	      do // process messages sent from server
	      { 
	         try // read message and display it
	         {
	            message = ( String ) input.readObject(); // read new message
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
	   
	   public void initServerGrid() {
			  
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
	   
	   public void initClientGrid(){
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
					  c.gridx = i;
					  c.gridy = j;
					  clientPanel.add(clientGrid[i][j], c);
				  }
			  }
			  primaryPanel.add(clientPanel);
		  }
	   
	   public void processClickEnemyGrid(int x, int y)
	   {
		   // SETUP PHASE
		   if (windowModel.getGameState() == 2)
		   {
			   // do nothing, game is setting up
			   
		   } else if (windowModel.getGameState() == 0) //server's turn
		   {
			   // do nothing, it's not your turn
		   
		   } else if (windowModel.getGameState() == 1) // client's turn
		   {
			   // send shot to opponent
			   // TODO: This should return something to indicate hit or miss
			   sendData("" + x + y); 
		   
		   } else
		   {
			   // do nothing
		   }
			   
	   }
}

