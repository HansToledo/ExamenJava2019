package strategy;

import model.IHulpdienstStrategy;

public class BrandStrategy implements IHulpdienstStrategy {
    public String Reddingstype(){
        return "Blussen brandhaarden.";
    };

    public String toString(){
        return "brandStrategy";
    }
}
