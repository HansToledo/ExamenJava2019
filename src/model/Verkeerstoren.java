package model;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:38<br/>
 * To change this template use File | Settings | File Templates.
 */
public class Verkeerstoren extends Actor {

    @Override
    public double getAfstand() {
        return 0;
    }


    //region StatusObserver
    private LinkedList<ISchip> status;
    public void statusUpdate(LinkedList<ISchip> status){
        this.status = status;
    }
    //endregion

}
