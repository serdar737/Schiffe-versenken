import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Layout für das Hauptmenue-Fenster des Spiels.
 * Beinhaltet Buttons um ein Spiel zu starten, einen um die Regeln zu lesen und einen um
 * das Spiel zu beenden.
 * @author kiki
 *
 */

public class MenuView extends JFrame{
	Model model = new Model();
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
		bpanel = new ButtonPanel();
		bpanel.setBorder(new EmptyBorder(10,10,150,10));
		add(bpanel, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	class ButtonPanel extends JPanel{
		
		private JButton[][] button = new JButton[3][1];
		
		public ButtonPanel(){
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
			button[1][0].addActionListener(new OpenManualListener());
			
			//Button zum Beenden des Spiels
			constraints.gridy = 2;
			button[2][0] = new JButton();
			button[2][0].setText("Spiel beenden");
			add(button[2][0], constraints);
			button[2][0].addActionListener(new CloseListener());
			
			for (int i = 0; i<3; i++){
				button[i][0].setPreferredSize(new Dimension(400, 60));
			}
		}
	}
	/**
	 * Der CloseListener ist dafür verantwortlich, dass das Programm geschlossen werden kann.
	 * @author kiki
	 */
	class CloseListener implements ActionListener{
		
		public CloseListener(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent close){
			System.exit(0);
		}
	}
	
	/**
	 * Der OpenManualLstener enthaelt die Anweisungen für den Button, welcher das Fenster
	 * mit den Regeln und der Anleitung des Spiels enthaelt
	 * @author kiki
	 *
	 */
	class OpenManualListener implements ActionListener{
		
		private ManualView manualview;
		private Object menuview;

		public OpenManualListener(){
			
		}
		
		@Override
		public void actionPerformed (ActionEvent openmanual){
			this.manualview = new ManualView();
			dispose();
		}
	}
}
