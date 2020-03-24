package com.lafontaine.mastermind.actuator.log4j;

import org.apache.log4j.Logger;

public class Main {
private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String args[]) {
        logger.info("Jeu lancé");
        Games games = new Games();
        Config.loadConfig();
        games.sommaire();

    }
}
