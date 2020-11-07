package classes;

public class Drone extends Robot {
	private int reservoirEau;
	
	public Drone(Case position, int vitesse, int eau) {
		super(position, vitesse);
		reservoirEau = eau;
	}
	public int getReservoirEau() {
		return reservoirEau;
	}
	

}
