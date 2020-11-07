package classes;

import enumeration.NatureTerrain;

public class Case {
	private int ligne;
	private int colonne;
	private NatureTerrain nature;
	
	
	public Case(int ligne, int colonne, NatureTerrain nature) {
		
		this.ligne = ligne;
		this.colonne = colonne;
		this.nature = nature;
	}

	public int getLigne() {
		return ligne;
	}
	
	public NatureTerrain getNature() {
		return nature;
	}
	
	
	public int getColonne() {
		return colonne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}

	public void setNature(NatureTerrain nature) {
		this.nature = nature;
	}
	
	
}
