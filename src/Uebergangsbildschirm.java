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

public class Uebergangsbildschirm extends JFrame {

	private WechselButton wb;
	
	public Uebergangsbildschirm(String s){
		setSize(900, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		wb = new WechselButton();
		add(wb, BorderLayout.CENTER);
		wb.text.setText("Spielerwechsel! Jetzt ist " + s+" an der Reihe!");
		setVisible(true);
	}
	
	public void setWeiterListener(ActionListener weiter){
		wb.button[0][0].addActionListener(weiter);
	}
	
	class WechselButton extends JPanel{
		
		private JButton[][] button = new JButton[1][1];
		private JLabel text;
		
		public WechselButton(){
			
			GridBagLayout gblayout = new GridBagLayout();
			setLayout(gblayout);
			GridBagConstraints con = new GridBagConstraints();
			
			con.gridy = 0;
			con.gridx = 0;
			
			text = new JLabel();
			add(text, con);
			 //na und dann muss da der Name halt erscheinen
			text.setBorder(new EmptyBorder(10,10,20,10));
			con.gridy++;
			
			button[0][0] = new JButton();
			add(button[0][0], con);
			button[0][0].setBorderPainted(true);
			button[0][0].setPreferredSize(new Dimension(160,50));
			button[0][0].setText("Weiter");
		}
	}
}
