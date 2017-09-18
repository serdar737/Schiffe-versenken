import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class OptionsView extends JFrame{

	Model model = new Model();
	ButtonPanel bpanel = new ButtonPanel();
	BTMButtonPanel btmpanel = new BTMButtonPanel();
	int feldgroesse;
	
	public OptionsView(){
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
		bpanel.setBorder(new EmptyBorder(10,10,150,10));
		//Zurück-Button
		add(btmpanel, BorderLayout.SOUTH);
		btmpanel.setBorder(new EmptyBorder(10,10,150,10));
		setVisible(true);		
	}

	public void setFeldgroessenButtons(ActionListener al){
			 
			bpanel.button[0][0].addActionListener(al);
			
			bpanel.button[1][0].addActionListener(al);
			
			bpanel.button[2][0].addActionListener(al);
	}
	
	class ButtonPanel extends JButton{
		
		private JButton[][] button = new JButton[3][1];
		
		public ButtonPanel(){
			GridBagLayout gblayout = new GridBagLayout();
			setLayout(gblayout);
			GridBagConstraints constraints = new GridBagConstraints();
			
			constraints.gridy = 0;
			constraints.gridx = 0;
			
			//Button der ein neues Spiel startet
			button[0][0] = new JButton();
			button[0][0].setText("8x8");
			add(button[0][0], constraints);
			
			//Button: Regeln und Anleitung anzeigen
			constraints.gridx = 1;
			button[1][0] = new JButton();
			button[1][0].setText("10x10");
			add(button[1][0], constraints);
			
			//Button zum Beenden des Spiels
			constraints.gridx = 2;
			button[2][0] = new JButton();
			button[2][0].setText("12x12");
			add(button[2][0], constraints);
			
			for (int i = 0; i<3; i++){
				button[i][0].setPreferredSize(new Dimension(200, 60));
			}
			
		}
	
	}
	
	/**
	 * Dieses Panel beinhaltet sowohl den Zurück-Button als auch den zum Spiel starten
	 * @author kiki
	 *
	 */
	class BTMButtonPanel extends JPanel{
		
		private JButton[][] button = new JButton[2][1];
		
		public BTMButtonPanel(){
			
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
//			button[0][0].addActionListener (new BackToMenuListener());
		}

	}
	
//	/**
//	 * BackToMenuListener: Der ActionListener mit den Anweisungen was passieren soll wenn der
//	 * "Zurueck" Button gedrückt wurde.
//	 * @author kiki
//	 *
//	 */
//	class BackToMenuListener implements ActionListener{
//		
//		private MenuView menuview;
//
//		public BackToMenuListener(){
//			
//		}
//		
//		@Override
//		public void actionPerformed(ActionEvent backtomenu){
//			this.menuview = new MenuView();
//			dispose();
//		}
//	}
	
//	class StartGameListener implements ActionListener{
//		
//		private SetShipView setshipview;
//		
//		public StartGameListener(){
//			
//		}
//		
//		@Override 
//		public void actionPerformed(ActionEvent start){
//			this.setshipview = new SetShipView();
//			dispose();
//		}
//	}

}
