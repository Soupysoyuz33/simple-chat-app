package ie.gmit.dip;

import java.io.*;
import java.net.*;

/**
 * Creates threads for users connected to the chat application
 * 
 * Based on code by Nam Ha Minh, 2019 How to Create a Chat Console Application
 * in Java using Socket
 * (https://www.codejava.net/java-se/networking/how-to-create-a-chat-console-application-in-java-using-socket)
 * 
 * @author Darren McCartney
 * @version 1.0
 * @see ChatServer
 */

public class ConnectionThread extends Thread {

	private Socket theSocket;
	private ChatServer theServer;
	private PrintWriter pw;

	/**
	 * Constructor for ConnectionThread
	 * 
	 * @param s  the socket
	 * @param cs the instance of ChatServer
	 */

	public ConnectionThread(Socket s, ChatServer cs) {
		this.theSocket = s;
		this.theServer = cs;
	}

	/**
	 * Creates input and output streams for the server, serves messages between
	 * users and and finalises by closing the socket when the user quits
	 */

	public void run() {
		try {
			InputStream is = theSocket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			OutputStream os = theSocket.getOutputStream();
			pw = new PrintWriter(os, true);

			String name = br.readLine();

			String serverMessage = "New user has joined the chat: " + name;
			theServer.multiuserMessage(serverMessage, this);

			String userMessage;

			do {
				userMessage = br.readLine();
				serverMessage = name + ": " + userMessage;
				theServer.multiuserMessage(serverMessage, this);

			} while (!userMessage.equals("\\q"));

			theSocket.close();

			serverMessage = name + " has quit.";
			theServer.multiuserMessage(serverMessage, this);

		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/**
	 * Allows messages to be written to the target
	 * 
	 * @param message message text to be sent
	 */

	public void sendMessage(String message) {
		pw.println(message);
	}
}
