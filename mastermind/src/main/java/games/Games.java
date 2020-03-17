package games;


import java.util.Scanner;

//import org.apache.log4j.Logger;

public class Games {

    //private static Logger logger = Logger.getLogger(Games.class);



    private Scanner sc = new Scanner(System.in);

    public void sommaire() {

        System.out.println("Bienvenue au Mastermind de Michel LAFONTAINE");
        byte choix = 0;

        do {
            System.out.println("\nChoisissez un mode de jeu :");
            System.out.println("\n1· Challenger \n2· Défenseur \n3· Duel \n4· Configuration \n5· Quitter");

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
                    Config.modifieConfiguration();
                    break;

                case 5:
                    System.out.println("A Bientôt");
                    System.exit(0);
                    break;
                default:
                    Erreur.erreurChoix();
            }
        } while (choix != 1 && choix != 2 && choix != 3 && choix != 4 && choix !=5);
    }

    public void challenger(){
        //logger.info("Mode challenger lancé");
        System.out.println("********MODE CHALLENGER********");
        int compteur = 1;
        Player joueur1 = new Human();
        Computer joueur2 = new Computer();
        int longueurNombreMystere = Config.getLongueurNombreMystere();
        int coupsMax = Config.getCoupsMax();
        joueur2.setLongueurNombreMystere(longueurNombreMystere);
        String proposition ="";
        String nombreMystere = joueur2.getNombreMystere();
        //logger.info("Joueur 1 défini comme humain");
        // On boucle tant que le nombre mystère n'est pas trouvé
        do {
            devMode(nombreMystere);
            afficherCompteur(compteur);
            proposition = joueur1.getProposition();
            String resultat = comparerNombres(nombreMystere, proposition);
            System.out.println("Résultat : " + resultat + "\n\n--------\n");
            compteur++;
        } while (!String.valueOf(proposition).equals(nombreMystere) && compteur < coupsMax + 1);
        finPartie("Vous avez", proposition, compteur, nombreMystere);
    }

    public void defenseur(){
        //logger.info("Mode défenseur lancé");
        System.out.println("********MODE DEFENSEUR********");
        int compteur = 1;
        int coupsMax = Config.getCoupsMax();
        String proposition ="";
        Player joueur1 = new Human();
        //logger.info("Joueur 1 défini comme humain");
        Player joueur2 = new Computer();
        //logger.info("Joueur 2 défini comme ordinateur");
        String nombreMystere = joueur1.getProposition();
        String nombreAComparer;
        do {
            devMode(nombreMystere);
            afficherCompteur(compteur);
            proposition = joueur2.getProposition();
            nombreAComparer = comparerNombres(nombreMystere, proposition);
            correction(nombreAComparer);
            compteur++;
        } while (!String.valueOf(proposition).equals(nombreMystere) && compteur < coupsMax + 1);
        finPartie("L'ordinateur a", proposition, compteur, nombreMystere);
    }

    public void duel() {
        //logger.info("Mode duel lancé");
        System.out.println("********MODE DUEL********");
        Computer joueur2 = new Computer();
        Human joueur1 = new Human();
        String combinaisonJoueur1, combinaisonJoueur2, nombreMystereDuel, proposition;
        int compteur = 1;
        int longueurNombreMystere = Config.getLongueurNombreMystere();
        joueur2.setLongueurNombreMystere(longueurNombreMystere);
        String nombreMystere = joueur2.getNombreMystere();
        combinaisonJoueur1 = nombreMystere;// On stocke le nombre à deviner par le joueur dans une variable
        //logger.info("Joueur 1 défini comme huamin");
        // L'utilisateur entre la combinaison à deviner pour l'ordinateur
        combinaisonJoueur2 = joueur1.getProposition();// Cette combinaison est stockée ici
        //logger.info("Joueur 2 défini comme ordinateur");

        do {
            nombreMystereDuel = combinaisonJoueur1;
            devMode(nombreMystere);
            afficherCompteur(compteur);
            System.out.println("À vous : ");
            proposition = joueur1.getProposition();
            String nombreAComparer;
            String resultat = comparerNombres(nombreMystere, proposition);
            System.out.println("Résultat : " + resultat + "\n\n--------\n");
            if (String.valueOf(proposition).equals(nombreMystereDuel)) {
                compteur++;
                break;
            }

            nombreMystere = combinaisonJoueur2;
            devMode(nombreMystere);
            System.out.println("À l'ordinateur :");
            joueur2.getProposition();
            proposition = joueur2.getProposition();
            nombreAComparer = comparerNombres(nombreMystere, proposition);
            correction(nombreAComparer);
            compteur++;
        } while (!String.valueOf(proposition).equals(nombreMystere));
        if (nombreMystere == combinaisonJoueur1)
            finPartie("Vous avez", proposition, compteur, nombreMystere);
        else if (nombreMystere == combinaisonJoueur2)
            finPartie("L'ordinateur a", proposition, compteur, nombreMystere);
    }



    public void devMode(String nombreMystere) {
        if (Config.getModDev() == 1)
        {
            System.out.println("[Mode développeur] Le nombre mystère est : "+ nombreMystere);
        }
    }

    public void afficherCompteur(int compteur) {
        //logger.info("Affichage du compteur de coups (coup n°"+compteur+")");
        System.out.println("Coup n°" + (compteur));
    }

    public void finPartie(String vainqueur, String proposition, int compteur, String nombreMystere) {
        //logger.info("Partie terminée");
        int coupsMax = Config.getCoupsMax();
        if (compteur >= coupsMax && !proposition.equals(nombreMystere))
            System.out.println(
                    "Vous avez atteint la limite de coups (" + coupsMax + ") ! Le nombre mystère était : " + nombreMystere + ".");
        else
            System.out.println("Bravo ! " + vainqueur + " trouvé le nombre mystère en " + (compteur - 1) + " coups !");

        sommaire();
    }

    public String comparerNombres(String nombreMystere, String proposition) {

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
        return resultat;
    }

    public void correction(String resultatPrecedent){
        System.out.print("Le résultat est-il correct : ");
        String correction;
        Scanner sc = new Scanner(System.in);
        do {
            correction = sc.next();
            if (correction.compareTo(resultatPrecedent) != 0) {
                Erreur.erreurCorrection();
            }
        } while (correction.compareTo(resultatPrecedent) != 0);
    }


}

