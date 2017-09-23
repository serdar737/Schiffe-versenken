import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller
 * Wertetabelle Schiffe setzten:
 * 0-unbelegt/frei
 * 1-Schiff gesetzt
 * 2-Gegnerisches Schiff getroffen
 * 3-Kein Treffer
 * 4-Platz um gesetztes Schiff in welchem kein Schiff gesetzt werden darf
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
	boolean beidegesetzt = false;
	boolean imspiel = false;
	int treffer;
	
	
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
				model.spieler = 1;
				setshipview = new SetShipView();
				welcherSpielerUebergabe();
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
				welchesSchiff = 5;
			}
		}
	
	class SetListenerVierer implements ActionListener{
		
		public SetListenerVierer(){

		}
		
		@Override
		public void actionPerformed(ActionEvent set){
			System.out.println("Vorher:"+model.anzahlvierer);
			welchesSchiff = 4;
			System.out.println("Nachher:"+model.anzahlvierer);
		}
	}
	
	class SetListenerDreier implements ActionListener{
		
		public SetListenerDreier(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent set){
			welchesSchiff = 3;
		}
	}
	
	class SetListenerZweier implements ActionListener{
		
		public SetListenerZweier(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent set){
			welchesSchiff = 2;
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
				ugb = new Uebergangsbildschirm(model.spieler2);
				ugb.setWeiterListener(new WeiterListener());
				model.setzeSpielfeldSpieler1();
				System.out.println(model.spieler1Feld[0][0]);
				setshipview.dispose();
				model.spielerWechsel();
				model.setSchiffsanzahl();
			}
			else if (model.anzahlfuenfer == 0 && model.anzahlvierer == 0 && model.anzahldreier == 0 && model.anzahlzweier == 0 && model.spieler == 2){
				ugb = new Uebergangsbildschirm(model.spieler1);
				ugb.setWeiterListener(new WeiterListener());
				model.setzeSpielfeldSpieler2();
				setshipview.dispose();
				model.spielerWechsel();
				beidegesetzt = true;
				imspiel = true;
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
			model.spieler1 = spielereingabe.s1;
			model.spieler2 = spielereingabe.s2;
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
			System.out.println("Feldwert: "+model.getPasst()+" "+ model.getTemp(n, m));
				for (n=0; n<10;n++) {
					for (m=0;m<10;m++) {
						temp = model.getTemp(n, m);
						if (temp==1) {
							setshipview.setSchiffe(n, m, temp);
						}
					}
				}
			
			
			if (model.getPasst() == true){
				if (welchesSchiff == 5){
					model.anzahlfuenfer--;
					if(model.anzahlfuenfer < 1){
						setshipview.deleteFuenfer();
					}
				}
				else if (welchesSchiff == 4){
					model.anzahlvierer--;
					if(model.anzahlvierer < 1){
						setshipview.deleteVierer();
					}
				}
				else if (welchesSchiff == 3){
					model.anzahldreier--;
					if(model.anzahldreier < 1){
						setshipview.deleteDreier();
					}
				}
				else if (welchesSchiff == 2){
					model.anzahlzweier--;
					if(model.anzahlzweier < 1){
						setshipview.deleteZweier();
					}
				}
			}
			welchesSchiff = 0;
		}
	}
	
	/**
	 * Listener fuer den Weiter Button im Uebergangsbildschirm.
	 * Ruft je nachdem wie weit man im Spiel ist entweder nochmal das SetShipView auf oder
	 * wenn bereits beide Spieler ihre Schiffe gesetzt haben ein GameView auf.
	 * @author Kirsten und Serdar
	 *
	 */
	class WeiterListener implements ActionListener{
		
		public WeiterListener(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent weiter){
			
			if (beidegesetzt == true){
				treffer = 1;
				model.setzeTemp();
					int temp = 0;
				gameview = new GameView();
				gameview.setWeiterListenerGV(new WeiterListenerGV());
				gameview.setButtonListenerGV(new ButtonListenerGV());
				for (int n = 0; n<10; n++){
					for (int m = 0; m<10; m++){
						if (model.temp[n][m] == 2){
							gameview.setSchiffeTreffer(n, m, temp);
						}
						else if (model.temp[n][m] == 3){
							gameview.setSchiffeKeinTreffer(n, m, temp);
						}
					}
				}
				ugb.dispose();
			}
			else if (beidegesetzt == false){
				
				for (int n=0; n<10;n++) {
					for (int m=0;m<10;m++) {
					model.temp[n][m] = 0;
					}
				}
				setshipview = new SetShipView();
				welcherSpielerUebergabe();
				setshipview.setListenerFuenfer(new SetListenerFuenfer());
				setshipview.setListenerVierer(new SetListenerVierer());
				setshipview.setListenerDreier(new SetListenerDreier());
				setshipview.setListenerZweier(new SetListenerZweier());
				setshipview.setListenerVertikal(new SetListenerVertikal());
				setshipview.setListenerHorizontal(new SetListenerHorizontal());
				setshipview.setFertigListener(new SetFertigListener());
				setshipview.setButtonListener(new ButtonListener());
				ugb.dispose();
			}
		}
	}
	
	class WeiterListenerGV implements ActionListener{
		
		public WeiterListenerGV(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent w){
			if (treffer == 0){
				if (model.getSpieler() == 1){
					ugb = new Uebergangsbildschirm(model.spieler2);
					ugb.setWeiterListener(new WeiterListener());
					model.setzeSpielfeldSpieler2();
					model.spielerWechsel();
					gameview.dispose();
				}
				else if (model.getSpieler() == 2){
					ugb = new Uebergangsbildschirm(model.spieler1);
					ugb.setWeiterListener(new WeiterListener());
					model.setzeSpielfeldSpieler1();
					model.spielerWechsel();
					gameview.dispose();
				}
			}
		}
	}
	
	class ButtonListenerGV implements ActionListener{
		
		public ButtonListenerGV(){
			
		}
		
		@Override
		public void actionPerformed(ActionEvent b){
			if (treffer ==1){
				String buttonname;
				buttonname = b.getActionCommand();
				int n = Integer.parseInt(buttonname.substring(0,1));
				int m = Integer.parseInt(buttonname.substring(buttonname.length()-1));
				System.out.println(model.getTemp(n, m));
				int temp;
					temp = model.getTemp(n, m);
					if (temp==1) {
						gameview.setSchiffeTreffer(n, m, temp);
						model.temp[n][m] = 2;
					}
					else if (temp == 0 || temp == 4){
						gameview.setSchiffeKeinTreffer(n, m, temp);
						model.temp[n][m] = 3;
					}
					if (model.temp[n][m] == 3){
						treffer--;
					}
					else if (model.temp[n][m] == 2){
						treffer = 1;
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
	
	/**
	 * Uebergibt dem SetshipView einen String mit dem Namen des aktuell spielenden Spielers,
	 * sodass dieser oben am Spielfeldrand ausgegeben werden kann
	 */
	public void welcherSpielerUebergabe(){
		if (model.getSpieler() == 1){
			setshipview.setNameAktuellerSpieler(model.spieler1);
		}
		else if (model.getSpieler() == 2){
			setshipview.setNameAktuellerSpieler(model.spieler2);
		}
	}
	
	public boolean testeSchiffVersenkt(int n, int m) {
		boolean versenkt;
		boolean a = false;
		boolean b = false;
		boolean c = false;
		boolean d = false;
		int tempn = n;
		int tempm = m;
		
		if (tempn>0) {
			tempn--;
			if (model.temp[tempn][tempm] != 1) {
				if (model.temp[tempn][tempm] == 2) {
					if (tempn>0) {
						tempn--;
						if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
							a = true;
							tempn = n;
						}
						else if (model.temp[tempn][tempm] == 2) {
							if (tempn>0) {
								tempn--;
								if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
									a = true;
									tempn = n;
								}
								else if (model.temp[tempn][tempm] == 2) {
									if (tempn>0) {
										tempn--;
										if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
											a = true;
											tempn = n;
										}
										else if (model.temp[tempn][tempm] == 2) {
											if (tempn>0) {
												tempn--;
												if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
													a = true;
													tempn = n;
												}
												else if (model.temp[tempn][tempm] == 2) {
													if (tempn>0) {
														tempn--;
														if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
															a = true;
															tempn = n;
														}
														else if (model.temp[tempn][tempm] == 2) {
															if (tempn>0) {
																tempn--;
																if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
																	a = true;
																	tempn = n;
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		else {
			tempn = n;
		}
		if (tempn<9) {
			tempn++;
			if (model.temp[tempn][tempm] != 1) {
				if (model.temp[tempn][tempm] == 2) {
					if (tempn<9) {
						tempn++;
						if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
							b = true;
							tempn = n;
						}
						else if (model.temp[tempn][tempm] == 2) {
							if (tempn<9) {
								tempn++;
								if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
									b = true;
									tempn = n;
								}
								else if (model.temp[tempn][tempm] == 2) {
									if (tempn<9) {
										tempn++;
										if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
											b = true;
											tempn = n;
										}
										else if (model.temp[tempn][tempm] == 2) {
											if (tempn<9) {
												tempn++;
												if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
													b = true;
													tempn = n;
												}
												else if (model.temp[tempn][tempm] == 2) {
													if (tempn<9) {
														tempn++;
														if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
															b = true;
															tempn = n;
														}
														else if (model.temp[tempn][tempm] == 2) {
															if (tempn<9) {
																tempn++;
																if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
																	b = true;
																	tempn = n;
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			else tempn = n;
		}
		if (tempm>0) {
			tempm--;
			if (model.temp[tempn][tempm] != 1) {
				if (model.temp[tempn][tempm] == 2) {
					tempm--;
					if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
						c = true;
						tempm = m;
					}
					else if (model.temp[tempn][tempm] == 2) {
						tempm--;
						if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
							c = true;
							tempm = m;
						}
						else if (model.temp[tempn][tempm] == 2) {
							tempm--;
							if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
								c = true;
								tempm = m;
							}
							else if (model.temp[tempn][tempm] == 2) {
								tempm--;
								if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
									c = true;
									tempm = m;
								}
								else if (model.temp[tempn][tempm] == 2) {
									tempm--;
									if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
										c = true;
										tempm = m;
									}
									else if (model.temp[tempn][tempm] == 2) {
										tempm--;
										if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
											c = true;
											tempm = m;
										}
									}
								}
							}
						}
					}
				}
			}
			else tempm = m;
		}
		if (tempm<9) {
			tempm++;
			if (model.temp[tempn][tempm] != 1) {
				if (model.temp[tempn][tempm] == 2) {
					tempm++;
					if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
						d = true;
						tempm = m;
					}
					else if (model.temp[tempn][tempm] == 2) {
						tempm++;
						if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
							d = true;
							tempm = m;
						}
						else if (model.temp[tempn][tempm] == 2) {
							tempm++;
							if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
								d = true;
								tempm = m;
							}
							else if (model.temp[tempn][tempm] == 2) {
								tempm++;
								if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
									d = true;
									tempm = m;
								}
								else if (model.temp[tempn][tempm] == 2) {
									tempm++;
									if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
										d = true;
										tempm = m;
									}
									else if (model.temp[tempn][tempm] == 2) {
										tempm++;
										if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
											d = true;
											tempm = m;
										}
									}
								}
							}
						}
					}
				}
			}
			else tempm = m;
		}
		if (a == true && b == true && c == true && d == true) {
			versenkt = true;
		}
		else {
			versenkt = false;
		}
		return versenkt;
	}
}
