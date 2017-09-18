
public class Model {
	int spieler = 1;
	int feldgroesse; 
	int [][] spieler1Feld = new int [feldgroesse][feldgroesse];
	int [][] spieler2Feld = new int [feldgroesse][feldgroesse];
	final int fuenfer = 5;
	final int vierer = 4;
	final int dreier = 3;
	final int zweier = 2;
	
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
	
	public void schiffeSetzen(int groesse, boolean vertikal) {
		for (int i = 0; i<groesse;i++) {
			if (vertikal == true) {
				button[n][m]=1;
				n++;
			}
			else {
				button[n][m]=1;
				m++;
			}
		}
	}
}
