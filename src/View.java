import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame {
	private JButton[][] button = new JButton [10][10];
	private GamePanel panel;

	public View() {
		super("Schiffe versenken");
		fensterGenerieren();
	}

	/*
	 * Methode, welche das Fenster in dem das Spiel zu sehen und spielen ist erstellt wird
	 */
	private void fensterGenerieren() {
		setSize(1000, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		setVisible(true);
		//Panel
		panel = new GamePanel();
		add(panel, BorderLayout.EAST);
		
	}
	
	class GamePanel extends JPanel {
		
		public GamePanel() {
			setLayout(new GridLayout(10, 10));
			for (int k=0; k<10; k++) {
				for (int h=0; h<10; h++) {
					button[k][h] = new JButton();
					add(button[k][h]);
					button[k][h].setBorderPainted(true);
				}
			}
		}
	}
}
