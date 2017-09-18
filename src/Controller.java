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
		this.menuview.setStartGameListener(new StartGameListener());
		this.menuview.setCloseListener(new CloseListener());
		this.menuview.setOpenManualListener(new OpenManualListener());
	}
	
	class StartGameListener implements ActionListener{
		
		
		
		public StartGameListener(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent startgame){
			menuview.dispose();
			optionsview = new OptionsView();
			optionsview.setFeldgroessenButtons(new SetFieldSizeListener());
			optionsview.setBTMListener(new OptionBackToMenuListener());
			
		}
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
				model.setFeldgroesse(8);
				optionsview.dispose();
				setshipview = new SetShipView();
				setshipview.setFeldgroesse(8);
				setshipview.panelGenerieren();
				System.out.println(setshipview.getFeldgroesse());
			}
			else if (buttonlabel.equals("10x10")){
				model.setFeldgroesse(10);
				optionsview.dispose();
				setshipview = new SetShipView();
				setshipview.setFeldgroesse(model.getFeldgroesse());
			}
			else if (buttonlabel.equals("12x12")){
				model.setFeldgroesse(12);
				optionsview.dispose();
				setshipview = new SetShipView();
				setshipview.setFeldgroesse(model.getFeldgroesse());
			}
			setshipview.fensterGenerierenSetShip();
		}
	}
	/**
	 * Ermöglicht es durch Tastendruck zurück zum Hauptmenue zu gelangen
	 * @author kiki
	 *
	 */
	class OptionBackToMenuListener implements ActionListener{
		
//		private MenuView menuview;

		public OptionBackToMenuListener(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent a){
			
			optionsview.dispose();
			menuview = new MenuView();
			menuview.setStartGameListener(new StartGameListener());
			menuview.setCloseListener(new CloseListener());
			menuview.setOpenManualListener(new OpenManualListener());
			
		}
	}
	
	class ManualBackToMenuListener implements ActionListener{
		
//		private MenuView menuview;

		public ManualBackToMenuListener(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent a){
			
			manualview.dispose();
			menuview = new MenuView();
			menuview.setStartGameListener(new StartGameListener());
			menuview.setCloseListener(new CloseListener());
			menuview.setOpenManualListener(new OpenManualListener());
			
		}
	}
	
	/**
	 * Der OpenManualLstener enthaelt die Anweisungen für den Button, welcher das Fenster
	 * mit den Regeln und der Anleitung des Spiels enthaelt
	 * @author kiki
	 *
	 */
	class OpenManualListener implements ActionListener{
		
//		private ManualView manualview;
//		private Object menuview;

		public OpenManualListener(){
			
		}
		
		@Override
		public void actionPerformed (ActionEvent openmanual){
			menuview.dispose();
			manualview = new ManualView();
			manualview.setBTMListener(new ManualBackToMenuListener());
			//dispose();
		}
	}
	
	/**
	 * Der CloseListener ist dafür verantwortlich, dass das Programm geschlossen werden kann.
	 * @author kiki
	 */
	class CloseListener implements ActionListener{
		
		public CloseListener(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent close){
			System.exit(0);
		}
	}
}
