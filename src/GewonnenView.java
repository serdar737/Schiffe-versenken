import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


/**
 * Dieser Bildschirm wird aufgerufen, wenn einer der Spieler alle Schiffe des Gegners versenkt hat
 * @author kiki
 *
 */
public class GewonnenView extends JFrame {
	
	private SchriftPanel panel;

	public GewonnenView(String gewinner) {
		super("Herzlichen Glückwunsch, du hast gewonnen!");
		setSize(900, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		panel = new SchriftPanel();
		panel.label.setText("Herzlichen Glückwunsch "+gewinner+" du hast dieses Spiel gewonnen!");
		add(panel, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void setBTMListener(ActionListener al) {
		panel.button[0][0].addActionListener(al);
	}
	
	public void setBTMListenerGewonnen(ActionListener zuruck){
		panel.button[0][0].addActionListener(zuruck);
	}
	
	class SchriftPanel extends JPanel {
		
		private JLabel label;
		private JButton button [][] = new JButton[1][1];
		
		public SchriftPanel() {
			GridBagLayout gblayout = new GridBagLayout();
			setLayout(gblayout);
			GridBagConstraints constraints = new GridBagConstraints();
			
			constraints.gridy = 0;
			constraints.gridx = 0;
			
			label = new JLabel();
			add(label, constraints);
			constraints.gridy++;
			button[0][0] = new JButton();
			button[0][0].setText("Zurück zum Hauptmenü");
			button[0][0].setPreferredSize(new Dimension(200, 50));
			add(button[0][0], constraints);		
		}
	}
}
