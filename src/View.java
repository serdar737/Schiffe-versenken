import java.awt.BorderLayout;

import javax.swing.JFrame;

public class View extends JFrame {

	public View() {
		super("Schiffe versenken");
		fensterGenerieren();
	}

	/*
	 * Methode welche das Fenster in dem das Spiel zu sehen und spielen ist erstellt wird
	 */
	private void fensterGenerieren() {
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		setVisible(true);
		
	}
}
