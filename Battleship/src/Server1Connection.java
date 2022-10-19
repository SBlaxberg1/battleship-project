import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Server1Connection {
	    BufferedReader is;
	    PrintStream os;
	    Socket clientSocket;
	    PlayerServer server;

	    public Server1Connection(Socket clientSocket, PlayerServer server) {
	    this.clientSocket = clientSocket;
	    this.server = server;
	    System.out.println( "Connection established with: " + clientSocket );
	    try {
	        is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	        os = new PrintStream(clientSocket.getOutputStream());
	    } catch (IOException e) {
	        System.out.println(e);
	    }
	    }

	    public void run() {
	        String line;
	    try {
	        boolean serverStop = false;

	            while (true) {
	                line = is.readLine();
	        System.out.println( "Received " + line );
	                int n = Integer.parseInt(line);
	        if ( n == -1 ) {
	            serverStop = true;
	            break;
	        }
	        if ( n == 0 ) break;
	                os.println("" + n*n ); 
	            }

	        System.out.println( "Connection closed." );
	            is.close();
	            os.close();
	            clientSocket.close();

	        if ( serverStop ) server.stopServer();
	    } catch (IOException e) {
	        System.out.println(e);
	    }
	    }
	}

