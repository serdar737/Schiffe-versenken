
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
		else if (passt == false){
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

		passt = true;
		boolean eins;
		boolean zwei;
		boolean drei;
		boolean vier;
		boolean fuenf;
		
		if (vertikal == true){
			if (groesse == 5){
				if (temp[n][m] == 0){
					eins = true;
					n++;
				}
				else eins = false;
				
				if (temp[n][m] == 0){
					zwei = true;
					n++;
				}
				else zwei = false;
				
				if (temp[n][m] == 0){
					drei = true;
					n++;
				}
				else drei = false;
				
				if (temp[n][m] == 0){
					vier = true;
					n++;
				}
				else vier = false;
				
				if (temp[n][m] == 0){
					fuenf = true;
					n++;
				}
				else fuenf = false;
				
				if (eins == true && zwei == true && drei == true && vier == true && fuenf == true){
					passt = true;
				}
				else passt = false;
			}
			
			else if (groesse == 4){
				if (temp[n][m] == 0){
					eins = true;
					n++;
				}
				else eins = false;
				
				if (temp[n][m] == 0){
					zwei = true;
					n++;
				}
				else zwei = false;
				
				if (temp[n][m] == 0){
					drei = true;
					n++;
				}
				else drei = false;
				
				if (temp[n][m] == 0){
					vier = true;
					n++;
				}
				else vier = false;
				
				if (eins == true && zwei == true && drei == true && vier == true){
					passt = true;
				}
				else passt = false;
			}
			
			else if (groesse == 3){
				if (temp[n][m] == 0){
					eins = true;
					n++;
				}
				else eins = false;
				
				if (temp[n][m] == 0){
					zwei = true;
					n++;
				}
				else zwei = false;
				
				if (temp[n][m] == 0){
					drei = true;
					n++;
				}
				else drei = false;
				
				if (eins == true && zwei == true && drei == true){
					passt = true;
				}
				else passt = false;
			}
			
			else if (groesse == 2){
				if (temp[n][m] == 0){
					eins = true;
					n++;
				}
				else eins = false;
				
				if (temp[n][m] == 0){
					zwei = true;
					n++;
				}
				else zwei = false;
				
				if (eins == true && zwei == true){
					passt = true;
				}
				else passt = false;
			}
		}
		
		if (vertikal == false){
			if (groesse == 5){
				if (temp[n][m] == 0){
					eins = true;
					m++;
				}
				else eins = false;
				
				if (temp[n][m] == 0){
					zwei = true;
					m++;
				}
				else zwei = false;
				
				if (temp[n][m] == 0){
					drei = true;
					m++;
				}
				else drei = false;
				
				if (temp[n][m] == 0){
					vier = true;
					m++;
				}
				else vier = false;
				
				if (temp[n][m] == 0){
					fuenf = true;
					m++;
				}
				else fuenf = false;
				
				if (eins == true && zwei == true && drei == true && vier == true && fuenf == true){
					passt = true;
				}
				else passt = false;
			}
			
			else if (groesse == 4){
				if (temp[n][m] == 0){
					eins = true;
					m++;
				}
				else eins = false;
				
				if (temp[n][m] == 0){
					zwei = true;
					m++;
				}
				else zwei = false;
				
				if (temp[n][m] == 0){
					drei = true;
					m++;
				}
				else drei = false;
				
				if (temp[n][m] == 0){
					vier = true;
					m++;
				}
				else vier = false;
				
				if (eins == true && zwei == true && drei == true && vier == true){
					passt = true;
				}
				else passt = false;
			}
			
			else if (groesse == 3){
				if (temp[n][m] == 0){
					eins = true;
					m++;
				}
				else eins = false;
				
				if (temp[n][m] == 0){
					zwei = true;
					m++;
				}
				else zwei = false;
				
				if (temp[n][m] == 0){
					drei = true;
					m++;
				}
				else drei = false;
				
				if (eins == true && zwei == true && drei == true){
					passt = true;
				}
				else passt = false;
			}
			
			else if (groesse == 2){
				if (temp[n][m] == 0){
					eins = true;
					m++;
				}
				else eins = false;
				
				if (temp[n][m] == 0){
					zwei = true;
					m++;
				}
				else zwei = false;
				
				if (eins == true && zwei == true){
					passt = true;
				}
				else passt = false;
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
