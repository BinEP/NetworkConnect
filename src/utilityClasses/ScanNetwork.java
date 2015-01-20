package utilityClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ScanNetwork {

	private static final int DEFAULT_PORT = 45017;
	public String subnetIP;

	public static void main(String[] args) {

		ScanNetwork sn = new ScanNetwork();
		sn.runFromMain();

	}

	public void runFromMain() {
		// TODO Auto-generated method stub

		checkHosts();
		checkHostsAtPort(45017);

	}

	public String getCurrentIP() {

		try {

			System.out.println(InetAddress.getLocalHost().getHostAddress());
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	public String[] checkHosts() {
		String subnet;
		
//		System.out.println(getCurrentIP());
		
		String IPAddr = getCurrentIP();
		int endSubStr = IPAddr.lastIndexOf(".");
		
		subnet = IPAddr.substring(0, endSubStr);
		
		int timeout = 10;
		ArrayList<String> openHosts = new ArrayList<String>();
		for (int i = 1; i < 254 && openHosts.size() < 12; i++) {
			String host = subnet + "." + i;
//			System.out.println("Checking " + host);
			try {
				if (InetAddress.getByName(host).isReachable(timeout)) {

					System.out.println(host + " is reachable");
					openHosts.add(host);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Done checking or found enough hosts");

		String[] hostsString = new String[openHosts.size()];
		hostsString = openHosts.toArray(hostsString);
		return hostsString;
	}
	
	public String[] checkHostsAtPort(int port) {
		
		CheckPort cp = new CheckPort(port);
		
		String[] ips = checkHosts();
		
//		System.out.println(getCurrentIP());
		
		String IPAddr = getCurrentIP();
		int endSubStr = IPAddr.lastIndexOf(".");
				
		ArrayList<String> openHosts = new ArrayList<String>();
		
		for (int i = 0; i < ips.length; i++) {
			String host = ips[i];
			System.out.println("Checking " + host);
				if (cp.testHost(host)) {

					System.out.println(host + " is open");
					openHosts.add(host);

				}
			
		}
		System.out.println("Done checking or found enough hosts");

		String[] hostsString = new String[openHosts.size()];
		hostsString = openHosts.toArray(hostsString);
		return hostsString;
		
		
		
	}
	
public String[] checkHostsAtPort(int port, boolean printStatus) {
		
		CheckPort cp = new CheckPort(port);
		
		String[] ips = checkHosts();
		
//		System.out.println(getCurrentIP());
		
		String IPAddr = getCurrentIP();
		int endSubStr = IPAddr.lastIndexOf(".");
				
		ArrayList<String> openHosts = new ArrayList<String>();
		
		for (int i = 0; i < ips.length; i++) {
			String host = ips[i];
			System.out.println("Checking " + host);
				if (cp.testHost(host, printStatus)) {

					System.out.println(host + " is open");
					openHosts.add(host);

				}
			
		}
		System.out.println("Done checking or found enough hosts");

		String[] hostsString = new String[(openHosts.size() == 0) ? 1 : openHosts.size()];
		hostsString = openHosts.toArray(hostsString);
		if (hostsString.length == 1 && hostsString[0] == null) hostsString[0] = "localhost";
		return hostsString;
		
		
		
	}

	public ScanNetwork() {
		// TODO Auto-generated constructor stub

		// checkHosts("10.42.5");
	}
}
