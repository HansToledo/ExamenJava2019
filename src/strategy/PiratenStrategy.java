package strategy;

import model.IHulpdienstStrategy;

public class PiratenStrategy implements IHulpdienstStrategy {

    public String Reddingstype(){
        return "Houten been van de piraat doorzagen.";
    }

    public String toString(){
        return "piratenStrategy";
    }
}
