import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Layout für das Hauptmenue-Fenster des Spiels.
 * Beinhaltet Buttons um ein Spiel zu starten, einen um die Regeln zu lesen und einen um
 * das Spiel zu beenden.
 * @author kiki
 *
 */

public class MenuView extends JFrame {
	ButtonPanel bpanel = new ButtonPanel();
	
	public MenuView() {
		super("Schiffe versenken");
		fensterGenerierenMenu();
	}
	
	public void fensterGenerierenMenu() {
		
		setSize(900, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		getContentPane().add(new JLabel(new ImageIcon("./src/BattleshipLogo.png")));
		setVisible(true);
		bpanel = new ButtonPanel();
		bpanel.setBorder(new EmptyBorder(50,10,100,10));
		add(bpanel, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	public void setCloseListener(ActionListener close) {
		
		bpanel.button[2][0].addActionListener(close);
	}
	
	public void setOpenManualListener(ActionListener manual) {
		bpanel.button[1][0].addActionListener(manual);
	}
	
	public void setStartGameListener(ActionListener start) {
		
		bpanel.button[0][0].addActionListener(start);
	}
	
	class ButtonPanel extends JPanel {
		
		private JButton[][] button = new JButton[3][1];
		
		public ButtonPanel() {
			GridBagLayout gblayout = new GridBagLayout();
			setLayout(gblayout);
			GridBagConstraints constraints = new GridBagConstraints();
			
			constraints.gridy = 0;
			constraints.gridx = 0;
			
			//Button der ein neues Spiel startet
			button[0][0] = new JButton();
			button[0][0].setText("Spiel starten");
			add(button[0][0], constraints);
			
			//Button: Regeln und Anleitung anzeigen
			constraints.gridy = 1;
			button[1][0] = new JButton();
			button[1][0].setText("Regeln & Anleitung");
			add(button[1][0], constraints);
			
			//Button zum Beenden des Spiels
			constraints.gridy = 2;
			button[2][0] = new JButton();
			button[2][0].setText("Spiel beenden");
			add(button[2][0], constraints);
			
			
			for (int i = 0; i<3; i++) {
				button[i][0].setPreferredSize(new Dimension(400, 60));
			}
		}
	}
}
