
public class Model {
	private int spieler = 1;
	
	public Model() {
		
	}
	
	public void spielerWechsel() {
		if (spieler < 3) {
			spieler++;
		}
		else {
			spieler = 1;
		}
		
	}

}
