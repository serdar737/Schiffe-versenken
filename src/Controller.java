import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller
 * @author kiki
 *
 */
public class Controller {

	private Model model;
	private MenuView menuview;
	private ManualView manualview;
	private OptionsView optionsview;
	private SetShipView setshipview;
	private GameView gameview;
	private SpielernamenEingabe spielereingabe;
	private Uebergangsbildschirm ugb;
	private int welchesSchiff;
	
	
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
			optionsview = new OptionsView();
			optionsview.setFeldgroessenButtons(new SetFieldSizeListener());
			optionsview.setBTMListener(new OptionBackToMenuListener());
			menuview.dispose();
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
				setshipview = new SetShipView();
				setshipview.setFeldgroesse(8);
				optionsview.dispose();
//				setshipview.panelGenerieren();
			}
			else if (buttonlabel.equals("10x10")){
				model.setFeldgroesse(10);
				
				setshipview = new SetShipView();
				setshipview.setListenerFuenfer(new SetListenerFuenfer());
				setshipview.setListenerVierer(new SetListenerVierer());
				setshipview.setListenerDreier(new SetListenerDreier());
				setshipview.setListenerZweier(new SetListenerZweier());
				setshipview.setListenerVertikal(new SetListenerVertikal());
				setshipview.setListenerHorizontal(new SetListenerHorizontal());
				setshipview.setFertigListener(new SetFertigListener());
				setshipview.setButtonListener(new ButtonListener());
				spielereingabe = new SpielernamenEingabe();
				spielereingabe.setBestaetigeListener(new BestaetigeListener());
				optionsview.dispose();
//				setshipview.setFeldgroesse(model.getFeldgroesse());
			}
			else if (buttonlabel.equals("12x12")){
				model.setFeldgroesse(12);
				setshipview = new SetShipView();
				setshipview.setFeldgroesse(model.getFeldgroesse());
				optionsview.dispose();
			}
//			setshipview.fensterGenerierenSetShip();
//			setshipview.panelGenerieren();
		}
	}
	
	class SetListenerFuenfer implements ActionListener{
			
			public SetListenerFuenfer(){
				
			}
			
			@Override
			public void actionPerformed(ActionEvent set){
				model.anzahlfuenfer--;
				welchesSchiff = 5;
				if(model.anzahlfuenfer < 1){
					setshipview.deleteFuenfer();
				}
			}
		}
	
	class SetListenerVierer implements ActionListener{
		
		public SetListenerVierer(){

		}
		
		@Override
		public void actionPerformed(ActionEvent set){
			System.out.println("Vorher:"+model.anzahlvierer);
			model.anzahlvierer--;
			welchesSchiff = 4;
			System.out.println("Nachher:"+model.anzahlvierer);
			
			if(model.anzahlvierer < 1){
				setshipview.deleteVierer();
			}
		}
	}
	
	class SetListenerDreier implements ActionListener{
		
		public SetListenerDreier(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent set){
			model.anzahldreier--;
			welchesSchiff = 3;
			if(model.anzahldreier < 1){
				setshipview.deleteDreier();
			}
		}
	}
	
	class SetListenerZweier implements ActionListener{
		
		public SetListenerZweier(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent set){
			model.anzahlzweier--;
			welchesSchiff = 2;
			if(model.anzahlzweier < 1){
				setshipview.deleteZweier();
			}
		}
	}
	
	class SetListenerVertikal implements ActionListener{
		
		public SetListenerVertikal(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent set){
			
			setshipview.setVertikal(true);
			
			if (model.anzahlfuenfer == 0 && model.anzahlvierer == 0 && model.anzahldreier == 0 && model.anzahlzweier == 0){
			setshipview.enableVertikalHorizontal();
			}
		}
	}
	
	class SetListenerHorizontal implements ActionListener{
		
		public SetListenerHorizontal(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent set){
			
			setshipview.setVertikal(false);
			if (model.anzahlfuenfer == 0 && model.anzahlvierer == 0 && model.anzahldreier == 0 && model.anzahlzweier == 0){
			setshipview.enableVertikalHorizontal();
			}
		}
	}
	
	class SetFertigListener implements ActionListener{
			
		public SetFertigListener(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent set){
			if (model.anzahlfuenfer == 0 && model.anzahlvierer == 0 && model.anzahldreier == 0 && model.anzahlzweier == 0 && model.spieler == 1){
				System.out.println(model.spieler+" vorher");
				model.spielerWechsel();
				ugb = new Uebergangsbildschirm();
				model.setSchiffsanzahl();
				System.out.println(model.spieler+" nachher");
			}
			else if (model.anzahlfuenfer == 0 && model.anzahlvierer == 0 && model.anzahldreier == 0 && model.anzahlzweier == 0 && model.spieler == 2){
				ugb = new Uebergangsbildschirm();
				setshipview.dispose();
			}
		}
	}

	/**
	 * Ermöglicht es durch Tastendruck von der Spielauswahl zurück zum Hauptmenue zu gelangen
	 * @author kiki
	 */
	class OptionBackToMenuListener implements ActionListener{
		
//		private MenuView menuview;

		public OptionBackToMenuListener(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent a){
			
			menuview = new MenuView();
			menuview.setStartGameListener(new StartGameListener());
			menuview.setCloseListener(new CloseListener());
			menuview.setOpenManualListener(new OpenManualListener());
			optionsview.dispose();
		}
	}
	
	class ManualBackToMenuListener implements ActionListener{
		
//		private MenuView menuview;

		public ManualBackToMenuListener(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent a){
			
			menuview = new MenuView();
			menuview.setStartGameListener(new StartGameListener());
			menuview.setCloseListener(new CloseListener());
			menuview.setOpenManualListener(new OpenManualListener());
			manualview.dispose();
		}
	}
	
	/**
	 * Der OpenManualListener enthaelt die Anweisungen für den Button, welcher das Fenster
	 * mit den Regeln und der Anleitung des Spiels oeffnet
	 * @author kiki
	 */
	class OpenManualListener implements ActionListener{
		
//		private ManualView manualview;
//		private Object menuview;

		public OpenManualListener(){
			
		}
		
		@Override
		public void actionPerformed (ActionEvent openmanual){
			
			manualview = new ManualView();
			manualview.setBTMListener(new ManualBackToMenuListener());
			menuview.dispose();
		}
	}
	
	class BestaetigeListener implements ActionListener{
		
		public BestaetigeListener(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent b){
			spielereingabe.getSpielerName(); 
			System.out.println(model.spieler1+"Vorher");
			model.spieler1 = spielereingabe.s1;
			model.spieler2 = spielereingabe.s2;
			System.out.println(model.spieler1);
			System.out.println(model.spieler2);
			spielereingabe.dispose();
		}
	}
	
	class ButtonListener implements ActionListener{
		
		public ButtonListener(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent button){
			String buttonname;
			buttonname = button.getActionCommand();
			int n = Integer.parseInt(buttonname.substring(0,1));
			int m = Integer.parseInt(buttonname.substring(buttonname.length()-1));
			model.schiffeSetzen(welchesSchiff, setshipview.getVertikal(), n, m);
			int temp;
			for (n=0; n<10;n++) {
				for (m=0;m<10;m++) {
					temp = model.getTemp(n, m);
					if (temp==1) {
						setshipview.setSchiffe(n, m, temp);
					}
				}
			}
		}
	}
	
	/**
	 * Der CloseListener ist dafür verantwortlich, dass das Programm vom Menue geschlossen werden kann.
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
