package classes;



public class Incendie {
	private Case position;
	private int nbLitresEau;
	
	public Incendie(Case position, int nbLitresEau) {
		
		this.position = position;
		this.nbLitresEau = nbLitresEau;
	}
    
	public int getNbLitresEau() {
		return nbLitresEau;
	}
	public Case getPosition() {
		return position;
	}

	public void setNbLitresEau(int nbLitresEau) {
		this.nbLitresEau = nbLitresEau;
	}
	
	
	
}
