import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Layout für das Fenster in dem der Spieler seine Schiffe auf das Feld setzt
 * @author kiki
 *
 */
public class SetShipView extends JFrame{
	Model model = new Model();
	private JButton[][] button = new JButton[model.getFeldgroesse()][model.getFeldgroesse()];
	SetShipPanel setpanel;

	public SetShipView() {
		super("Schiffe versenken");
		fensterGenerierenSetShip();
	}
	
	public void fensterGenerierenSetShip() {
		setSize(900, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		setpanel = new SetShipPanel(model.getFeldgroesse(), 50);
		add(setpanel, BorderLayout.CENTER);
		setVisible(true);
	}
	
	class SetShipPanel extends JPanel{
		
		public SetShipPanel(int i, int j) {
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
