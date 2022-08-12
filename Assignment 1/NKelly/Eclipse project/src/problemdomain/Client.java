package problemdomain;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/** This class represents a client's connection,
 *  it has public instance variables and it is used to clean up the code
 * @author Nic Kelly
 */
public class Client {
	public Socket socket;
	public ObjectInputStream in;
	public ObjectOutputStream out;
	public String username;
	/**
	 * This is the constructor the create new client object
	 * @param socket the socket connected to the server
	 * @param in the inputstream for the socket
	 * @param out the outputstream for the socket
	 * @param username the username of the client
	 */
	public Client(Socket socket, ObjectInputStream in, ObjectOutputStream out, String username) {
		this.socket = socket;
		this.in = in;
		this.out = out;
		this.username = username;
	}
}
