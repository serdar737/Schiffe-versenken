import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * Layout für das Fenster in dem der Spieler seine Schiffe auf das Feld setzt
 * @author kiki
 *
 */
public class SetShipView extends JFrame{
	Model model = new Model();

	public SetShipView() {
		super("Schiffe versenken");
		fensterGenerierenSetShip();
	}
	
	public void fensterGenerierenSetShip() {
		setSize(900, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		setVisible(true);
	}
}
