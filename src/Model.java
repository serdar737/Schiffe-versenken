
public class Model {
	private int spieler;
	
	public Model() {
		
	}
	
	public void spielerWechsel() {
		spieler++;
		spieler %= 2;
	}

}
