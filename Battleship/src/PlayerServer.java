 import java.io.*;
 import java.net.*;

// (Controller) The server player

public class PlayerServer extends Player
{
	public PlayerServer()
	{
		launch();
	}
	
	public void launch() {
	    int port = 6789;
	    PlayerServer server = new PlayerServer( port );
	    server.startServer();
	    }

	    // declare a server socket and a client socket for the server

	    ServerSocket echoServer = null;
	    Socket clientSocket = null;
	    int port;

	    public PlayerServer( int port ) {
	    this.port = port;
	    }

	    public void stopServer() {
	    System.out.println( "Server cleaning up." );
	    System.exit(0);
	    }

	    public void startServer() {
	    // Try to open a server socket on the given port
	    // Note that we can't choose a port less than 1024 if we are not
	    // privileged users (root)

	        try {
	        echoServer = new ServerSocket(port);
	        }
	        catch (IOException e) {
	        System.out.println(e);
	        }   

	    System.out.println( "Waiting for connections. Only one connection is allowed." );

	    // Create a socket object from the ServerSocket to listen and accept connections.
	    // Use Server1Connection to process the connection.

	    while ( true ) {
	        try {
	        clientSocket = echoServer.accept();
	        Server1Connection oneconnection = new Server1Connection(clientSocket, this);
	        oneconnection.run();
	        }   
	        catch (IOException e) {
	        System.out.println(e);
	        }
	    }
	    }
	}