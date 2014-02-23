import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;


public class CAAServer {

	public static int port;
	public static int numClients;
	public Client[] clients;


	public static void main(String args[]) {
		port = Integer.parseInt(args[0]);
		numClients = Integer.parseInt(args[1]);
		
		try {
			System.out.println(InetAddress.getLocalHost());
		} catch (UnknownHostException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		CAAServer caa = new CAAServer();
		
		try {
			caa.connectClients(numClients);
			caa.sendMessage("Hello!");
			caa.receiveMessages();
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
            clients[i] = new Client();

			// set client socket
            System.out.println("ima poop");
			clients[i].socket = ss.accept();
            System.out.println("client connected");
			// set client IO
			clients[i].out = new PrintWriter(clients[i].socket.getOutputStream(), true);
			clients[i].in = new BufferedReader(new InputStreamReader(clients[i].socket.getInputStream()));
		}
	}
	
	private void sendMessage(String s) {
		for(int i = 0; i < numClients; i++) {
			clients[i].out.println(s);
		}
        System.out.println("Message Sent");
	}
	
	private void receiveMessages() {
		for(int i = 0; i < numClients; i++) {
			try {
				System.out.println(clients[i].in.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
