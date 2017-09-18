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
		this.menuview.setCloseListener(new CloseListener());
		this.menuview.setOpenManualListener(new OpenManualListener());
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
	/**
	 * Ermöglicht es durch Tastendruck zurück zum Hauptmenue zu gelangen
	 * @author kiki
	 *
	 */
	class BackToMenuListener implements ActionListener{
		
		private MenuView menuview;

		public BackToMenuListener(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent a){
			this.menuview = new MenuView();
			this.menuview.setCloseListener(new CloseListener());
			this.menuview.setOpenManualListener(new OpenManualListener());
			//dispose();
		}
	}
	
	/**
	 * Der OpenManualLstener enthaelt die Anweisungen für den Button, welcher das Fenster
	 * mit den Regeln und der Anleitung des Spiels enthaelt
	 * @author kiki
	 *
	 */
	class OpenManualListener implements ActionListener{
		
		private ManualView manualview;
		private Object menuview;

		public OpenManualListener(){
			
		}
		
		@Override
		public void actionPerformed (ActionEvent openmanual){
			this.manualview = new ManualView();
			this.manualview.setBTMListener(new BackToMenuListener());
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
