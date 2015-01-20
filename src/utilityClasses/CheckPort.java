package utilityClasses;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class CheckPort {

	public String host;
	public int port;
	public static void main(String[] args) {
		CheckPort runIt = new CheckPort();
		runIt.runFromMain();
	}

	public void runFromMain() {
		
		CheckPort cp = new CheckPort("localhost", 45017);
		System.out.println(cp.testPort());
		
	}

	public CheckPort() {
		// TODO Auto-generated constructor stub
	}
	
	public CheckPort(String host, int port) {
		
		this.host = host;
		this.port = port;
		
		
	}
	
public CheckPort(int port) {
		
		this.port = port;
		
		
	}

public CheckPort(String host) {
	
	
	this.host = host;	
	
}
	
	public boolean testPort() {
		
		try {
			Socket s = new Socket(host, port);
			System.out.println("Port " + port + " open at " + host);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Port " + port + " not open at " + host);
//			e.printStackTrace();
			return false;
			
			
		}
		
		
	}
	
public boolean testPort(boolean printStatus) {
		
		try {
			Socket s = new Socket(host, port);
			if (printStatus) System.out.println("Port " + port + " open at " + host);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if (printStatus) System.out.println("Port " + port + " not open at " + host);
//			e.printStackTrace();
			return false;
			
			
		}
		
		
	}
	
public boolean testHost(String host) {
		
		try {
			Socket s = new Socket(host, port);
			System.out.println("Port " + port + " open");
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Port " + port + " closed");
//			e.printStackTrace();
			return false;
			
			
		}
		
		
	}

public boolean testHost(String host, boolean printStatus) {
	
	try {
		Socket s = new Socket(host, port);
		if (printStatus) System.out.println("Port " + port + " open");
		return true;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		if (printStatus) System.out.println("Port " + port + " closed");
//		e.printStackTrace();
		return false;
		
		
	}
	
	
}

public boolean testPort(int port) {
	
	try {
		Socket s = new Socket(host, port);
		System.out.println("Port " + port + " open");
		return true;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("Port " + port + " closed");
//		e.printStackTrace();
		return false;
		
		
	}
	
	
}

public boolean testPort(int port, boolean printStatus) {
	
	try {
		Socket s = new Socket(host, port);
		if (printStatus) System.out.println("Port " + port + " open");
		return true;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		if (printStatus) System.out.println("Port " + port + " closed");
//		e.printStackTrace();
		return false;
		
		
	}
	
	
}

}
