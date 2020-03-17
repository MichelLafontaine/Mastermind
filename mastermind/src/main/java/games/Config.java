package games;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class Config {

    static private int longueurNombreMystere;
    static private int coupsMax;
    static private int modDev;

    public static void modifieConfiguration(){

        Properties prop = new Properties();
        OutputStream output = null;
        InputStream input = null;
        try {
            input = new FileInputStream("C:\\Users\\miche\\eclipse-workspace\\test\\src\\main\\java\\");
            prop.load(input);

            Scanner sc = new Scanner(System.in);
            System.out.println("Combien de chiffre comprend le nombre mystère(entre 0 et 15)?");
            String nbChiffre = sc.next();
            System.out.println("Combien de chance maximum pour deviner le nombre mystère ? (entre 0 et 20)");
            String nbCoup = sc.next();
            prop.setProperty("longueurMasterMind", nbChiffre);
            prop.setProperty("coupsMax", nbCoup);
            output = new FileOutputStream("config.properties");
            prop.store(output, null);

            longueurNombreMystere = Integer.valueOf(prop.getProperty("longueurMasterMind"));
            coupsMax = Integer.valueOf(prop.getProperty("coupsMax"));
            modDev = Integer.valueOf(prop.getProperty("modeDeveloppeur"));
        } catch (final IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void loadConfig()  {
        Properties prop = new Properties();
        FileInputStream input = null;
        OutputStream output = null;

        try {

        	input = new FileInputStream("C:\\Users\\miche\\eclipse-workspace\\test\\config.properties");
            prop.load(input);

            if (input == null) {
                output = new FileOutputStream("C:\\Users\\miche\\eclipse-workspace\\test\\config.properties");

                // Valeurs par défaut
                prop.setProperty("longueurMasterMind", "4");
                prop.setProperty("coupsMax", "15");
                prop.setProperty("modeDeveloppeur", "0");
                //logger.info("Fichier config par défaut créé");
                // Config sauvegardée à la racine du projet
                prop.store(output, null);
            }

            longueurNombreMystere = Integer.valueOf(prop.getProperty("longueurMasterMind"));
            coupsMax = Integer.valueOf(prop.getProperty("coupsMax"));
            modDev = Integer.valueOf(prop.getProperty("modeDeveloppeur"));
        } catch (final IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int getLongueurNombreMystere(){
        return longueurNombreMystere;
    }

    public static int getCoupsMax() {
        return coupsMax;
    }

    public static int getModDev () {
        return modDev;

    }
}
