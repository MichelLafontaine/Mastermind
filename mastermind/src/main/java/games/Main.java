package games;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
	
	public static Logger logger = LogManager.getLogger("Main.class");
	
    public static void main(String args[]) {
        logger.info("Jeu lancé");
        Games games = new Games();
        Config.loadConfig();
        games.sommaire();

    }
}
