package model;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:38<br/>
 * To change this template use File | Settings | File Templates.
 */
public class Verkeerstoren extends Actor implements IHulpdienst, INoodSubject,IStatusObserver{


    @Override
    public double getAfstand(){ return 0;};

    @Override
    public double getSnelheid() {
        return 0;
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
        return 0;
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





    //region StatusObserver
    private LinkedList<ISchip> status;
    public void statusUpdate(LinkedList<ISchip> status){
        this.status = status;
    }
    //endregion

}
