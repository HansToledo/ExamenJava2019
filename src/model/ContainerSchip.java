package model;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:59<br/>
 * To change this template use File | Settings | File Templates.
 */
public class ContainerSchip extends Schepen {


    @Override
    public double getAfstand() {
        return 0;
    };

    @Override
    public double getSnelheid() { // snelheid in knopen containerschip

        return 0;//(1 + Math.random() * 30);
    }

    @Override
    public double getReactieTijd() {
        return 0;
    }

    @Override
    public double getWendbaarheid() {
        return 0;
    }

    @Override
    public double getGrootte() {
        return 0;
    }

    @Override
    public double getCapaciteit() {
        return 0;
    }

    @Override
    public int getKoers() {
        return 10;
    }

    @Override
    public void setLocatie(Coördinaten locatie) {

    }

    @Override
    public void setSnelheid(double snelheid) {

    }

    @Override
    public void setGrootte(double grootte) {

    }

    @Override
    public void setCapaciteit(double capaciteit) {

    }

    @Override
    public void setKoers(int koers) {

    }
}
