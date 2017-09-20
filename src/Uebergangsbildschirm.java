import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Uebergangsbildschirm extends JFrame {

	public Uebergangsbildschirm(){
		setSize(900, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setVisible(true);
	}
	
	class WechselButton extends JPanel{
		
		public WechselButton(){
			
		}
	}
}
