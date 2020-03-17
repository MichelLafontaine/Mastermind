package games;

//import org.apache.log4j.Logger;

public class Erreur {

    //private static Logger logger = Logger.getLogger(Main.class);

    // Message affiché si une entrée clavier ne correspond à aucun choix proposé
    public static void erreurChoix() {
        System.out.println("\nVeuillez choisir parmi les propositions.");
        //logger.error("Le choix entré n'existe pas parmi les propositions");
    }

    public static void erreurNombre() {
        System.out.println("\nVeuillez entrer un nombre à " + Config.getLongueurNombreMystere() + " chiffres.");
        //logger.error("La longueur du nombre proposé ne correspond pas au paramètre défini dans config.properties");
    }

    public static void erreurCorrection() {
        System.out.println("Vous devez entrer " + Config.getLongueurNombreMystere()+" caractères : +, - ou =");
        System.out.println("Veuillez recommencer votre saisie :");
    }
}
