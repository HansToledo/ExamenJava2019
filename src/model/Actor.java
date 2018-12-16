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


    public double getAfstand(double breedteX, double lengteX, double breedteY, double lengteY) {
        //Latitude = breedte
        //Longitude = lengte
        double afstand = 0;
        try{
            double Radius = 6371; // radius van de aarde is 6371km
            double LatX = Math.toRadians(breedteX);
            double LatY = Math.toRadians(breedteY);
            double differenceLat = Math.toRadians(breedteX-breedteY);
            double differenceLon = Math.toRadians(lengteX-lengteY);

            double a = Math.sin(differenceLat/2) * Math.sin(differenceLat/2) + Math.cos(LatY) * Math.cos(LatX) * Math.sin(differenceLon/2) * Math.sin(differenceLon/2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

            afstand = (Radius * c) / 1.852; //uitgedruk in zeemijl dus / 1.852km

        } catch(Exception e){
            e.printStackTrace();
        }
        return afstand;
    }

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
