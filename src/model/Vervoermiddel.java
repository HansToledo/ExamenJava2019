package model;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 16/12/2018<br/>
 * Time: 19:17<br/>
 * To change this template use File | Settings | File Templates.
 */
public class Vervoermiddel extends Actor implements INoodObserver,IHulpdienstStrategy{

    public double getSnelheid(){

        return 0.0;
    };

    public double getReactieTijd(){

        return 0.0;
    };


    public double getWendbaarheid(){

        return 0.0;
};

    public double getGrootte(){

        return 0.0;
    };


    public double getCapaciteit(){
        return 0.0;
    };

    public int getKoers(){
        return 0;
    };

    public void setLocatie(Coördinaten locatie){};

    public void setSnelheid(double snelheid){};

    public void setGrootte(double grootte){};

    public void setCapaciteit(double capaciteit){};

    public void setKoers(int koers){};

    @Override
    public double getAfstand() {
        return 0;
    }

}
