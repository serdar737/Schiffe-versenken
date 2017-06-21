import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View extends JFrame {
	int feldgroesse = 10;
	private JButton[][] button = new JButton [feldgroesse][feldgroesse];
	private JTextField text;
	private GamePanel panel1, panel2;


	public View() {
		super("Schiffe versenken");
		fensterGenerieren();
	}

	/*
	 * Methode, welche das Fenster in dem das Spiel zu sehen und spielen ist erstellt wird
	 */
	private void fensterGenerieren() {
		setSize(1100, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLayout(new BorderLayout());
		//Panel
		text = new JTextField("Platzhalter");
		add(text, BorderLayout.PAGE_START);
		panel1 = new GamePanel(feldgroesse);
		add(panel1, BorderLayout.LINE_START);
		panel2 = new GamePanel(feldgroesse);
		add(panel2, BorderLayout.LINE_END);
		setVisible(true);
		
	}
	
	class GamePanel extends JPanel {

		
		public GamePanel(int i) {
			setLayout(new GridLayout(10,10));
			setSize(100, 100);
			for (int k=0; k<i; k++) {
				for (int h=0; h<i; h++) {
					button[k][h] = new JButton();
					add(button[k][h]);
					button[k][h].setBorderPainted(true);
				}
			}
		}
	}
}
