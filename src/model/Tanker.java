package model;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:58<br/>
 * To change this template use File | Settings | File Templates.
 */
public class Tanker extends Schepen {

    public Tanker(){

    }

    public Tanker(Coördinaten coördinaten,double snelheid, double grootte, double capaciteit, int koers,IHulpdienstStrategy hulpdienstStrategy) {

        super(coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);


    }
    @Override
    public void doUpdate() {

    }
    @Override
    public String toString() {
        return "Tanker{} " + super.toString();
    }


}
