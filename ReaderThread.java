package ie.gmit.dip;

import java.io.*;
import java.net.*;

/**
 * Threads for reading actions on client side
 * 
 * Based on code by Nam Ha Minh, 2019 How to Create a Chat Console Application
 * in Java using Socket
 * (https://www.codejava.net/java-se/networking/how-to-create-a-chat-console-application-in-java-using-socket)
 * 
 * @author Darren McCartney
 * @version 1.0
 * @see ChatClient
 *
 */

public class ReaderThread extends Thread {
	private BufferedReader br;
	private Socket theSocket;
	private ChatClient user;

	/**
	 * Constructor for ReaderThread, creates input stream and sets up buffering
	 * 
	 * @param s the socket
	 * @param u the instance of ChatClient
	 */

	public ReaderThread(Socket s, ChatClient u) {
		this.theSocket = s;
		this.user = u;

		try {
			InputStream is = theSocket.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/**
	 * Reads in and prints the messages from other users
	 */

	public void run() {
		while (true) {
			try {
				String message = br.readLine();
				System.out.println("\n" + message);

				if (user.getName() != null) {
					System.out.print(user.getName() + ": ");
				}
			} catch (IOException e) {
				System.err.println(e);
				break;
			}
		}
	}
}
