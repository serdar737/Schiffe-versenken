import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Spielernamen koennen eingegeben werden wenn die Spieler dies gern haetten
 */
public class SpielernamenEingabe extends JFrame {
	
	private ButtonPanel bpanel;
	private NamePanel npanel;
	private JLabel label;
	String s1;
	String s2;

	public SpielernamenEingabe(){
		super("Spielernamen eingeben");
		fensterGenerieren();
	}

	private void fensterGenerieren() {
		setSize(400, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		bpanel = new ButtonPanel();
		add(bpanel, BorderLayout.SOUTH);
		bpanel.setBorder(new EmptyBorder(20,20,20,20));
		npanel = new NamePanel();
		add(npanel, BorderLayout.CENTER);
		npanel.setBorder(new EmptyBorder(20,20,20,20));
		setVisible(true);
	}
	
	public void getSpielerName(){
		s1 = npanel.field1.getText();
		s2 = npanel.field2.getText();
	}
	
	public void setBestaetigeListener(ActionListener b){
		bpanel.button[0][0].addActionListener(b);
	}
	
	class ButtonPanel extends JPanel{
		
		private JButton[][] button = new JButton[7][1];
		
		public ButtonPanel(){
			
			GridBagLayout gblayout = new GridBagLayout();
			setLayout(gblayout);
			GridBagConstraints constraints = new GridBagConstraints();
			
			constraints.gridy = 0;
			constraints.gridx = 0;
			
				button[0][0] = new JButton();
				add(button[0][0], constraints);
				button[0][0].setBorderPainted(true);
				button[0][0].setPreferredSize(new Dimension(120,30));
				button[0][0].setText("Bestätigen");
		}
	}
	
	class NamePanel extends JPanel{
		
		private JTextField field1, field2;
		
		public NamePanel(){
			
			GridBagLayout gblayout = new GridBagLayout();
			setLayout(gblayout);
			GridBagConstraints constraints = new GridBagConstraints();
			
			constraints.gridy = 0;
			constraints.gridx = 0;
			
			label = new JLabel();
			label.setText("Bitte die Spielernamen eingeben (optional):");
			add(label, constraints);
			
			constraints.gridy++;
			field1 = new JTextField("Spieler 1", 15);
			add(field1, constraints);
			field1.setSize(200,70);
			constraints.gridy++;
			
			field2 = new JTextField("Spieler 2", 15);
			add(field2, constraints);
			field2.setSize(200,70);
		}
	}
}
