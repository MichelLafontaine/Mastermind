package games;

import java.util.Random;

//import org.apache.log4j.Logger;

public class Computer extends Player {

    //private static Logger logger = Logger.getLogger(Computer.class);
    private String tourPrecedent = "";
    private String nombreMystere;

    public Computer() {

    }

    public String getProposition() {
        System.out.print("Proposition : ");
        String proposition = "";
        if (tourPrecedent.equals("")) {
            proposition = "";
            Random random = new Random();

            int chiffreNombreMystere[] = new int[Config.getLongueurNombreMystere()];

            for (int i = 0; i < Config.getLongueurNombreMystere(); i++) {// On génère un chiffre aléatoire jusqu'à atteindre la
                // longueur définie dans longueurNombreMystere
                chiffreNombreMystere[i] = random.nextInt(9 + 1);

                proposition += chiffreNombreMystere[i];
            }
            tourPrecedent = proposition;
            System.out.println(proposition);
        } else {
            int chiffreNombreMystere[] = new int[Config.getLongueurNombreMystere()];

            for (int i = 0; i < Config.getLongueurNombreMystere(); i++) {
                if (resultatPrecedent.charAt(i) == '+') {
                    chiffreNombreMystere[i] = Character.getNumericValue(tourPrecedent.charAt(i));
                    chiffreNombreMystere[i]++;
                } else if (resultatPrecedent.charAt(i) == '-') {
                    chiffreNombreMystere[i] = Character.getNumericValue(tourPrecedent.charAt(i));
                    chiffreNombreMystere[i]--;
                } else if (resultatPrecedent.charAt(i) == '=')
                    chiffreNombreMystere[i] = Character.getNumericValue(tourPrecedent.charAt(i));

                proposition += chiffreNombreMystere[i];
            }
            proposition = proposition.substring(Config.getLongueurNombreMystere());
            tourPrecedent = proposition;
            System.out.println(proposition);
        }
        //logger.info("Nombre proposé : "+proposition);
        return proposition;
    }

    // Génération du nombre mystère
    public void setLongueurNombreMystere(int longueurNombreMystere) {
        nombreMystere = "";
        Random random = new Random();

        int chiffreNombreMystere[] = new int[longueurNombreMystere];

        for (int i = 0; i < longueurNombreMystere; i++) {// On génère un chiffre aléatoire jusqu'à atteindre la longueur
            // définie dans longueurNombreMystere
            chiffreNombreMystere[i] = random.nextInt(9 + 1);

            nombreMystere += chiffreNombreMystere[i];
        }
        //logger.info("Nombre mystère généré : "+nombreMystere);
    }

    public String getNombreMystere(){
        return nombreMystere;
    }
}
