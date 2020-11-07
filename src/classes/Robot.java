package classes;


public  class Robot {
	private Case position;
	private int vitesse;
	
	
	
	public Robot(Case position, int vitesse) {
		
		this.position = position;
		this.vitesse = vitesse;
		
	}
	
	public Case getPosition() {
		return position;
	}
	public double getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	

	public void setPosition(Case position) {
		this.position = position;
	}
	

}