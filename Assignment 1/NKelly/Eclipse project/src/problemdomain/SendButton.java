package problemdomain;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;

/** SendButton represents a button on the client interface, it extends JButton
 * @author Nic Kelly
 */
public class SendButton extends JButton {
	private GUI gui;
	private Client c;

	/**
	 * This is the constructor for a ConnectButton
	 * @param title the title of the button, passed to the super constructor
	 * @param gui the reference to the parent gui
	 */
	public SendButton(String title, GUI gui) {
		super(title);
		this.gui = gui;
		this.c = this.gui.getClient();
		this.addActionListener((ActionEvent e) -> {
			if (!this.c.socket.isConnected()) {
				this.gui.makeConnection();
			}
			
			String message = this.c.username + ":";
			try {
				message += this.gui.getMessage();
				this.c.out.writeObject(message); //sends the message to the server USERNAME:MESSAGE
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	/**
	 * Updates the button when a new connection was made
	 * @param title the title of the button
	 * @param gui reference to the parent to interact with
	 * @return SendButton
	 */
	public SendButton updateButton(String title, GUI gui) {
		this.gui = gui;
		this.c = this.gui.getClient();
		return this;
	}
}