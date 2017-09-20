import java.awt.BorderLayout;
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
 * Layout für das Fenster in dem der Spieler seine Schiffe auf das Feld setzt
 * @author kiki
 *
 */
public class SetShipView extends JFrame{
	
	SetShipPanel setpanel;
	SchiffsauswahlPanel auswahlpanel;
	AktuellerSpieler as;
	private int groesse = 10;
	boolean vertikal; 

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
		as = new AktuellerSpieler();
		add(as, BorderLayout.NORTH);
		as.setBorder(new EmptyBorder(30,10,10,10));
		setVisible(true);
	}
	
	public void setFeldgroesse(int g){
		groesse = g;
	}
	
	public int getFeldgroesse() {
		return groesse;
	}
	
	public void setListenerFuenfer(ActionListener set){
		auswahlpanel.sbutton[0][0].addActionListener(set);
	}
	
	public void setListenerVierer(ActionListener set){
		auswahlpanel.sbutton[1][0].addActionListener(set);
	}
	
	public void setListenerDreier(ActionListener set){
		auswahlpanel.sbutton[2][0].addActionListener(set);
	}
	
	public void setListenerZweier(ActionListener set){
		auswahlpanel.sbutton[3][0].addActionListener(set);
	}
	
	public void setListenerVertikal(ActionListener set){
		auswahlpanel.sbutton[4][0].addActionListener(set);
	}
	
	public void setListenerHorizontal(ActionListener set){
		auswahlpanel.sbutton[5][0].addActionListener(set);
	}
	
	public void setFertigListener(ActionListener fertig){
		auswahlpanel.sbutton[6][0].addActionListener(fertig);
	}
	
	public void deleteFuenfer(){
		auswahlpanel.sbutton[0][0].setEnabled(false);
	}
	
	public void deleteVierer(){
		auswahlpanel.sbutton[1][0].setEnabled(false);
	}
	
	public void deleteDreier(){
		auswahlpanel.sbutton[2][0].setEnabled(false);
	}
	
	public void deleteZweier(){
		auswahlpanel.sbutton[3][0].setEnabled(false);
	}
	
	public void enableVertikalHorizontal(){
		auswahlpanel.sbutton[4][0].setEnabled(false);
		auswahlpanel.sbutton[5][0].setEnabled(false);
	}
	
	public void setVertikal(boolean b){
		vertikal = b;
	}
	
	public boolean getVertikal(){
		return vertikal;
	}
	
	public void setButtonListener(ActionListener button){
		
		int k;
		int h;
		
		for (k=0; k<groesse;k++) {
			for (h=0; h<groesse; h++){
				setpanel.button[k][h].setActionCommand(""+k+h);
				setpanel.button[k][h].addActionListener(button);
			}
		}
	}
	class SetShipPanel extends JPanel{
		
		private JButton[][] button = new JButton[groesse][groesse];
		
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
		private JButton[][] sbutton = new JButton[7][1];
		
		public SchiffsauswahlPanel(){
			
			GridBagLayout gblayout = new GridBagLayout();
			setLayout(gblayout);
			GridBagConstraints constraints = new GridBagConstraints();
			
			constraints.gridy = 0;
			constraints.gridx = 0;
			
				for (k=0; k<4; k++) {
					sbutton[k][0] = new JButton();
					add(sbutton[k][0], constraints);
					sbutton[k][0].setBorderPainted(true);
					sbutton[k][0].setPreferredSize(new Dimension(160,50));
					constraints.gridy++;
				}
				
				//Einfügen eines Textfeldes wo steht wozu die Buttons da sind
				//also zb: "Waehle die Platzierungrichtung des Schiffes"
				
				for (k=4; k<7; k++) {
					sbutton[k][0] = new JButton();
					add(sbutton[k][0], constraints);
					sbutton[k][0].setBorderPainted(true);
					sbutton[k][0].setPreferredSize(new Dimension(160,50));
					constraints.gridy++;
				}
				sbutton[0][0].setText("Fünferschiff");
				sbutton[1][0].setText("Viererschiff");
				sbutton[2][0].setText("Dreierschiff");
				sbutton[3][0].setText("Zweierschiff");
				sbutton[4][0].setText("Vertikal setzten");
				sbutton[5][0].setText("Horizontal setzten");
				sbutton[6][0].setText("Fertig");
		}
	}
	
	class AktuellerSpieler extends JPanel{
		
		private JLabel spielerlabel;

		public AktuellerSpieler(){
			
			GridBagLayout gblayout = new GridBagLayout();
			setLayout(gblayout);
			GridBagConstraints con = new GridBagConstraints();
			
			con.gridy = 0;
			con.gridx = 0;
			
			spielerlabel = new JLabel();
			spielerlabel.setText("Momentan am Zug: ");
			add(spielerlabel, con);
			
		}
	}
}
		
		
	

