package com.lafontaine.mastermind.actuator.log4j;

import java.io.*;
import java.util.Properties;
import org.apache.log4j.Logger;

public class Config {
	private static Logger logger = Logger.getLogger(Computer.class);
    static private int longueurNombreMystere;
    static private int coupsMax;
    static private int modDev;

    public static void loadConfig()  {
        Properties prop = new Properties();
        FileInputStream input = null;

        try {

        	input = new FileInputStream("config.properties");
            prop.load(input);

            longueurNombreMystere = Integer.valueOf(prop.getProperty("longueurMasterMind"));
            coupsMax = Integer.valueOf(prop.getProperty("coupsMax"));
            modDev = Integer.valueOf(prop.getProperty("modeDeveloppeur"));
            logger.info("Propriètés chargées ");
        } catch (final IOException ex) {        
            longueurNombreMystere = 4;
            coupsMax = 8;
            modDev = 0;
            logger.error(ex);
            System.out.println("Propriètés non chargés, valeurs par défauts");
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