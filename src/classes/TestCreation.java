package classes;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import enumeration.*;

public class TestCreation {
	public static void main(String[] args) throws FileNotFoundException, DataFormatException {
		DonneesSimulation d = LecteurDonnees.lire("cartes/carteSujet.map");
		int nblig = d.carte.getNbLignes();
		int nbcol = d.carte.getNbColonnes();
		
		System.out.println("Les cases");
		for (int i = 0; i < nblig; i++) {
			for (int j = 0; j < nbcol; j++) {
				Case a = d.carte.carte[i][j];
				System.out.println(" "+a.getLigne()+
						" "+a.getColonne()+
						" "+a.getNature());
			}
			
		}
		System.out.println("Les robots");
		for (Robot r : d.robots) {
			System.out.println(""+r.getPosition().getLigne()
					+ " "+ r.getPosition().getColonne()+
					 " "+ r.getPosition().getNature()
					
					);
		}
		System.out.println("Les incendies");
		for (Incendie f : d.incendies) {
			System.out.println(""+f.getPosition().getLigne()+
					" "+f.getPosition().getColonne()+
					" "+f.getNbLitresEau()
					);
		}
//		
		
		
		

	}
}






	

