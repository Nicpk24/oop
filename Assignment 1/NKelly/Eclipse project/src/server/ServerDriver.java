/**
 * 
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import problemdomain.ServerGUI;

/** This class creates the serve, accepts clients, and starts the GUI
 * @author Nic Kelly
 */
public class ServerDriver {
	/**
	 * The main method
	 * @param args from the console, not used
	 * @throws IOException, when the listener throws an error. perhaps the server is running already.
	 */
	public static void main(String[] args) throws IOException {
		String portString = JOptionPane.showInputDialog("Enter port to run server on");
		int port = 0;
		try {
			port = Integer.valueOf(portString);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid entry");
			System.exit(0);
		}
		ServerSocket listener = new ServerSocket(port);
		ArrayList<ServerConnection> clients = new ArrayList<ServerConnection>();
		ArrayList<String> inGame = new ArrayList<String>();
		ServerGUI gui = new ServerGUI();
		gui.frame.setVisible(true);
		
		while (listener.isBound()) {
			Socket client = listener.accept();
			
			System.out.println("Client connected.");
			gui.addMessage(">Client connected");
			
			ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			
			
			ServerConnection connection = new ServerConnection(client, in, out, clients, inGame, gui); //create the thread for serverouput to a client
			clients.add(connection); //add the connection to the list of connections
			connection.start();//start the thread
		}
		listener.close();
	}

}
