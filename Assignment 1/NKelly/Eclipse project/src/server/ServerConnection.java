package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import problemdomain.ServerGUI;

/** The ServerConnection class extends thread and represents a connection on the server.
 * @author Nic Kelly
 */
public class ServerConnection extends Thread {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private ArrayList<ServerConnection> clients;
	private ArrayList<String> inGame;
	private ServerGUI gui;

	/**
	 * The constructor for the ServerConnecton
	 * @param client socket of the connection
	 * @param in ObjectInpuStream of the connection
	 * @param out ObjectOutputStream of the connection
	 * @param clients an array list of server connections
	 * @param inGame a list of clients wiating to be placed in a game
	 * @param gui the parent reference to the gui
	 */
	public ServerConnection(Socket client, ObjectInputStream in, ObjectOutputStream out,
			ArrayList<ServerConnection> clients, ArrayList<String> inGame, ServerGUI gui) {
		this.socket = client;
		this.in = in;
		this.out = out;
		this.clients = clients;
		this.inGame = inGame;
		this.gui = gui;
	}

	/**
	 * This is the code that will be run by the thread.
	 */
	@Override
	public void run() {
		while (true) {
			if (socket == null || in == null || out == null) {
				try {
					Thread.sleep(1000); //sleep if the connection hasn't been established
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.exit(-1);
				}
			} else {
				try {
					while (!socket.isClosed()) {
						String message = (String) in.readObject(); //read messages from client
						
						if (message.contains("FINDGAME")) {
							String [] parsed = message.split(":");
							String username = parsed[1];
							if (!inGame.contains(username)) {
								inGame.add(username); //if the format checks out then add the username to the inGame list
							}
							
							if ((inGame.size() % 2) == 0) {
								messageClient("GAME FOUND"); //not working?
							}
						}
						
						System.out.println("(SERVER) " + message); //message displayed
						this.gui.addMessage(">" + message);
						
						for (ServerConnection c : clients) {
							this.gui.addMessage("Sent to: " + c.getName()); //tracks what threads messages are sent to
//							System.out.println("Sent to: " + c.getName());
							c.messageClient(message); //message the client
						}
					}
				} catch (SocketException | EOFException e1) {
					System.out.println("Client disconnected");
					clients.remove(this);
					this.gui.addMessage(">" + "Client Disconnected");
					this.interrupt(); //interrupts the thread. parhaps i should use this elsewhere.
					return;
				} catch (ClassNotFoundException e2) {
					e2.printStackTrace();
					System.exit(-1);
				} catch (IOException e3) {
					e3.printStackTrace();
					return;
				}
			}
		}
	}
	
	/**
	 * Messages a client
	 * @param message to send
	 */
	private synchronized void messageClient(String message) {
		try {
			this.out.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}