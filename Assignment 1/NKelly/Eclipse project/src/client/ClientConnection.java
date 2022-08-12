package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import problemdomain.Client;
import problemdomain.GUI;

/** ClientConnection is a thread that is created when a client connects to the server
 * @author Nic Kelly
 */
public class ClientConnection extends Thread {
	private GUI gui;
	private Client c;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	/**
	 * Constructor for a client connection
	 * @param gui the reference to the gui that created this thread
	 */
	public ClientConnection(GUI gui) {
		this.gui = gui;
		this.c = this.gui.getClient();
		this.socket = this.c.socket;
		this.in = this.c.in;
		this.out = this.c.out;
	}
	
	/**
	 * This is the code that the thread runs
	 */
	@Override
	public void run() {
		while (true) {
			//if the socket isn't connected yet the thread will sleep for 1 second to try to make that connection
			if (socket == null || in == null) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.exit(-1);
				}
			} else {
				try {
					while (!socket.isClosed()) {
						//This is a message from the server
						String message = (String) in.readObject();
						this.gui.addMessage(message);
					}
				} catch (SocketException e1) {
					//Socket was closed, make sure this thread stops.
					return;
				} catch (ClassNotFoundException e2) {
					e2.printStackTrace();
					System.exit(-1);
				} catch (IOException e3) {
					e3.printStackTrace();
					System.exit(-1);
				}
			}
		}
	}
}
