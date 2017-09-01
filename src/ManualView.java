import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ManualView extends JFrame {

	Model model = new Model();
	ManualButtonPanel mbpanel = new ManualButtonPanel();
	
	public ManualView(){
		super("Schiffe Versenken - Regeln und Kurzanleitung");
		fensterGenerierenManual();
	}

	private void fensterGenerierenManual() {
		//Grundlayout des ManualViews
		setSize(900, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		mbpanel.setBorder(new EmptyBorder(10,10,30,10));
		add(mbpanel, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	class ManualButtonPanel extends JPanel{
		
		private JButton[][] button = new JButton[1][1];
		
		public ManualButtonPanel(){
			
			GridBagLayout mgblayout = new GridBagLayout();
			setLayout(mgblayout);
			GridBagConstraints mconstraints = new GridBagConstraints();
			
			mconstraints.gridy = 0;
			mconstraints.gridx = 0;
			
			button[0][0] = new JButton();
			button[0][0].setText("Zurück zum Hauptmenü");
			button[0][0].setPreferredSize(new Dimension(200, 60));
			add(button[0][0], mconstraints);
		}
	}
}
