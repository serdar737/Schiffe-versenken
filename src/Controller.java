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
	private GewonnenView gewonnenview;
	private int welchesSchiff;
	boolean beidegesetzt = false;
	boolean imspiel = false;
	int treffer;
	boolean versenkt;
	boolean gewonnen = false;
	
	int an1, an2, an3, an4, an5, an6 = -1;
	int bn1, bn2, bn3, bn4, bn5, bn6 = -1;
	int cn1, cn2, cn3, cn4, cn5, cn6 = -1;
	int dn1, dn2, dn3, dn4, dn5, dn6 = -1;
	int am1, am2, am3, am4, am5, am6 = -1;
	int bm1, bm2, bm3, bm4, bm5, bm6 = -1;
	int cm1, cm2, cm3, cm4, cm5, cm6 = -1;
	int dm1, dm2, dm3, dm4, dm5, dm6 = -1;
	
	
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
						else if (model.temp[n][m] == 6){
							gameview.setSchiffeTreffer(n, m, temp);
							gameview.setSchiffVersenkt(n, m);
						}
					}
				}
				
				if(model.getSpieler() == 1){
					for (int n = 0; n<10; n++){
						for (int m = 0; m<10; m++){
							if (model.spieler1Feld[n][m] == 1){
								gameview.setzeTrefferKlein(n, m);
							}
							else if (model.spieler1Feld[n][m] == 2){
								gameview.setzeTrefferGetroffen(n, m);
							}
							else if (model.spieler1Feld[n][m] == 6){
								gameview.setzeVersenkt(n, m);
							}
							else if (model.spieler1Feld[n][m] == 3){
								gameview.setzeSchuss(n, m);
							}
						}
					}
				}
				
				else if(model.getSpieler() == 2){
					for (int n = 0; n<10; n++){
						for (int m = 0; m<10; m++){
							if (model.spieler2Feld[n][m] == 1){
								gameview.setzeTrefferKlein(n, m);
							}
							else if (model.spieler2Feld[n][m] == 2){
								gameview.setzeTrefferGetroffen(n, m);
							}
							else if (model.spieler2Feld[n][m] == 6){
								gameview.setzeVersenkt(n, m);
							}
							else if (model.spieler2Feld[n][m] == 3){
								gameview.setzeSchuss(n, m);
							}
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
							gewonnen();
							if (gewonnen == true){
								gewonnenview = new GewonnenView(model.spieler1);
								gameview.dispose();
							}
							else {
								ugb = new Uebergangsbildschirm(model.spieler2);
								ugb.setWeiterListener(new WeiterListener());
								model.setzeSpielfeldSpieler2();
								model.spielerWechsel();
								gameview.dispose();
							}
					}
				else if (model.getSpieler() == 2){
						gewonnen();
						if (gewonnen == true){
							gewonnenview = new GewonnenView(model.spieler1);
							gameview.dispose();
						}
						else{
							ugb = new Uebergangsbildschirm(model.spieler1);
							ugb.setWeiterListener(new WeiterListener());
							model.setzeSpielfeldSpieler1();
							model.spielerWechsel();
							gameview.dispose();
						}
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
						testeSchiffVersenkt(n, m);
						if (versenkt == true){
						
						}
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
	
	/**
	 * Diese Methode wird im ButtonListenerGV aufgerufen, wenn der Spieler ein Schiff trifft. Sie untersucht die unmittelbare Umgebung des Schiffes und gibt true aus,
	 * wenn die benachbarten Felder kein Teil des Schiffes ist. Diese Methode gibt also nur true zurück, wenn alle Teile eines Schiffes getroffen wurden und dient deshalb
	 * dazu, dem Spieler zu signalisieren, dass er ein Schiff versenkt hat.
	 * @param n
	 * @param m
	 * @return
	 */
	public boolean testeSchiffVersenkt(int n, int m) {
		boolean a = false;
		boolean b = false;
		boolean c = false;
		boolean d = false;
		int tempn = n;
		int tempm = m;
		
		if (tempn>0) {
			tempn--; // Das obere Nachbarfeld wird untersucht
			if (model.temp[tempn][tempm] != 1) {
				if (model.temp[tempn][tempm] == 2) { // Wenn das Feld auch schon getroffen wurde, wird das Feld untersucht, welches darueber liegt
					an1 = tempn;
					am1 = tempm;
					if (tempn>0) {
						tempn--;
						if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) { // Falls das Feld kein Schiffteil entdeckt, wird die Arbeit nach oben hin beendet
							a = true;
							tempn = n;
						}
						else if (model.temp[tempn][tempm] == 2) {
							an2 = tempn;
							am2 = tempm;
							if (tempn>0) {
								tempn--;
								if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
									a = true;
									tempn = n;
								}
								else if (model.temp[tempn][tempm] == 2) {
									an3 = tempn;
									am3 = tempm;
									if (tempn>0) {
										tempn--;
										if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
											a = true;
											tempn = n;
										}
										else if (model.temp[tempn][tempm] == 2) {
											an4 = tempn;
											am4 = tempm;
											if (tempn>0) {
												tempn--;
												if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
													a = true;
													tempn = n;
												}
												else if (model.temp[tempn][tempm] == 2) {
													an5 = tempn;
													am5 = tempm;
													if (tempn>0) {
														tempn--;
														if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
															a = true;
															tempn = n;
														}
														else if (model.temp[tempn][tempm] == 2) {
															an6 = tempn;
															am6 = tempm;
															if (tempn>0) {
																tempn--;
																if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
																	a = true;
																	tempn = n;
																}
															}
															else {
																a = true;
															}
														}
													}
													else {
														a = true;
													}
												}
											}
											else {
												a = true;
											}
										}
									}
									else {
										a = true;
									}
								}
							}
							else {
								a = true;
							}
						}
					}
					else {
						a = true;
					}
				}
				else {
					a = true;
				}
			}
			else {
				a = false;
			}
			
		}
		else {
			a = true;
		}
		
		tempn = n;
		if (tempn<9) {
			tempn++; // Das untere Nachbarfeld wird untersucht
			if (model.temp[tempn][tempm] != 1) {
				if (model.temp[tempn][tempm] == 2) {
					bn1 = tempn; 
					bm1 = tempm;
					if (tempn<9) {
						tempn++;
						if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
							b = true;
							tempn = n;
						}
						else if (model.temp[tempn][tempm] == 2) {
							bn2 = tempn; 
							bm2 = tempm;
							if (tempn<9) {
								tempn++;
								if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
									b = true;
									tempn = n;
								}
								else if (model.temp[tempn][tempm] == 2) {
									bn3 = tempn; 
									bm3 = tempm;
									if (tempn<9) {
										tempn++;
										if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
											b = true;
											tempn = n;
										}
										else if (model.temp[tempn][tempm] == 2) {
											bn4 = tempn; 
											bm4 = tempm;
											if (tempn<9) {
												tempn++;
												if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
													b = true;
													tempn = n;
												}
												else if (model.temp[tempn][tempm] == 2) {
													bn5 = tempn; 
													bm5 = tempm;
													if (tempn<9) {
														tempn++;
														if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
															b = true;
															tempn = n;
														}
														else if (model.temp[tempn][tempm] == 2) {
															bn6 = tempn; 
															bm6 = tempm;
															if (tempn<9) {
																tempn++;
																if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
																	b = true;
																	tempn = n;
																}
															}
															else {
																b = true;
															} 
														}
													}
													else {
														b = true;
													} 
												}
											}
											else {
												b = true;
											} 
										}
									}
									else {
										b = true;
									} 
								}
							}
							else {
								b = true;
							} 
						}
					}
					else {
						b = true;
					} 
				}
				else {
					b = true;
				} 
				
			}
			else {
				b = false;
			}
		}
		else {
			b = true;
		}
		
		tempn = n;
		if (tempm>0) {
			tempm--; // Das linke Nachbarfeld wird untersucht.
			if (model.temp[tempn][tempm] != 1) {
				if (model.temp[tempn][tempm] == 2) {
					cn1 = tempn;
					cm1 = tempm;
					if (tempm>0) {
						tempm--;
						if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
							c = true;
							tempm = m;
						}
						else if (model.temp[tempn][tempm] == 2) {
							cn2 = tempn;
							cm2 = tempm;
							if (tempm>0) {
								tempm--;
								if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
									c = true;
									tempm = m;
								}
								else if (model.temp[tempn][tempm] == 2) {
									cn3 = tempn;
									cm3 = tempm;
									if (tempm>0) {
										tempm--;
										if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
											c = true;
											tempm = m;
										}
										else if (model.temp[tempn][tempm] == 2) {
											cn4 = tempn;
											cm4 = tempm;
											if (tempm>0) {
												tempm--;
												if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
													c = true;
													tempm = m;
												}
												else if (model.temp[tempn][tempm] == 2) {
													cn5 = tempn;
													cm5 = tempm;
													if (tempm>0) {
														tempm--;
														if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
															c = true;
															tempm = m;
														}
														else if (model.temp[tempn][tempm] == 2) {
															cn6 = tempn;
															cm6 = tempm;
															if (tempm>0) {
																tempm--;
																if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
																	c = true;
																	tempm = m;
																}
															}
															else {
																c = true;
															} 
														}
													}
													else {
														c = true;
													} 
												}
											}
											else {
												c = true;
											} 
										}
									}
									else {
										c = true;
									} 
								}
							}
							else {
								c = true;
							} 
						}
					}
					else {
						c = true;
					} 
				}
				else {
					c = true;
				} 
			}
			else {
				c = false;
			}
		}
		else {
			c = true;
		} 
		
		tempm = m;
		if (tempm<9) {
			tempm++; //Das rechte Nachbarfeld wird untersucht
			if (model.temp[tempn][tempm] != 1) {
				if (model.temp[tempn][tempm] == 2) {
					dn1 = tempn;
					dm1 = tempm;
					if (tempm<9) {
						tempm++;
						if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
							d = true;
							tempm = m;
						}
						else if (model.temp[tempn][tempm] == 2) {
							dn2 = tempn;
							dm2 = tempm;
							if (tempm<9) {
								tempm++;
								if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
									d = true;
									tempm = m;
								}
								else if (model.temp[tempn][tempm] == 2) {
									dn3 = tempn;
									dm3 = tempm;
									if (tempm<9) {
										tempm++;
										if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
											d = true;
											tempm = m;
										}
										else if (model.temp[tempn][tempm] == 2) {
											dn4 = tempn;
											dm4 = tempm;
											if (tempm<9) {
												tempm++;
												if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
													d = true;
													tempm = m;
												}
												else if (model.temp[tempn][tempm] == 2) {
													dn5 = tempn;
													dm5 = tempm;
													if (tempm<9) {
														tempm++;
														if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
															d = true;
															tempm = m;
														}
														else if (model.temp[tempn][tempm] == 2) {
															dn6 = tempn;
															dm6 = tempm;
															if (tempm<9) {
																tempm++;
																if (model.temp[tempn][tempm] == 3 || model.temp[tempn][tempm] == 4) {
																	d = true;
																	tempm = m;
																}
															}
															else {
																d = true;
															}
														}
													}
													else {
														d = true;
													}
												}
											}
											else {
												d = true;
											}
										}
									}
									else {
										d = true;
									}
								}
							}
							else {
								d = true;
							}
						}
					}
					else {
						d = true;
					}
				}
				else {
					d = true;
				}
			}
			else {
				tempm = m;
				d = false;
			}
		}
		else {
			d = true;
		}
		
		
		if (a == true && b == true && c == true && d == true) {
			versenkt = true;
			model.temp[n][m] = 6;
			gameview.setSchiffVersenkt(n, m);
			if (an1 != -1 && am1 != -1){
				gameview.setSchiffVersenkt(an1, am1);
				model.temp[an1][am1] = 6;
				an1 = -1;
				am1 = -1;
			}
			
			if (an2 != -1 && am2 != -1){
				gameview.setSchiffVersenkt(an2, am2);
				model.temp[an2][am2] = 6;
				an2 = -1;
				am2 = -1;
			}
			
			if (an3 != -1 && am3 != -1){
				gameview.setSchiffVersenkt(an3, am3);
				model.temp[an3][am3] = 6;
				an3 = -1;
				am3 = -1;
			}
			
			if (an4 != -1 && am4 != -1){
				gameview.setSchiffVersenkt(an4, am4);
				model.temp[an4][am4] = 6;
				an4 = -1;
				am4 = -1;
			}
			
			if (an5 != -1 && am5 != -1){
				gameview.setSchiffVersenkt(an5, am5);
				model.temp[an5][am5] = 6;
				an5 = -1;
				am5 = -1;
			}
			
			if (an6 != -1 && am6 != -1){
				gameview.setSchiffVersenkt(an6, am6);
				model.temp[an6][am6] = 6;
				an6 = -1;
				am6 = -1;
			}
			
			if (bn1 != -1 && bm1 != -1){
				gameview.setSchiffVersenkt(bn1, bm1);
				model.temp[bn1][bm1] = 6;
				bn1 = -1;
				bm1 = -1;
			}
			
			if (bn2 != -1 && bm2 != -1){
				gameview.setSchiffVersenkt(bn2, bm2);
				model.temp[bn2][bm2] = 6;
				bn2 = -1;
				bm2 = -1;
			}
			
			if (bn3 != -1 && bm3 != -1){
				gameview.setSchiffVersenkt(bn3, bm3);
				model.temp[bn3][bm3] = 6;
				bn3 = -1;
				bm3 = -1;
			}
			
			if (bn4 != -1 && bm4 != -1){
				gameview.setSchiffVersenkt(bn4, bm4);
				model.temp[bn4][bm4] = 6;
				bn4 = -1;
				bm4 = -1;
			}
			
			if (bn5 != -1 && bm5 != -1){
				gameview.setSchiffVersenkt(bn5, bm5);
				model.temp[bn5][bm5] = 6;
				bn5 = -1;
				bm5 = -1;
			}
			
			if (bn6 != -1 && bm6 != -1){
				gameview.setSchiffVersenkt(bn6, bm6);
				model.temp[bn6][bm6] = 6;
				bn6 = -1;
				bm6 = -1;
			}
			
			if (cn1 != -1 && cm1 != -1){
				gameview.setSchiffVersenkt(cn1, cm1);
				model.temp[cn1][cm1] = 6;
				cn1 = -1;
				cm1 = -1;
			}
			
			if (cn2 != -1 && cm2 != -1){
				gameview.setSchiffVersenkt(cn2, cm2);
				model.temp[cn2][cm2] = 6;
				cn2 = -1;
				cm2 = -1;
			}
			
			if (cn3 != -1 && cm3 != -1){
				gameview.setSchiffVersenkt(cn3, cm3);
				model.temp[cn3][cm3] = 6;
				cn3 = -1;
				cm3 = -1;
			}
			
			if (cn4 != -1 && cm4 != -1){
				gameview.setSchiffVersenkt(cn4, cm4);
				model.temp[cn4][cm4] = 6;
				cn4 = -1;
				cm4 = -1;
			}
			
			if (cn5 != -1 && cm5 != -1){
				gameview.setSchiffVersenkt(cn5, cm5);
				model.temp[cn5][cm5] = 6;
				cn5 = -1;
				cm5 = -1;
			}
			
			if (cn6 != -1 && cm6 != -1){
				gameview.setSchiffVersenkt(cn6, cm6);
				model.temp[cn6][cm6] = 6;
				cn6 = -1;
				cm6 = -1;
			}
			
			if (dn1 != -1 && dm1 != -1){
				gameview.setSchiffVersenkt(dn1, dm1);
				model.temp[dn1][dm1] = 6;
				dn1 = -1;
				dm1 = -1;
			}
			
			if (dn2 != -1 && dm2 != -1){
				gameview.setSchiffVersenkt(dn2, dm2);
				model.temp[dn2][dm2] = 6;
				dn2 = -1;
				dm2 = -1;
			}
			
			if (dn3 != -1 && dm3 != -1){
				gameview.setSchiffVersenkt(dn3, dm3);
				model.temp[dn3][dm3] = 6;
				dn3 = -1;
				dm3 = -1;
			}
			
			if (dn4 != -1 && dm4 != -1){
				gameview.setSchiffVersenkt(dn4, dm4);
				model.temp[dn4][dm4] = 6;
				dn4 = -1;
				dm4 = -1;
			}
			
			if (dn5 != -1 && dm5 != -1){
				gameview.setSchiffVersenkt(dn5, dm5);
				model.temp[dn5][dm5] = 6;
				dn5 = -1;
				dm5 = -1;
			}
			
			if (dn6 != -1 && dm6 != -1){
				gameview.setSchiffVersenkt(dn6, dm6);
				model.temp[dn6][dm6] = 6;
				dn6 = -1;
				dm6 = -1;
			}
		}
		else {
			versenkt = false;
		}
		System.out.println("" + a + b + c + d);
		return versenkt;
	}
	
	public boolean gewonnen() {
		for (int n=0; n<10; n++) {
			for (int m=0; m<10; m++) {
				if (model.temp[n][m] != 1) {
						gewonnen = true;
				}
			}
		}
		return gewonnen;
	}
}
