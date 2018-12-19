package model;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:59<br/>
 * To change this template use File | Settings | File Templates.
 */
public class ContainerSchip extends Schepen {

    public ContainerSchip(){

    }

    public ContainerSchip(Coördinaten coördinaten,double snelheid, double grootte, double capaciteit, int koers) {

        super(coördinaten,snelheid,grootte,capaciteit,koers);

    }

    @Override
    public String toString() {
        return "ContainerSchip{} " + super.toString();
    }
}
