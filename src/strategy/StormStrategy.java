package strategy;

import model.IHulpdienstStrategy;

public class StormStrategy implements IHulpdienstStrategy {
    public String Reddingstype(){
        return "Opvarenden ophalen.";
    }

    public String toString(){
        return "stormStrategy";
    }
}
