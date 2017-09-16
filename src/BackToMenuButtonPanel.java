import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel welches einen Button enthält der die Beschriftung "Zurueck zum Hauptmenue" hat
 * @author kiki
 *
 */
public class BackToMenuButtonPanel extends JPanel {
	
	private JButton[][] btmbutton = new JButton[1][1];
	
	public BackToMenuButtonPanel(){
		
		//Layout des Panels
		GridBagLayout gblayout = new GridBagLayout();
		setLayout(gblayout);
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		
		//Erstellen des Buttons
		btmbutton[0][0] = new JButton();
		btmbutton[0][0].setText("Zurück zum Hauptmenü");
		btmbutton[0][0].setPreferredSize(new Dimension(200, 60));
		add(btmbutton[0][0], constraints);
	}
	
	public void addListener(ActionListener btm){
		
		btmbutton[0][0].addActionListener (btm);
	}
	
}
