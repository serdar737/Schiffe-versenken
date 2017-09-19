import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Spielernamen koennen eingegeben werden wenn die Spieler dies gern haetten
 */
public class SpielernamenEingabe extends JFrame {
	
	private ButtonPanel bpanel ;

	public SpielernamenEingabe(){
		super("Spielernamen eingeben");
		fensterGenerieren();
	}

	private void fensterGenerieren() {
		setSize(400, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		bpanel = new ButtonPanel();
		add(bpanel, BorderLayout.SOUTH);
		bpanel.setBorder(new EmptyBorder(20,20,20,20));
		setVisible(true);
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
}
