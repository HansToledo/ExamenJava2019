package strategy;

import model.IHulpdienstStrategy;

public class GekapseisdStrategy implements IHulpdienstStrategy {
    public String Reddingstype(){
        return "Duikers & reddingsvesten paraat. Drenkelingen redden.";
    };

    public String toString(){
        return "meldingStrategy";
    }
}
