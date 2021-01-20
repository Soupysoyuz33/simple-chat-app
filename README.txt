Chat Application - Network Programming Project
by Darren McCartney 

Description

This a java application which contains both server and client functionality for a simple chat application.
Users can create a server on a specificied port number and using the client can then connect to the server 
by providing the port number of the server. Users are then entered into a chatroom where they can send and 
receive messages.

The application is formed from five java classes; ChatServer, ConnectionThread, ChatClient, ReaderThread and
WriterThread. 

ChatServer is what provides the main functionality for the server. When run, it allows users to create a server
on a specified port, provided as args through the command line. The computer first checks to see the user has 
entered any arguments with their run command and if not exits the programme instructing them on how to properly
enter the command with arguments for the port number. If they correctly enter a port number then a new server
is created and a connection is established as a socket to the port. A new user thread is also created by creating
a new instance of ConnectionThread and calling its start() method.

ConnectionThread extends the java class Thread and therefore implements the run() method. run() creates an input 
stream, wrapping it in a BufferedReader to take in text and creates an output stream which is wrapped by a
PrintWriter to allow the server to write text to users. The users name is then printed to other users to show they
have entered the chat. User's messages will then be read and written to other users in the chat with a prefix of
their name until the user inputs \q which will close the socket, print the user has left and close that users 
application.

ChatClient is what manages the users input and received messages from the server with the help of threads from
ReaderThread and WriterThread. The threaded approach to both server and client allows there to be many users in 
the chat at one time. ChatClient allows for user's names to be set and returned, creates a socket with the server
when provided with the port number through args, prints an introductory text and finally starts the ReaderThread
and WriterThread.

ReaderThread creates an input stream wrapped by a BufferedReader to take in any received messages from the server.
It will continously read incoming messages and print them to the console with a prefix of the sender's name while
the socket is active.

WriterThread creates an output stream wrapped in a PrintWriter. It initially takes in the users name upon start up
and keeps a record of it. WriterThread then continues to output the user's message with a prefix of their name to 
the server until the user inputs \q. This closes the socket and exits the application for the user.

Usage

The user first needs to create their host server by opening a command line interface at the directory containing 
the .class files for the application. This will usually be a bin directory not the src. The files are packaged 
with ie.gmit.dip. From here, they should enter the command

java ie.gmit.dip.ChatServer [port number]

inserting the port number they wish to establish the server on. For example, entering the command

java ie.gmit.dip.ChatServer 3000

would establish a chat server on port number 3000.The server will be running at this point and is only closed when 
the user enters the standard exit shortcut for their interface, eg. windows is ctrl c.

Any users then wishing to connect to the chat server will have to also access the .class files directory. They 
should enter the command

java ie.gmit.dip.ChatClient [hostname] [port number]

where hostname reflects the server's host address which should be the IP address of the computer hosting the server
and port number is the port number the server was established on. For example, if the user wants to connect to 
a server on the IP address 128.43.3.20 and the server was established on port 3000 they would run the command

java ie.gmit.dip.ChatClient 128.43.3.20 3000

If the server is being hosted on their computer then they would use localhost for the hostname. At this point the
user should enter the chatroom where they will be prompted to enter their name for identification within the chat.
They can continue to chat with other users while the server is active and if they wish to quit they should enter
\q which will close their client side of the application.

This project was designed and written with help from code by 
Nam Ha Minh, 2019 How to Create a Chat Console Application in Java using Socket
(https://www.codejava.net/java-se/networking/how-to-create-a-chat-console-application-in-java-using-socket)