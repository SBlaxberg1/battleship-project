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

	   public ServerGameWindow() throws IOException
	   {
		   super();
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

	      do // process messages sent from client
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
		    				  sendData("" + x + y);
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
}
