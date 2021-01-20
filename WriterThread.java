package ie.gmit.dip;

import java.io.*;
import java.net.*;

/**
 * Creates threads for writing actions on chat application
 * 
 * Based on code by Nam Ha Minh, 2019 How to Create a Chat Console Application
 * in Java using Socket
 * (https://www.codejava.net/java-se/networking/how-to-create-a-chat-console-application-in-java-using-socket)
 * 
 * @author Darren McCartney
 * @version 1.0
 * @see ChatClient
 */

public class WriterThread extends Thread {
	private PrintWriter pw;
	private Socket theSocket;
	private ChatClient user;

	/**
	 * Constructor for WriterThread, creates output stream and sets up writer
	 * 
	 * @param s the socket
	 * @param u the instance of ChatClient
	 */

	public WriterThread(Socket s, ChatClient u) {
		this.theSocket = s;
		this.user = u;

		try {
			OutputStream os = theSocket.getOutputStream();
			pw = new PrintWriter(os, true);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/**
	 * Takes users name and prints their messages with a prefix of their name, until
	 * the user inputs \q, socket then is closed and the application exits
	 */

	public void run() {

		Console con = System.console();

		String name = con.readLine("\nEnter your name: ");
		user.setName(name);
		pw.println(name);

		String st;

		do {
			st = con.readLine(name + ": ");
			pw.println(st);

		} while (!st.equals("\\q"));

		try {
			System.out.println("Exiting Application... Goodbye.");
			theSocket.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
