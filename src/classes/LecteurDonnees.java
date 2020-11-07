package classes;


import enumeration.NatureTerrain;


import java.io.*;

import java.util.*;
import java.util.zip.DataFormatException;



/**
 * Lecteur de cartes au format spectifié dans le sujet.
 * Les données sur les cases, robots puis incendies sont lues dans le fichier,
 * puis simplement affichées.
 * A noter: pas de vérification sémantique sur les valeurs numériques lues.
 *
 * IMPORTANT:
 *
 * Cette classe ne fait que LIRE les infos et les afficher.
 * A vous de modifier ou d'ajouter des méthodes, inspirées de celles présentes
 * (ou non), qui CREENT les objets au moment adéquat pour construire une
 * instance de la classe DonneesSimulation à partir d'un fichier.
 *
 * Vous pouvez par exemple ajouter une méthode qui crée et retourne un objet
 * contenant toutes les données lues:
 *    public static DonneesSimulation creeDonnees(String fichierDonnees);
 * Et faire des méthode creeCase(), creeRobot(), ... qui lisent les données,
 * créent les objets adéquats et les ajoutent ds l'instance de
 * DonneesSimulation.
 */
public class LecteurDonnees {


    /**
     * Lit et affiche le contenu d'un fichier de donnees (cases,
     * robots et incendies).
     * Ceci est méthode de classe; utilisation:
     * LecteurDonnees.lire(fichierDonnees)
     * @param fichierDonnees nom du fichier à lire
     */
    public static  DonneesSimulation lire(String fichierDonnees )
        throws FileNotFoundException, DataFormatException {
    	
        LecteurDonnees lecteur = new LecteurDonnees(fichierDonnees);
        Carte map = lecteur.lireCarte();
        DonneesSimulation info = new DonneesSimulation(map,
								        lecteur.lireIncendies(map ),
								        lecteur.lireRobots(map)
								        );
        scanner.close();
        return info;
        
    }




    // Tout le reste de la classe est prive!

    private static Scanner scanner;

    /**
     * Constructeur prive; impossible d'instancier la classe depuis l'exterieur
     * @param fichierDonnees nom du fichier a lire
     */
    private LecteurDonnees(String fichierDonnees)
        throws FileNotFoundException {
        scanner = new Scanner(new File(fichierDonnees));
        scanner.useLocale(Locale.US);
    }

    /**
     * Lit et affiche les donnees de la carte.
     * @throws ExceptionFormatDonnees
     */
    private Carte lireCarte() throws DataFormatException {
    	
    	Carte map;
        ignorerCommentaires();
        try {
        	
            int nbLignes = scanner.nextInt();
            int nbColonnes = scanner.nextInt();
            int tailleCases = scanner.nextInt();	
            
            map = new Carte(tailleCases , nbLignes ,nbColonnes );
           
            for (int lig = 0; lig < nbLignes; lig++) {
                for (int col = 0; col < nbColonnes; col++) {
                	
                	
                	map.carte[lig][col] = lireCase(lig, col);
                    
                }
            }
            
        } catch (NoSuchElementException e) {
        	
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbLignes nbColonnes tailleCases");
        }
        
        return map;
        // une ExceptionFormat levee depuis lireCase est remontee telle quelle
    }




    /**
     * Lit et affiche les donnees d'une case.
     */
    
   
    private NatureTerrain toEnum(String nom) {
    	if (nom.equals("EAU") == true ) return NatureTerrain.EAU;
    	if (nom.equals("FORET") == true) return NatureTerrain.FORET;
    	if (nom.equals("ROCHE") == true) return NatureTerrain.ROCHE;
    	if (nom.equals("TERRAIN_LIBRE") == true) return NatureTerrain.TERRAIN_LIBRE;
    	return NatureTerrain.HABITAT;
    }
    private Case lireCase(int lig, int col) throws DataFormatException {
    	Case toto;
        ignorerCommentaires();
        
        String chaineNature = new String();
        
        //		NatureTerrain nature;

        try {
            chaineNature = scanner.next();
           
            
            toto = new Case(lig , col ,toEnum(chaineNature));
            
            verifieLigneTerminee();

           

        } catch (NoSuchElementException e) {
        	
            throw new DataFormatException("format de case invalide. "
                    + "Attendu: nature altitude [valeur_specifique]");
        }

        return toto;
    }


    /**
     * Lit et affiche les donnees des incendies.
     */
    private Incendie[] lireIncendies(Carte map) throws DataFormatException {
        ignorerCommentaires();
        Incendie[] incendies;
        try {
            int nbIncendies = scanner.nextInt();
            incendies = new Incendie[nbIncendies];
            
            
            for (int i = 0; i < nbIncendies; i++) {
            	
            	incendies[i] = lireIncendie(i,map);
            }

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbIncendies");
        }
        return incendies;
    }


    /**
     * Lit et affiche les donnees du i-eme incendie.
     * @param i
     */
    private Incendie lireIncendie(int i , Carte map) throws DataFormatException {
    	
    	ignorerCommentaires();
        Incendie fire;

        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            int intensite = scanner.nextInt();
            fire = new Incendie( map.getCase(lig, col) , intensite );
            if (intensite <= 0) {
                throw new DataFormatException("incendie " + i
                        + "nb litres pour eteindre doit etre > 0");
            }
            verifieLigneTerminee();

           

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format d'incendie invalide. "
                    + "Attendu: ligne colonne intensite");
        }
        return fire;
    }


    /**
     * Lit et affiche les donnees des robots.
     */
    private Robot[] lireRobots(Carte map ) throws DataFormatException {
        ignorerCommentaires();
        Robot[] robots;
        try {
            int nbRobots = scanner.nextInt();
            robots = new Robot[nbRobots];
            for (int i = 0; i < nbRobots; i++) {
            	robots[i]=lireRobot(i,map);
                
            }

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbRobots");
        }
        return robots;
    }


    /**
     * Lit et affiche les donnees du i-eme robot.
     * @param i
     */
    private Robot lireRobot(int i , Carte map) throws DataFormatException {
        ignorerCommentaires();
        Robot robot;

        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
       
            String type = scanner.next();

            
            String s = scanner.findInLine("(\\d+)");	
            
            int vitesse = 0;
            if (s != null) {
            	 vitesse = Integer.parseInt(s);
            }
            
            robot = new Robot(map.getCase(lig, col) , vitesse);
            verifieLigneTerminee();

            

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format de robot invalide. "
                    + "Attendu: ligne colonne type [valeur_specifique]");
        
    }
        return robot;
    }  




    /** Ignore toute (fin de) ligne commencant par '#' */
    private void ignorerCommentaires() {
        while(scanner.hasNext("#.*")) {
            scanner.nextLine();
        }
    }

    /**
     * Verifie qu'il n'y a plus rien a lire sur cette ligne (int ou float).
     * @throws ExceptionFormatDonnees
     */
    private void verifieLigneTerminee() throws DataFormatException {
        if (scanner.findInLine("(\\d+)") != null) {
        	
            throw new DataFormatException("format invalide, donnees en trop.");
        }
    }
}
