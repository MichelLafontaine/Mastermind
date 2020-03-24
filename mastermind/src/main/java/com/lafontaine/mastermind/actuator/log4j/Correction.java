package com.lafontaine.mastermind.actuator.log4j;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class Correction {
	private static Logger logger = Logger.getLogger(Correction.class);
	private static String correction;
	private String comparerNombres;
	
	public void setComparerNombres(String nombreMystere, String proposition) {

	      String sProposition = String.valueOf(proposition);// On met la valeur de proposition dans un String
	      String resultat = "";
	      for (int i = 0; i < Config.getLongueurNombreMystere(); i++) {
	          int chiffrePropose = Character.getNumericValue(sProposition.charAt(i));
	          int chiffreNombreMystere = Character.getNumericValue(nombreMystere.charAt(i));

	          if (chiffrePropose > chiffreNombreMystere)
	              resultat += "-";
	          else if (chiffrePropose < chiffreNombreMystere)
	              resultat += "+";
	          else if (chiffrePropose == chiffreNombreMystere)
	              resultat += "=";
	      }
	      comparerNombres = resultat;
	  }
	
	public String getComparerNombres() {
		return comparerNombres;
	}

	  public void setCorrection(String resultatPrecedent){
	      System.out.print("Le résultat est-il correct : ");
	      Scanner sc = new Scanner(System.in);
	      do {
	          correction = sc.next();
	          if (correction.compareTo(resultatPrecedent) != 0) {
	              Erreur.erreurCorrection();
	              logger.error("Le joueur n'a pas bien défini la correction ");
	          }
	      } while (correction.compareTo(resultatPrecedent) != 0);;
	      logger.info("Le joueur a défini sa correction : "+ correction);
	  }
	  
	  public static String getCorrection() {
		  return correction;
	  }
}
