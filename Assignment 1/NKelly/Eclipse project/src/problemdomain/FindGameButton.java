/**
 * 
 */
package problemdomain;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;

/** FindGameButton represents a button on the client interface, it extends JButton
 * @author Nic Kelly
 */
public class FindGameButton extends JButton {
	private GUI gui;
	
	/**
	 * This is the constructor for the button
	 * @param text title of the button
	 * @param gui parent reference
	 */
	public FindGameButton(String text, GUI gui) {
		super(text);
		this.gui = gui;
		addActionListener((ActionEvent e) -> {
			try {
				this.gui.getClient().out.writeObject("FINDGAME:" + this.gui.username);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	/**
	 * Updates the button when a new connection has been made
	 * @param title of the button
	 * @param gui reference to the parent gui
	 * @return FindGameButton
	 */
	public FindGameButton updateButton(String title, GUI gui) {
		this.gui = gui;
		return this;
	}
}
