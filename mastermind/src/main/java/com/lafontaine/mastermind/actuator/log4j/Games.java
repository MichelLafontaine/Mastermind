package com.lafontaine.mastermind.actuator.log4j;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class Games {

  private static Logger logger = Logger.getLogger(Games.class);

  private Scanner sc = new Scanner(System.in);

  public void sommaire() {

      System.out.println("Bienvenue au Mastermind de Michel LAFONTAINE");
      byte choix = 0;

      do {
          System.out.println("\nChoisissez un mode de jeu :");
          System.out.println("\n1· Challenger \n2· Défenseur \n3· Duel \n4· Quitter");

          // Si l'entrée clavier n'est pas un byte
          if (!sc.hasNextByte()) {
              Erreur.erreurChoix();
              sc.next();
              continue;
          }
          choix = sc.nextByte();
          switch (choix) {

              case 1:
                  challenger();
                  break;

              case 2:
                  defenseur();
                  break;

              case 3:
                  duel();
                  break;

              case 4:
                  System.out.println("A Bientôt");
                  System.exit(0);
                  break;
              default:
                  Erreur.erreurChoix();
          }
      } while (choix != 1 && choix != 2 && choix != 3 && choix != 4);
  }

  public void challenger(){
      logger.info("Mode challenger lancé");
      System.out.println("********MODE CHALLENGER********");
      int compteur = 1;
      Player joueur1 = new Human();
      Computer joueur2 = new Computer();
      int longueurNombreMystere = Config.getLongueurNombreMystere();
      int coupsMax = Config.getCoupsMax();
      joueur2.setLongueurNombreMystere(longueurNombreMystere);
      String proposition ="";
      System.out.println("Computer a choisi son nombre mystère");
      String nombreMystere = joueur2.getNombreMystere();
      logger.info("Joueur 1 défini comme humain");
      // On boucle tant que le nombre mystère n'est pas trouvé
      Correction correction = new Correction();
      do {
          devMode(nombreMystere);
          afficherCompteur(compteur);
          proposition = joueur1.getProposition();
          correction.setComparerNombres(nombreMystere, proposition);
		  String resultat = correction.getComparerNombres();
          System.out.println("Résultat : " + resultat + "\n\n--------\n");
          compteur++;
      } while (!String.valueOf(proposition).equals(nombreMystere) && compteur < coupsMax + 1);
      finPartie("Vous avez", proposition, compteur, nombreMystere);
  }

  public void defenseur(){
      logger.info("Mode défenseur lancé");
      System.out.println("********MODE DEFENSEUR********");
      int compteur = 1;
      int coupsMax = Config.getCoupsMax();
      String proposition ="";
      Player joueur1 = new Human();
      logger.info("Joueur 1 défini comme humain");
      Player joueur2 = new Computer();
      logger.info("Joueur 2 défini comme ordinateur");
      System.out.println("Veuillez définir votre nombre mystère :");
      String nombreMystere = joueur1.getProposition();
      String nombreAComparer;
      Correction correction = new Correction();
      do {
          devMode(nombreMystere);
          afficherCompteur(compteur);
          proposition = joueur2.getProposition();
          correction.setComparerNombres(nombreMystere, proposition);
          nombreAComparer = correction.getComparerNombres();
          correction.setCorrection(nombreAComparer);
          compteur++;
      } while (!String.valueOf(proposition).equals(nombreMystere) && compteur < coupsMax + 1);
      finPartie("L'ordinateur a", proposition, compteur, nombreMystere);
  }

  public void duel() {
logger.info("Mode duel lancé");
      System.out.println("********MODE DUEL********");
      Computer joueur2 = new Computer();
      Human joueur1 = new Human();
      String nbMystereJoueur1, nbMystereJoueur2, propositionJoueur1, propositionJoueur2 ="";
      int compteur = 1;
      int longueurNombreMystere = Config.getLongueurNombreMystere();
      int resolu = 0;
      joueur2.setLongueurNombreMystere(longueurNombreMystere);
      System.out.println("Computer a choisi son nombre mystère");
      nbMystereJoueur2 = joueur2.getNombreMystere();
      // On stocke le nombre à deviner par le joueur dans une variable
      logger.info("Joueur 1 défini comme huamin");
      // L'utilisateur entre la combinaison à deviner pour l'ordinateur
      System.out.println("Veuillez définir votre nombre mystère :");
      nbMystereJoueur1 = joueur1.getProposition();
      // Cette combinaison est stockée ici
      logger.info("Joueur 2 défini comme ordinateur");
      Correction correction = new Correction();
      

      do {
          devMode(nbMystereJoueur2);
          afficherCompteur(compteur);
          System.out.println("À vous : ");
          propositionJoueur1 = joueur1.getProposition();
          String nombreAComparer;
          correction.setComparerNombres(nbMystereJoueur2, propositionJoueur1);
          String resultat = correction.getComparerNombres();
          System.out.println("Résultat : " + resultat + "\n\n--------\n");
          compteur++;
          
          if (!String.valueOf(propositionJoueur1).equals(nbMystereJoueur2)) {          
        	  devMode(nbMystereJoueur1);
        	  System.out.println("À l'ordinateur :");
        	  propositionJoueur2 = joueur2.getProposition();
        	  correction.setComparerNombres(nbMystereJoueur1, propositionJoueur2);
        	  nombreAComparer = correction.getComparerNombres();
        	  correction.setCorrection(nombreAComparer);
        	  
        	  if (String.valueOf(propositionJoueur2).equals(nbMystereJoueur1))
        		  resolu = 2;        	  
          }
          else 
        	  resolu = 1;          
      } while (resolu == 0);
      if (resolu == 1)
          finPartie("Vous avez", propositionJoueur1, compteur, nbMystereJoueur2);
      if (resolu == 2)
          finPartie("L'ordinateur a", propositionJoueur2, compteur, nbMystereJoueur1);
  }



  public void devMode(String nombreMystere) {
      if (Config.getModDev() == 1)
      {
          System.out.println("[Mode développeur] Le nombre mystère est : "+ nombreMystere);
      }
  }

  public void afficherCompteur(int compteur) {
      logger.info("Affichage du compteur de coups (coup n°"+compteur+")");
      System.out.println("Coup n°" + (compteur));
  }

  public void finPartie(String vainqueur, String proposition, int compteur, String nombreMystere) {
      logger.info("Partie terminée");
      int coupsMax = Config.getCoupsMax();
      if (compteur >= coupsMax && !proposition.equals(nombreMystere))
          System.out.println(
                  "Vous avez atteint la limite de coups (" + coupsMax + ") ! Le nombre mystère était : " + nombreMystere + ".");
      else
          System.out.println("Bravo ! " + vainqueur + " trouvé le nombre mystère en " + (compteur - 1) + " coups !");

      sommaire();
  }

}


