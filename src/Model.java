/**
 * Das Model enthaelt die Logik fuer das gesamte Spiel, unter anderem die Methoden
 * zum Schiffe setzen, um den Spieler zu wechseln und wie viele Schiffe von welcher
 * Groesse es ueberhaupt gibt.
 * @author Kirsten und Serdar
 */
public class Model {
	int spieler = 1;
	int feldgroesse = 10; 
	//2 Dimensionale Arrays zum speichern der Feldwerte/ temp ist der Zwischenspeicher
	int [][] spieler1Feld = new int [feldgroesse][feldgroesse];
	int [][] spieler2Feld = new int [feldgroesse][feldgroesse];
	int [][] temp = new int [10][10];
	//Groeße der jeweiligen Schiffe wird festgelegt
	final int fuenfer = 5;
	final int vierer = 4;
	final int dreier = 3;
	final int zweier = 2;
	//Anzahl der Schiffe wird festgelegt
	int anzahlfuenfer = 1;
	int anzahlvierer = 2;
	int anzahldreier = 3;
	int anzahlzweier = 4;
	//String Variable fuer die Namensein- bzw. Ausgabe
	String spieler1 = "Spieler 1";
	String spieler2 = "Spieler 2";
	//Variable, welche aussagt, ob ein Schiff passt oder nicht passt
	boolean passt;
	
	public Model() {
		
	}
	
	public int getFuenfer() {
		return fuenfer;
	}
	
	public int getVierer() {
		return vierer;
	}
	
	public int getDreier() {
		return dreier;
	}
	
	public int getZweier() {
		return zweier;
	}
	
	public void setTemp(int i, int j) {
		
	}
	
	/**
	 * Anzahl der Schiffe, die es gibt, wird zurueckgesetzt
	 * @author Kirsten und Serdar
	 */
	public void setSchiffsanzahl() {
		anzahlfuenfer = 1;
		anzahlvierer = 2;
		anzahldreier = 3;
		anzahlzweier = 4;
	}
	
	public void spielerWechsel() {
		if (spieler < 2) {
			spieler++;
		}
		else {
			spieler = 1;
		}
		
	}
	
	public int getSpieler() {
		return spieler;
	}
	
	public void setFeldgroesse(int f) {
		feldgroesse = f;
	}
	
	public int getFeldgroesse() {
		return feldgroesse;
	}
	
	public void setSpieler1() {
		
	}
	
	public int getTemp(int n, int m) {
		return temp[n][m];
	}
	
	/**
	 * Methode ueberprueft im WeiterListenerGV vor jedem Spielerwechsel, ob auf dem Spielfeld des Gegners noch Schiffteile sind, die noch nicht getroffen wurden.
	 * Es wird jedes einzelne Feld untersucht und immer, wenn ein Feld nicht den Wert 1 hat, also sich auf dem Feld kein ungetroffenes Schiff befindet, wird zaehler inkrementiert.
	 * Da wir 100 Felder haben, wird geschaut, ob zaehler den Wert 100 hat.
	 * Wenn ja, wird gewonnen auf true gesetzt. 
	 * Wenn nicht, bleibt es auf false.
	 * @return
	 */
	public boolean gewonnen() {
		boolean gewonnen = false;
		int zaehler = 0;
		for (int n=0; n<10; n++) {
			for (int m=0; m<10; m++) {
				if (temp[n][m] != 1) {
						zaehler++;
				}
			}
		}
		if (zaehler == 100) {
			gewonnen = true;
		}
		return gewonnen;
	}
	
	/**
	 * Methode, um die Schiffe im SetShipView je nach Groeße und Richtung zu setzen.
	 * @param groesse
	 * @param vertikal
	 * @param button
	 */
	public void schiffeSetzen(int groesse, boolean vertikal, int n, int m) {
		
		passtDasSchiff(groesse, vertikal, n, m);
		if (getPasst() == true) {
			for (int i = 0; i<groesse;i++) {
				if (vertikal == true) {
					if (getPasst() == true) {
						temp[n][m] = 1;
						testeSchiffUmgebung(n, m);
						n++;
					}		
				}
			else {
					temp[n][m] = 1;
					testeSchiffUmgebung(n,m);
					m++;
				}
			}
		}
		else if (getPasst() == false) {
			System.err.println("Nicht genug Platz!");
		}
	}
	
	/**
	 * Ueberprueft, ob das Schiff auf die ausgewaehlten Felder passt.
	 * Variable passt wird auf true gesetzt, wenn das Schiff gesetzt werden kann.
	 * Variable passt wird auf false gesetzt, wenn das Schiff nicht gesetzt werden kann.
	 * Es werden verschiedene boolsche Werte gesetzt, damit fuer jeden Teil des Schiffes
	 * getestet werden kann, ob das Feld belegbar ist.
	 * Eine kuerzere Variante mit mehreren Schleifen war leider nicht moeglich, da die 
	 * Werte nicht korrekt gesetzt wurden.
	 * @param groesse
	 * @param vertikal
	 * @param n
	 * @param m
	 * @return
	 * @author Kirsten und Serdar
	 */
	public boolean passtDasSchiff(int groesse, boolean vertikal, int n, int m) {

		passt = true;
		boolean eins;
		boolean zwei;
		boolean drei;
		boolean vier;
		boolean fuenf;
		
		if (vertikal == true) {
			if (groesse == 5 && groesse + n <= 10) {
				if (temp[n][m] == 0) {
					eins = true;
					n++;
				}
				else eins = false;
				
				if (temp[n][m] == 0) {
					zwei = true;
					n++;
				}
				else zwei = false;
				
				if (temp[n][m] == 0) {
					drei = true;
					n++;
				}
				else drei = false;
				
				if (temp[n][m] == 0) {
					vier = true;
					n++;
				}
				else vier = false;
				
				if (temp[n][m] == 0) {
					fuenf = true;
				}
				else fuenf = false;
				
				if (eins == true && zwei == true && drei == true && vier == true && fuenf == true) {
					passt = true;
				}
				else passt = false;
			}
			
			else if (groesse == 4 && groesse + n <= 10) {
				if (temp[n][m] == 0) {
					eins = true;
					n++;
				}
				else eins = false;
				
				if (temp[n][m] == 0) {
					zwei = true;
					n++;
				}
				else zwei = false;
				
				if (temp[n][m] == 0) {
					drei = true;
					n++;
				}
				else drei = false;
				
				if (temp[n][m] == 0) {
					vier = true;
				}
				else vier = false;
				
				if (eins == true && zwei == true && drei == true && vier == true) {
					passt = true;
				}
				else passt = false;
			}
			
			else if (groesse == 3 && groesse + n <= 10) {
				if (temp[n][m] == 0) {
					eins = true;
					n++;
				}
				else eins = false;
				
				if (temp[n][m] == 0) {
					zwei = true;
					n++;
				}
				else zwei = false;
				
				if (temp[n][m] == 0) {
					drei = true;
				}
				else drei = false;
				
				if (eins == true && zwei == true && drei == true) {
					passt = true;
				}
				else passt = false;
			}
			
			else if (groesse == 2 && groesse + n <= 10) {
				if (temp[n][m] == 0) {
					eins = true;
					n++;
				}
				else eins = false;
				
				if (temp[n][m] == 0) {
					zwei = true;
				}
				else zwei = false;
				
				if (eins == true && zwei == true) {
					passt = true;
				}
				else passt = false;
			}
			
			else {
				passt = false;
			}
		}
		
		if (vertikal == false) {
			if (groesse == 5 && groesse + m <= 10) {
				if (temp[n][m] == 0) {
					eins = true;
					m++;
				}
				else eins = false;
				
				if (temp[n][m] == 0) {
					zwei = true;
					m++;
				}
				else zwei = false;
				
				if (temp[n][m] == 0) {
					drei = true;
					m++;
				}
				else drei = false;
				
				if (temp[n][m] == 0) {
					vier = true;
					m++;
				}
				else vier = false;
				
				if (temp[n][m] == 0) {
					fuenf = true;
				}
				else fuenf = false;
				
				if (eins == true && zwei == true && drei == true && vier == true && fuenf == true) {
					passt = true;
				}
				else passt = false;
			}
			
			else if (groesse == 4 && groesse + m <= 10) {
				if (temp[n][m] == 0) {
					eins = true;
					m++;
				}
				else eins = false;
				
				if (temp[n][m] == 0) {
					zwei = true;
					m++;
				}
				else zwei = false;
				
				if (temp[n][m] == 0) {
					drei = true;
					m++;
				}
				else drei = false;
				
				if (temp[n][m] == 0) {
					vier = true;
				}
				else vier = false;
				
				if (eins == true && zwei == true && drei == true && vier == true) {
					passt = true;
				}
				else passt = false;
			}
			
			else if (groesse == 3 && groesse + m <= 10) {
				if (temp[n][m] == 0) {
					eins = true;
					m++;
				}
				else eins = false;
				
				if (temp[n][m] == 0) {
					zwei = true;
					m++;
				}
				else zwei = false;
				
				if (temp[n][m] == 0) {
					drei = true;
				}
				else drei = false;
				
				if (eins == true && zwei == true && drei == true) {
					passt = true;
				}
				else passt = false;
			}
			
			else if (groesse == 2 && groesse + m <= 10) {
				if (temp[n][m] == 0) {
					eins = true;
					m++;
				}
				else eins = false;
				
				if (temp[n][m] == 0) {
					zwei = true;
				}
				else zwei = false;
				
				if (eins == true && zwei == true) {
					passt = true;
				}
				else passt = false;
			}
			
			else {
				passt = false;
			}
		}
		
		return passt;
	}
	
	public boolean getPasst() {
		return passt;
	}
	
	/**
	 * Diese Methode setzt die Felder um das Schiff auf den Wert 4, sodass spaeter 
	 * diese Felder nicht mehr besetzt werden koennen (nur Felder mit dem Wert 0 
	 * koennen besetzt werden).
	 * @param n
	 * @param m
	 * @author Kirsten und Serdar
	 */
	public void testeSchiffUmgebung(int n, int m) {

		if (n>0) {
			n--;
			if (temp[n][m] == 0) {
				temp[n][m] = 4;
			}
			n++;
		}
		if (m<9) {	
				m++;
			if (temp[n][m] == 0) {
				temp[n][m] = 4;
			}
			m--;
		}
		if (n<9) {	
				n++;
			if (temp[n][m] == 0) {
				temp[n][m] = 4;
			}
			n--;
		}
		if (m>0) {
				m--;
			if (temp[n][m] == 0) {
				temp[n][m] = 4;
			}
			m++;
		}
	}
	
	/**
	 * Ueberprueft, ob ein Schiff versenkt wurde, indem von jedem getroffenen Schiffsteil die umliegenden Punkte untersucht werden und daraufhin ueberprueft wird,
	 * ob sich dort noch ein ungetroffenes Teil befindet.
	 * @param n
	 * @param m
	 * @return
	 */
	public boolean testeSchiffVersenkt(int n, int m) {
		boolean versenkt;
		boolean a = false;
		boolean b = false;
		boolean c = false;
		boolean d = false;
		
		if (n>0) {
			n--;
			if (temp[n][m] != 1) {
				n++;
				a = true;
			}
			else n++;
		}
		if (n<9) {
			n++;
			if (temp[n][m] != 1) {
				n--;
				b = true;
			}
			else n--;
		}
		if (m>0) {
			m--;
			if (temp[n][m] != 1) {
				m++;
				c = true;
			}
			else m++;
		}
		if (m<0) {
			m++;
			if (temp[n][m] != 1) {
				m--;
				d = true;
			}
			else m--;
		}
		if (a == true && b == true && c == true && d == true) {
			versenkt = true;
		}
		else {
			versenkt = false;
		}
		return versenkt;
	}
	
	/**
	 * Ueberschreibt die Arrays Spieler1Feld und Spieler2Feld mit den Daten des Arrays Temp
	 */
	public void setzeSpielfeldSpieler1() {
		for (int n = 0; n<feldgroesse; n++) {
			for (int m = 0; m<feldgroesse; m++) {
				spieler1Feld[n][m] = temp[n][m];
			}
		}
	}
	
	public void setzeSpielfeldSpieler2() {
		for (int n = 0; n<feldgroesse; n++) {
			for (int m = 0; m<feldgroesse; m++) {
				spieler2Feld[n][m] = temp[n][m];
			}
		}
	}
	
	/**
	 * Ueberschreibt das Array Temp mit den Daten vom Array des jeweiligen Spielers
	 */
	public void setzeTemp() {
		if (getSpieler() == 1) {
			for (int n = 0; n<feldgroesse; n++) {
				for (int m = 0; m<feldgroesse; m++) {
					temp[n][m] = spieler2Feld[n][m];
				}
			}
		}
		else if (getSpieler() == 2) {
			for (int n = 0; n<feldgroesse; n++) {
				for (int m = 0; m<feldgroesse; m++) {
					temp[n][m] = spieler1Feld[n][m];
				}
			}
		}
	}
}
