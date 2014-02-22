import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;


public class CAAServer {

	public static int port;
	public static int numClients;
	public Client[] clients;
	
	
	public static void main(String args[]) {
		port = Integer.parseInt(args[0]);
		numClients = Integer.parseInt(args[1]);
		
		CAAServer caa = new CAAServer();
		
		try {
			caa.connectClients(numClients);
			caa.sendMessage("Hello!");
		} catch (IOException e) {
			System.err.println("Socket failure");
		} catch (Exception e1) {
			System.err.println("Fuck.");
		}
		
	}
	
	private void connectClients(int n) throws IOException {
		clients = new Client[n];
		
		ServerSocket ss = new ServerSocket(port);
		for(int i = 0; i < n; i++) {
			// set client socket
			clients[i].socket = ss.accept();
			ss = new ServerSocket(port);
			
			// set client IO
			clients[i].out = new PrintWriter(clients[i].socket.getOutputStream(), true);
			clients[i].in = new BufferedReader(new InputStreamReader(clients[i].socket.getInputStream()));
		}
		ss.close();
	}
	
	private void sendMessage(String s) {
		for(int i = 0; i < numClients; i++) {
			clients[i].out.write(s);
		}
	}
	
}
