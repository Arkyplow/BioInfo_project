package gui;

import javax.swing.*;

/**
 * fenetre principale
 * @author Jannou
 *
 */
public class MainFrame {

	/**
	 *
	 * @param args parametre min fenetre principale
     */
	public static void main(String[] args) {
		initializeMain();
	}

	/**
	 * initialisation fentre principale
	 */
	private static void initializeMain() {
		//Create and set up the window.
		JFrame frame = new JFrame("BioInformatique");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
}
