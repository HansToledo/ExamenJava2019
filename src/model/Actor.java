package model;

import com.sun.xml.internal.bind.v2.runtime.Coordinator;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:10<br/>
 * To change this template use File | Settings | File Templates.
 */
public abstract class Actor  {


    public abstract Coördinaten getLocatie();

    public abstract double getAfstand();

    public abstract double getSnelheid();

    public abstract double getReactieTijd();

    public abstract double getWendbaarheid();

    public abstract double getGrootte();

    public abstract double getCapaciteit();

    public abstract int getKoers();

    public abstract void setLocatie(Coördinaten locatie);

    public abstract void setSnelheid(double snelheid);

    public abstract void setGrootte(double grootte);

    public abstract void setCapaciteit(double capaciteit);

    public abstract void setKoers(int koers);

}
