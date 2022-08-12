package problemdomain;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;

/** ConnectButton represents a button on the client interface, it extends JButton
 * @author Nic Kelly
 */
public class ConnectButton extends JButton {
	private GUI gui;
	private Client c;
	
	/**
	 * This is the constructor for a ConnectButton
	 * @param title the title of the button, passed to the super constructor
	 * @param gui the reference to the parent gui
	 */
	public ConnectButton(String title, GUI gui) {
		super(title);
		this.gui = gui;
		this.c = this.gui.getClient();
		this.addActionListener((ActionEvent e) -> {
			if (this.c.socket.isConnected()) {
				try {
					this.c.socket.close(); //if connected close
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			this.gui.makeConnection(); //try to make another connection
		});
	}
	
	/**
	 * Updates the button when a new connection was made
	 * @param title the title of the button
	 * @param gui reference to the parent to interact with
	 * @return ConnectButton (this)
	 */
	public ConnectButton updateButton(String title, GUI gui) {
		this.gui = gui;
		this.c = this.gui.getClient();
		return this;
	}
}
