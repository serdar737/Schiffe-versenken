import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class GameView extends JFrame {
	Model model = new Model();
	private JButton[][] button = new JButton [model.getFeldgroesse()][model.getFeldgroesse()];
	private JLabel text;
	private GamePanel panel1, panel2;
	GridBagConstraints constraints = new GridBagConstraints();
	


	public GameView() {
		super("Schiffe versenken");
		fensterGenerieren();
	}
	
	

	/*
	 * Methode in welcher das Fenster, in dem das Spiel zu sehen und spielen ist, erstellt wird
	 */
	private void fensterGenerieren() {
		setSize(900, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		//Panel
		text = new JLabel("Spieler " + model.getSpieler());
		add(text, BorderLayout.PAGE_START);
		panel1 = new GamePanel(model.getFeldgroesse(), 50);
		add(panel1, BorderLayout.CENTER);
		panel2 = new GamePanel(model.getFeldgroesse(), 18);
		add(panel2, BorderLayout.EAST);
		panel1.setBorder(new EmptyBorder(5,5,5,5));
		panel2.setBorder(new EmptyBorder(5,5,5,40));
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

			for (k=0; k<i;k++) {
				for (h=0; h<i; h++) {
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
