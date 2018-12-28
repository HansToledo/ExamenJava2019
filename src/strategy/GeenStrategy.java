package strategy;

import model.IHulpdienstStrategy;

public class GeenStrategy implements IHulpdienstStrategy {

    public String Reddingstype(){
        return "Geen";
    }

    public String toString(){
        return "geenStrategy";
    }
}
