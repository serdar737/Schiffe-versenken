
public class Model {
	int spieler;
	int feldgroesse = 10; 
	int [][] spieler1Feld = new int [feldgroesse][feldgroesse];
	int [][] spieler2Feld = new int [feldgroesse][feldgroesse];
	int [][] temp = new int [10][10];
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
		
		passtDasSchiff(groesse, vertikal, n, m);
		System.out.println(passt+" in schiffeSetzten/nach passtDasSchiff Abfrage");
		for (int i = 0; i<groesse;i++) {
			System.out.println(passt+" Variable i:"+ i+" in schiffeSetzten/forSchleife");
			if (vertikal == true) {
				System.out.println(passt+" in schiffeSetzten/erste if Schleife");
				if (passt == true) {
					System.out.println(passt+" in schiffeSetzten/zweite if Schleife");
					temp[n][m] = 1;
					testeSchiffUmgebung(n, m);
					System.out.println("Nach testeSchiffUmgebung");
					n++;
				}		
			}
			else {
//				passtDasSchiff(groesse, false, n, m);
				if (passt == true) {
					temp[n][m] = 1;
					testeSchiffUmgebung(n,m);
					m++;
				}	
			}
		}
		
		if (passt == false){
			if (groesse == 5) {
				anzahlfuenfer++;
			}
			else if (groesse == 4) {
				anzahlvierer++;
			}
			else if (groesse == 3) {
				anzahldreier++;
			}
			else if (groesse == 2) {
				anzahlzweier++;
			}
			System.err.println("Nicht genug Platz!");
		}
	}
	
	public boolean passtDasSchiff(int groesse, boolean vertikal, int n, int m) {
<<<<<<< HEAD
		passt = true;
=======
		System.out.println(passt+" in passtDasSchiff/vor der for Schleife");
>>>>>>> origin/update
		for (int i=0; i<groesse; i++) {
			if (passt == true) {
				if (vertikal == true) {
					if (groesse + n < 10 && temp[n][m] == 0) {
						passt = true;
						System.out.println(passt+" in passtDasSchiff/dritte if Schleife");
					}
					else if (temp[n][m] == 1 || temp[n][m] == 4){  
						passt = false;
					}
				}
				else if (vertikal == false) {
					if (groesse + m < 10 && temp[n][m] == 0) {
						passt = true;
					}
					else if (temp[n][m] == 1 || temp[n][m] == 4){  
						passt = false;
					}
				}
			}
		}	
		return passt;
	}
	
	public boolean getPasst(){
		return passt;
	}
	
	public void testeSchiffUmgebung(int n, int m){

		if (n>0){
			n--;
			if (temp[n][m] == 0){
				temp[n][m] = 4;
			}
			n++;
		}
		if (m<9){		
				m++;
			if (temp[n][m] == 0){
				temp[n][m] = 4;
			}
			m--;
		}
		if (n<9){	
				n++;
			if (temp[n][m] == 0){
				temp[n][m] = 4;
			}
			n--;
		}
		if (m>0){
				m--;
			if (temp[n][m] == 0){
				temp[n][m] = 4;
			}
			m++;
		}
	}
}
