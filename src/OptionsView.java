import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class OptionsView extends JFrame {

	Model model = new Model();
	ButtonPanel bpanel = new ButtonPanel();
	BTMButtonPanel btmpanel = new BTMButtonPanel();
	int feldgroesse;
	
	public OptionsView() {
		super("Schiffe Versenken");
		fensterGenerierenOptions();
	}

	private void fensterGenerierenOptions() {
		//Grundlayout des ManualViews
		setSize(900, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		//ButtonPanel
		add(bpanel, BorderLayout.CENTER);
		bpanel.setBorder(new EmptyBorder(150,10,150,10));
		//Zurück-Button
		add(btmpanel, BorderLayout.SOUTH);
		btmpanel.setBorder(new EmptyBorder(10,10,150,10));
		setVisible(true);		
	}

	public void setFeldgroessenButtons(ActionListener al) {
			 
			bpanel.button[0][0].addActionListener(al);	
	}
	
	public void setBTMListener(ActionListener btm) {
		
		btmpanel.button[0][0].addActionListener (btm);
	}
	
	class ButtonPanel extends JButton {
		
		private JButton[][] button = new JButton[3][1];
		
		public ButtonPanel(){
			GridBagLayout gblayout = new GridBagLayout();
			setLayout(gblayout);
			GridBagConstraints constraints = new GridBagConstraints();
			
			constraints.gridy = 0;
			constraints.gridx = 0;
			
			constraints.gridx = 1;
			button[0][0] = new JButton();
			button[0][0].setText("10x10");
			button[0][0].setPreferredSize(new Dimension(200, 60));
			add(button[0][0], constraints);
			
		}
	
	}
	
	/**
	 * Dieses Panel beinhaltet sowohl den Zurück-Button als auch den zum Spiel starten
	 * @author kiki
	 *
	 */
	class BTMButtonPanel extends JPanel {
		
		private JButton[][] button = new JButton[2][1];
		
		public BTMButtonPanel() {
			
			//Layout
			GridBagLayout btmlayout = new GridBagLayout();
			setLayout(btmlayout);
			GridBagConstraints btmconstraints = new GridBagConstraints();
			
			btmconstraints.gridy = 0;
			btmconstraints.gridx = 0;
			
//			//Erstellen des eigentlichen Buttons, setzten der Groesse, 
//			//sowie hinzufuegen des ActionListeners
			button[0][0] = new JButton();
			button[0][0].setText("Zurück zum Hauptmenü");
			button[0][0].setPreferredSize(new Dimension(200, 60));
			add(button[0][0], btmconstraints);
		}
	}
}
