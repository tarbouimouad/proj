package classes;
import enumeration.NatureTerrain;

public class Carte {
	private int tailleCases;
	private int nbLignes;
	private int nbColonnes;
	
	public Case[][] carte;
	
	
	
	public Carte(int tailleCases, int nbLignes, int nbColonnes) {
		
		this.tailleCases = tailleCases;
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
		carte = new Case[nbLignes][];
		for (int i = 0; i <nbLignes ; i++) {
			carte[i] = new Case[nbColonnes] ;
		}
	}

	public int getTailleCases() {
		return tailleCases;
	}
	public void setTailleCases(int tailleCases) {
		this.tailleCases = tailleCases;
	}
	public int getNbLignes() {
		return nbLignes;
	}
	public void setNbLignes(int nbLignes) {
		this.nbLignes = nbLignes;
	}
	public int getNbColonnes() {
		return nbColonnes;
	}
	public void setNbColonnes(int nbColonnes) {
		this.nbColonnes = nbColonnes;
	}
	
	public Case getCase(int ligne , int colonne) {
		return carte[ligne][colonne];
	}
	public void setCase(int ligne , int colonne , NatureTerrain nature) {
		carte[ligne][colonne].setNature(nature);
	}
	
	
	
	
}