package problemdomain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/** This class is used to handle the gameboards created for the client gui
 * @author Nic Kelly
 */
public class ButtonBoard {
	private JPanel panel;
	private Color beforeClicked;
	private Color afterClicked;
	private Boolean shouldClick;
	private GUI gui;
	private final int SIZE = 10; //size of the board
	/**
	 * This is the constructor to make a button board
	 * @param shouldClick should the button be able to click
	 * @param beforeClicked the color the button is before it's clicked
	 * @param afterClicked the color the button is after it's clicked
	 * @param gui the reference to the parent gui
	 */
	public ButtonBoard(Boolean shouldClick, Color beforeClicked, Color afterClicked, GUI gui) {
		this.panel = new JPanel();
		this.shouldClick = shouldClick;
		this.beforeClicked = beforeClicked;
		this.afterClicked = afterClicked;
		this.panel.setLayout(new GridLayout(SIZE, SIZE));
		this.gui = gui;
	}
	
	/**
	 * makes the panel of buttons and returns it
	 * @return the panel containing the button board
	 */
	public JPanel makePanel() {
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				int index = i;
				int j_ = j;
				JButton button = new JButton();
				button.setEnabled(this.shouldClick);
				button.setBackground(this.beforeClicked);
				button.setPreferredSize(new Dimension(55, 42)); //this is a hard coded button size to ensure large enough visuals
				button.addActionListener((ActionEvent e)-> {
					((JButton) e.getSource()).setBackground(this.afterClicked);
					try {
						//send a message to the server of what button was pressed
						this.gui.getClient().out.writeObject("BUTTONCLICK:" + index + ":" + j_);
					} catch (SocketException e2) {
						JOptionPane.showMessageDialog(null, "Server closed");
						this.gui.makeConnection();							
					} catch (IOException e1) {
						e1.printStackTrace();
					};
				});
				this.panel.add(button);
			}
		}
		return this.panel;
	}
	
	/**
	 * updates the reference when a connection is changed
	 * @param gui the parent gui
	 * @return returns a new board
	 */
	public ButtonBoard updateBoard(GUI gui) {
		this.panel.removeAll();
		this.gui = gui;
		return this;
	}

	/** NOT WORKING .. Sets the color of button to press it from a server request
	 * @param i the i'th index of the button in the container
	 * @param j the j'th index of the button in the container
	 */
	public void press(int i, int j) {
		this.panel.getComponentAt(i,  j).setBackground(Color.GRAY);;
	}
}
