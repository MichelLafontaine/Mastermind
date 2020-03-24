package com.lafontaine.mastermind.actuator.log4j;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class Human extends Player {
    private static Logger logger = Logger.getLogger(Main.class);

    public Human() {

    }

    public String getProposition() {
        System.out.print("Proposition : ");
        String proposition;
        
        do { 
        	Scanner sc = new Scanner(System.in);
        	proposition = sc.nextLine();
        	longueurProposition = String.valueOf(proposition).length();
            // longueurProposition est égal à la longueur (nombre de chiffres) dans proposition

            if (!proposition.matches("[0-9]+") || longueurProposition != Config.getLongueurNombreMystere())
                Erreur.erreurNombre();

        } while (longueurProposition != Config.getLongueurNombreMystere() || !proposition.matches("[0-9]+"));
        // Boucle tant que la proposition n'a pas le même nombre de/ chiffres que le paramètre longueurNombreMystere
        logger.info("Nombre proposé : "+proposition);
        
        
        return proposition;
    }


}


