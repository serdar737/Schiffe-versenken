import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private OptionsView optionsview;
	
	
	public Controller() {
		this.model = new Model();
		this.menuview = new MenuView();
		this.optionsview = new OptionsView();
		this.optionsview.setFeldgroessenButtons(new SetFieldSizeListener());
	}
	
	/**
	 * Dieser Listener weißt den jeweiligen Buttons zu, welche Größe das Spielfeld bekommen 
	 * soll wenn diese gedrückt werden
	 * @author kiki
	 *
	 */
	class SetFieldSizeListener implements ActionListener{
		
		public SetFieldSizeListener(){
			
		}
		
		@Override 
		public void actionPerformed (ActionEvent ae){
			
			String buttonlabel;
			buttonlabel = ae.getActionCommand();
			
			if (buttonlabel.equals("8x8")){
				model.feldgroesse=8;
				System.out.println(model.getFeldgroesse());
			}
			else if (buttonlabel.equals("10x10")){
				model.feldgroesse=10;
			}
			else if (buttonlabel.equals("12x12")){
				model.feldgroesse=12;
			}
		}
	}
}
