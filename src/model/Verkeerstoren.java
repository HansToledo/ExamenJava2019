package model;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:38<br/>
 * To change this template use File | Settings | File Templates.
 */
public class Verkeerstoren extends Actor implements INoodSubject, IStatusObserver{

    @Override
    public double getAfstand() {
        return 0;
    }



    //region StatusObserver
    private LinkedList<Actor> statusSchip;

    public void statusUpdate(LinkedList<Actor> statusSchip){
        this.statusSchip = statusSchip;
    }
    //endregion

}
