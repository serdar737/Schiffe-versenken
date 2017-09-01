/**
 * Controller
 * @author kiki
 *
 */
public class Controller {

	//Bin mir grad nicht sicher ob wir das ueberhaupt brauchen
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
