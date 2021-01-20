package ie.gmit.dip;

import java.net.*;
import java.io.*;

/**
 * Provides chat functionality on client side
 * 
 * Based on code by Nam Ha Minh, 2019 How to Create a Chat Console Application
 * in Java using Socket
 * (https://www.codejava.net/java-se/networking/how-to-create-a-chat-console-application-in-java-using-socket)
 * 
 * @author Darren McCartney
 * @version 1.0
 * @see ReaderThread
 * @see WriterThread
 *
 */

public class ChatClient {

	private static String host = "localhost";
	private int portNumber;
	private String name;

	/**
	 * Constructor for ChatClient
	 * 
	 * @param p port number for server
	 */

	public ChatClient(int p) {
		this.portNumber = p;
	}

	/**
	 * Creates connection to server and prints introduction text and starts the
	 * reading and writing threads for the user
	 */

	public void go() {
		try {
			Socket theSocket = new Socket(host, portNumber);

			System.out.println("Welcome to Chat Server.");
			System.out.println("TO QUIT ENTER \\q");

			new ReaderThread(theSocket, this).start();
			new WriterThread(theSocket, this).start();

		} catch (UnknownHostException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}

	}

	/**
	 * Sets the users name
	 * 
	 * @param name name of user
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the users name
	 * 
	 * @return the users name
	 */

	public String getName() {
		return this.name;
	}

	public static void main(String[] args) {
		int pn = Integer.parseInt(args[0]);

		ChatClient user = new ChatClient(pn);
		user.go();
	}

}
