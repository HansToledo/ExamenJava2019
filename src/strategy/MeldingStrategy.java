package strategy;

import model.IHulpdienstStrategy;

public class MeldingStrategy implements IHulpdienstStrategy {
    public String Reddingstype(){
        return "Noodsituatie gemeld.";
    };

    public String toString(){
        return "meldingStrategy";
    }
}
