package strategy;

import model.IHulpdienstStrategy;

public class ZinkendStrategy implements IHulpdienstStrategy {
    public String Reddingstype(){
        return "Lassers ter plaatse brengen en mogelijks opvarenden en drenkelingen ophalen.";
    };

    public String toString(){
        return "stormStrategy";
    }
}
