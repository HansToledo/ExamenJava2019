package model;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 12:04<br/>
 * To change this template use File | Settings | File Templates.
 */
public class ScheepsvaartPolitie extends Vervoermiddel {

    public ScheepsvaartPolitie(){

    }

    public ScheepsvaartPolitie (Coördinaten coördinaten,double snelheid, double grootte, double capaciteit, int koers,IHulpdienstStrategy hulpdienstStrategy) {

        super(coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);

    }
    @Override
    public void doUpdate() {

    }
    @Override
    public String toString() {
        return "ScheepsvaartPolitie{} " + super.toString();
    }


    @Override
    public void addVerkeerstorenObserver(IStatusObserver statusObserver) {

    }

    @Override
    public void removeVerkeerstorenObserver(IStatusObserver statusObserver) {

    }

    @Override
    public void notifyVerkeerstorenObservers() {

    }
}
