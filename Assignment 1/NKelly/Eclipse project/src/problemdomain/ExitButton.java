package problemdomain;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;

/** ExitButton represents a button on the client interface, it extends JButton
 * @author Nic Kelly
 */
public class ExitButton extends JButton {
	private GUI gui;
	private Client c;
	
	/**
	 * This is the constructor for the exit button
	 * @param title of the button
	 * @param gui the reference to the parent class
	 */
	public ExitButton(String title, GUI gui) {
		super(title);
		this.gui = gui;
		this.c = this.gui.getClient();
		this.addActionListener((ActionEvent e) -> {
			if (!c.socket.isClosed()) {
				try {
					c.socket.close(); //if not closed, then close the connection
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				System.exit(0); //otherwise exit
			}
		});
	}
	
	/**
	 * Updates the button when a new connection has been made
	 * @param title of the button
	 * @param gui reference to the parent gui
	 * @return ExitButton
	 */
	public ExitButton updateButton(String title, GUI gui) {
		this.gui = gui;
		this.c = this.gui.getClient();
		return this;
	}
}
