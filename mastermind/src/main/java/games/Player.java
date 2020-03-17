package games;

public abstract class Player {

    protected String proposition;
    protected int longueurProposition;
    protected String resultatPrecedent = "";

    public Player() {

    }

    public String getProposition(){
        return proposition;
    }

}
