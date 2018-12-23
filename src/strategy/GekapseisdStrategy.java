package strategy;

import model.IHulpdienstStrategy;

public class GekapseisdStrategy implements IHulpdienstStrategy {
    public String Reddingstype(){
        return "Duikers & reddingsvesten paraat. Drenkelingen uit water halen.";
    };

    public String toString(){
        return "meldingStrategy";
    }
}
