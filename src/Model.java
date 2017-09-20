
public class Model {
	int spieler = 1;
	int feldgroesse = 10; 
	int [][] spieler1Feld = new int [feldgroesse][feldgroesse];
	int [][] spieler2Feld = new int [feldgroesse][feldgroesse];
	private int [][] temp = new int [10][10];
	final int fuenfer = 5;
	final int vierer = 4;
	final int dreier = 3;
	final int zweier = 2;
	int anzahlfuenfer = 1;
	int anzahlvierer = 2;
	int anzahldreier = 3;
	int anzahlzweier = 4;
	String spieler1 = "Spieler 1";
	String spieler2 = "Spieler 2";
	
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
	
	public void setTemp(int i, int j){
		
	}
	
	public void setSchiffsanzahl(){
		anzahlfuenfer = 1;
		anzahlvierer = 2;
		anzahldreier = 3;
		anzahlzweier = 4;
	}
	public void spielerWechsel() {
		if (spieler < 3) {
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
	 * Methode, um die Schiffe im SetShipView je nach Groeße und Richtung zu setzen.
	 * @param groesse
	 * @param vertikal
	 * @param button
	 */
	public void schiffeSetzen(int groesse, boolean vertikal, int n, int m) {
		
		for (int i = 0; i<groesse;i++) {
			if (vertikal == true) {
				temp[n][m] = 1;
				n++;
			}
			else {
				temp[n][m] = 1;
				m++;
			}
		}
	}
}
