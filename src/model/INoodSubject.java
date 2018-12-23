package model;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 12:45<br/>
 * To change this template use File | Settings | File Templates.
 */
public interface INoodSubject {

    public void addNoodObserver(INoodObserver noodObserver);
    public void removeNoodObserver(INoodObserver noodObserver);
    public void doNotify();

}
