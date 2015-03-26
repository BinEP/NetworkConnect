package myCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static BufferedReader receiveStream;
	public static PrintWriter outStream;
	public static BufferedReader talkStream;

	public static void main(String[] args) {

		Scanner console = new Scanner(System.in);

		System.out.println("Host: ");
		String host = null;
//		String host = console.nextLine();
		
		if (host == null || host.equals("")) {
			host = "127.0.0.1";
		}

		Client c = new Client();
		c.new ClientThread(host, "Client");

		// try {
		// if (InetAddress.getByName(host).isReachable(50)) {
		//
		// Socket s = new Socket(host, Server.portNum);
		// receiveStream = new BufferedReader(new
		// InputStreamReader(s.getInputStream()));
		// outStream = new PrintWriter(s.getOutputStream(), true);
		// talkStream = new BufferedReader(new InputStreamReader(System.in));
		//
		// while (talkStream.readLine() != null) {
		// String text = talkStream.readLine();
		// if (text.equals("Bye")) break;
		//
		// outStream.println(text);
		// System.out.println("echo: " + receiveStream.readLine());
		// }
		//
		// }
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	public class ClientThread extends Thread {

		private Socket clientSocket;
		private String clientID;
		private Thread client;
		private boolean running = false;

		public ClientThread(String host, String threadName) {

			try {
				Socket s = new Socket(host, Server.portNum);
				this.clientSocket = s;
				clientID = threadName;
				System.out.println("Creating Client Thread");
				running = true;
				start();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				receiveStream = new BufferedReader(new InputStreamReader(
						clientSocket.getInputStream()));

				outStream = new PrintWriter(clientSocket.getOutputStream(),
						true);

				talkStream = new BufferedReader(
						new InputStreamReader(System.in));
				System.out.println(receiveStream.readLine());
				
				while (running) {
//					System.out.println("Runnning");
					String text = talkStream.readLine();
					if (text.equals("Bye")) {
						running = false;
						break;
					}
						

					outStream.println(text);
					System.out.println("echo: " + receiveStream.readLine());
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
