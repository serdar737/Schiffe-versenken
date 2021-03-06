import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class GameView extends JFrame {
	
	private JLabel text;
	private GamePanel panel1, panel2;
	private WeiterButtonPanel wbp;
	GridBagConstraints constraints = new GridBagConstraints();
	private int groesse = 10;

	public GameView(String sn) {
		super("Schiffe versenken");
		setSize(900, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		//Ausgabe des Spielernamens
		text = new JLabel("Momentan am Zug: "+sn);
		add(text, BorderLayout.NORTH);
		text.setBorder(new EmptyBorder(20, 20, 10, 10));
		//Panel fuer das Spielfeld und fuer die kleine Anzeigeuebersicht des eigenen Feldes
		panel1 = new GamePanel(10, 50);
		add(panel1, BorderLayout.CENTER);
		panel2 = new GamePanel(10, 18);
		add(panel2, BorderLayout.EAST);
		panel1.setBorder(new EmptyBorder(5,5,5,5));
		panel2.setBorder(new EmptyBorder(5,5,5,40));
		//WeiterButton
		wbp = new WeiterButtonPanel();
		add(wbp, BorderLayout.PAGE_END);
		wbp.setBorder(new EmptyBorder(10,10,20,10));
		setVisible(true);
	}
	
	public void setPanel2SchiffeTreffer(int i, int j) {
		panel2.button[i][j].setText("X");
	}
	
	public void setPanel2SchiffeDaneben(int i, int j) {
		panel2.button[i][j].setText("O");
	}
	
	public void setSchiffeTreffer(int i, int j, int k) {
		panel1.button[i][j].setText("X");
	}
	
	public void setSchiffeKeinTreffer(int i, int j, int k) {
		panel1.button[i][j].setText("O");
	}
	
	public void setSchiffVersenkt(int i, int j) {
		panel1.button[i][j].setOpaque(true);
		panel1.button[i][j].setBorderPainted(true);
		panel1.button[i][j].setForeground(Color.RED);
	}
	
	public void setWeiterListenerGV(ActionListener weiter) {
		wbp.button[0][0].addActionListener(weiter);
	}
	
	public void setButtonListenerGV(ActionListener button) {
		for (int k=0; k<groesse;k++) {
			for (int h=0; h<groesse; h++) {
			panel1.button[k][h].setActionCommand(""+k+h);
			panel1.button[k][h].addActionListener(button);
			}
		}
	}
	
	public void setzeTrefferKlein(int k, int h) {
			panel2.button[k][h].setOpaque(true);
			panel2.button[k][h].setBorderPainted(true);
			panel2.button[k][h].setForeground(Color.BLUE);
			panel2.button[k][h].setText("X");
	}
	
	public void setzeTrefferGetroffen(int k, int h) {
		panel2.button[k][h].setText("X");
	}
	
	public void setzeVersenkt(int k, int h) {
		panel2.button[k][h].setOpaque(true);
		panel2.button[k][h].setBorderPainted(true);
		panel2.button[k][h].setForeground(Color.RED);
		panel2.button[k][h].setText("X");
	}
	
	public void setzeSchuss(int k, int h) {
		panel2.button[k][h].setText("O");
}
	
	class GamePanel extends JPanel {

		private JButton[][] button = new JButton [10][10];
		
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
	
	class WeiterButtonPanel extends JPanel {
		
		private JButton[][] button = new JButton [1][1];
		
		public WeiterButtonPanel() {
			GridBagLayout gblayout = new GridBagLayout();
			setLayout(gblayout);
			GridBagConstraints constraints = new GridBagConstraints();
			
			constraints.gridy = 0;
			constraints.gridx = 0;
			
			button[0][0] = new JButton();
			add(button[0][0], constraints);
			button[0][0].setBorderPainted(true);
			button[0][0].setPreferredSize(new Dimension(160,50));
			button[0][0].setText("Weiter");

		}
	}
}
