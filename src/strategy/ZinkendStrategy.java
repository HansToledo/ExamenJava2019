package strategy;

import model.IHulpdienstStrategy;

public class ZinkendStrategy implements IHulpdienstStrategy {
    public String Reddingstype(){
        return "Lassers ter plaatse brengen en mogelijks opvarenden en drenkelingen redden.";
    };

    public String toString(){
        return "zinkendStrategy";
    }
}
