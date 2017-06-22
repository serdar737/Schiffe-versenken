import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class View extends JFrame {
	int feldgroesse = 10;
	private JButton[][] button = new JButton [feldgroesse][feldgroesse];
	private JLabel text;
	private GamePanel panel1, panel2;
	GridBagConstraints constraints = new GridBagConstraints();


	public View() {
		super("Schiffe versenken");
		fensterGenerieren();
	}

	/*
	 * Methode in welcher das Fenster, in dem das Spiel zu sehen und spielen ist, erstellt wird
	 */
	private void fensterGenerieren() {
		setSize(1100, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLayout(new BorderLayout());
		//Panel
		text = new JLabel("Platzhalter");
		add(text, BorderLayout.PAGE_START);
		panel1 = new GamePanel(feldgroesse, 50);
		add(panel1, BorderLayout.CENTER);
		panel2 = new GamePanel(feldgroesse, 17);
		add(panel2, BorderLayout.EAST);
		setVisible(true);
		
	}
	
	class GamePanel extends JPanel {

		
		public GamePanel(int i, int j) {
			int k =0;
			int h =0;
			GridBagLayout gblayout = new GridBagLayout();
			setLayout(gblayout);
			GridBagConstraints constraints = new GridBagConstraints();
			
			constraints.gridy = 0;
			constraints.gridx = 0;

			for (h=0; h<i;h++) {
				for (k=0; k<i; k++) {
					button[k][h] = new JButton();
					add(button[k][h], constraints);
					button[k][h].setBorderPainted(true);
					button[k][h].setPreferredSize(new Dimension(j,j));
					constraints.gridx++;
					
				}
				constraints.gridx = 0;
				constraints.gridy++;
			}		
		}
	}
}
