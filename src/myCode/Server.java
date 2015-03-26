package myCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

	public static int portNum = 45321;
	public static ArrayList<Socket> clients = new ArrayList<Socket>();
	public static Socket socket;
	public static BufferedReader receiveStream;
	public static PrintWriter outStream;

	public static void main(String[] args) {

//		Scanner console = new Scanner(System.in);

//		try {
			
			try {
				ServerSocket s = new ServerSocket(portNum);
				Server server = new Server();
				ServerThread serverThread = server.new ServerThread(s, "Server");
				serverThread.start();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			socket = s.accept();
//			receiveStream = new BufferedReader(new InputStreamReader(
//					socket.getInputStream()));
//			outStream = new PrintWriter(socket.getOutputStream(), true);
//
//			String outline;
//			String inline;
//			ConnectionData data = new ConnectionData();
//			outline = data.processData(null);
//			outStream.println(outline);
//
//			while (receiveStream.readLine() != null) {
//				inline = receiveStream.readLine();
//				outline = data.processData(inline);
//				outStream.println(outline);
//				if (outline.equals("Bye"))
//					break;
//			}
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
	}

	public class ServerThread extends Thread {

		private Thread server;
		private String serverName;
		private boolean running = false;
		private ServerSocket s;
		public ServerReceive sr;

		public ServerThread(ServerSocket s, String name) {

			running = true;
			this.s = s;
			serverName = name;
			System.out.println("Creating Server Thread");
			

		}
		
		public void stopThread() {
			running = false;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			int maxConnectClients = 3;
			int numOfClients = 0;
			while (running && numOfClients < maxConnectClients) {
				try {
					Socket client = s.accept();
					System.out.println("Client number: " + numOfClients + " accepted");
					Server c = new Server();
					sr = c.new ServerReceive(client, "" + numOfClients);
					numOfClients++;
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		public void start() {
			System.out.println("Starting Server...");

			if (server == null) {
				server = new Thread(this, serverName);
				server.start();
			}
		}
	}
	
	public class ServerReceive extends Thread {

		private Socket clientSocket;
		private String clientID;
		private Thread client;
		private boolean running = false;

		public ServerReceive(Socket s, String threadName) {

				this.clientSocket = s;
				clientID = threadName;
				System.out.println("Creating Client Thread");
				running = true;
				start();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				receiveStream = new BufferedReader(new InputStreamReader(
						clientSocket.getInputStream()));

				outStream = new PrintWriter(clientSocket.getOutputStream(),
						true);

				String outline;
				String inline;
				ConnectionData data = new ConnectionData();
				outline = data.processData(null);
				outStream.println(outline);

				while (running) {
//					System.out.println("Runnning");
					
					inline = receiveStream.readLine();
//					System.out.println(inline);
					if (inline == null) {
						running = false;
						break;
					}
					
					outline = data.processData(inline);
					outStream.println(outline);
					
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				System.out.println("Client Disconnected");
				running = false;
			}
			
			System.out.println("Stopping Server");

		}

		public void start() {
			System.out.println("Starting Client...");

			if (client == null) {
				client = new Thread(this, clientID);
				client.start();
			}
		}
	}

}
