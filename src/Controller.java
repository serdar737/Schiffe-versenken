import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller
 * @author kiki
 *
 */
public class Controller {

	private GameView gameview;
	private Model model;
	private MenuView menuview;
	private SetShipView setshipview;
	private ManualView manualview;
	
	
	public Controller() {
		this.model = new Model();
		//this.gameview = new GameView();
		this.menuview = new MenuView();
		//this.setshipview = new SetShipView();
	}
	
}
