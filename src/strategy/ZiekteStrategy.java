package strategy;

import model.IHulpdienstStrategy;

public class ZiekteStrategy implements IHulpdienstStrategy {
    public String Reddingstype(){
        return "Dokters & medicijnen brengen.";
    };

    public String toString(){
        return "ziekteStrategy";
    }
}
