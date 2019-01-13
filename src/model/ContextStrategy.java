package model;

public class ContextStrategy {
    private IHulpdienstStrategy strategy;

    public ContextStrategy(IHulpdienstStrategy strategy){
        this.strategy = strategy;
    }

    public IHulpdienstStrategy executeStrategy(){
        return this.strategy;
    }
}
