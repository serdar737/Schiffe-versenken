import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Layout für das Fenster in dem der Spieler seine Schiffe auf das Feld setzt
 * @author kiki
 *
 */
public class SetShipView extends JFrame{
	
	SetShipPanel setpanel;
	SchiffsauswahlPanel auswahlpanel;
	private int groesse = 10;
	private JButton[][] button = new JButton[groesse][groesse];

	public SetShipView() {
		super("Schiffe versenken");
		fensterGenerierenSetShip();
	}
	
	public void fensterGenerierenSetShip() {
		setSize(900, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setpanel = new SetShipPanel(groesse, 50);
		add(setpanel, BorderLayout.CENTER);
		auswahlpanel = new SchiffsauswahlPanel();
		add(auswahlpanel, BorderLayout.EAST);
		auswahlpanel.setBorder(new EmptyBorder(10,10,10,70));
		setVisible(true);
	}
	
	
	public void setFeldgroesse(int g){
		groesse = g;
	}
	
	public int getFeldgroesse() {
		return groesse;
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
	
	class SchiffsauswahlPanel extends JPanel{
		
		int k;
		
		public SchiffsauswahlPanel(){
			
			GridBagLayout gblayout = new GridBagLayout();
			setLayout(gblayout);
			GridBagConstraints constraints = new GridBagConstraints();
			
			constraints.gridy = 0;
			constraints.gridx = 0;
			
				for (k=0; k<4; k++) {
					button[k][0] = new JButton();
					add(button[k][0], constraints);
					button[k][0].setBorderPainted(true);
					button[k][0].setPreferredSize(new Dimension(160,50));
					constraints.gridy++;
				}
				
				//Einfügen eines Textfeldes wo steht wozu die Buttons da sind
				//also zb: "Waehle die Platzierungrichtung des Schiffes"
				
				for (k=4; k<6; k++) {
					button[k][0] = new JButton();
					add(button[k][0], constraints);
					button[k][0].setBorderPainted(true);
					button[k][0].setPreferredSize(new Dimension(160,50));
					constraints.gridy++;
				}
				button[0][0].setText("Fünferschiff");
				button[1][0].setText("Viererschiff");
				button[2][0].setText("Dreierschiff");
				button[3][0].setText("Zweierschiff");
				button[4][0].setText("Vertikal setzten");
				button[5][0].setText("Horizontal setzten");
		}
	}
}
		
		
	

