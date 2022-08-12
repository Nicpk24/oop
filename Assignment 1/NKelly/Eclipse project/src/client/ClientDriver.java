package client;

import problemdomain.GUI;

/** This is the class that contains the main method to start the client.
 * @author Nic Kelly
 */
public class ClientDriver {
	/**
	 * This is the main method
	 * @param args a string array of arguments from the console
	 */
	public static void main(String[] args) {
		//Create the gui
		GUI g = new GUI();
		//Show the gui
		g.display();
	}

}
