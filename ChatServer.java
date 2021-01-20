package ie.gmit.dip;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Creates a server for a multiuser chat application
 * 
 * Based on code by Nam Ha Minh, 2019 How to Create a Chat Console Application
 * in Java using Socket
 * (https://www.codejava.net/java-se/networking/how-to-create-a-chat-console-application-in-java-using-socket)
 * 
 * @author Darren McCartney
 * @version 1.0
 * @see ConnectionThread
 */

public class ChatServer {

	private int portNumber;
	private Set<ConnectionThread> users = new HashSet<>();

	/**
	 * Constructor for ChatServer
	 * 
	 * @param port port number to listen on
	 */

	public ChatServer(int port) {
		this.portNumber = port;
	}

	/**
	 * Establishes server on specific port number and sets up user
	 */

	public void go() {
		try (ServerSocket theServer = new ServerSocket(portNumber)) {

			while (true) {
				Socket theSocket = theServer.accept();
				ConnectionThread user = new ConnectionThread(theSocket, this);
				users.add(user);
				user.start();
			}

		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("No port provided as arguments...");
			System.out.println("Programme should be run as > java ie.gmit.dip.ChatServer [port number]");
			System.exit(0);
		}

		int pn = Integer.parseInt(args[0]);
		ChatServer cs = new ChatServer(pn);
		cs.go();
	}

	/**
	 * Allows text to be broadcast to all users
	 * 
	 * @param message message to be sent to all users
	 * @param current ensures message isn't sent to person sending it
	 */

	public void multiuserMessage(String message, ConnectionThread current) {
		for (ConnectionThread user : users) {
			if (user != current) {
				user.sendMessage(message);
			}
		}
	}
}