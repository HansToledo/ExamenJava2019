package model;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 16/12/2018<br/>
 * Time: 19:17<br/>
 * To change this template use File | Settings | File Templates.
 */
public abstract class Vervoermiddel extends Actor implements INoodObserver{


    public abstract double getSnelheid();

    public abstract double getReactieTijd();


    public abstract double getWendbaarheid();

    public abstract double getGrootte();


    public abstract double getCapaciteit();

    public abstract int getKoers();

    public abstract void setLocatie();

    public abstract void setSnelheid();

    public abstract void setGrootte();

    public abstract void setCapaciteit();

    public abstract void setKoers();


}
