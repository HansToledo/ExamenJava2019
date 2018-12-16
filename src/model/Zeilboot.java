package model;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:58<br/>
 * To change this template use File | Settings | File Templates.
 */
public class Zeilboot extends Actor implements ISchip,IStatusSubject {


    @Override
    public Coördinaten getLocatie() {
        return null;
    }


    //region StatusObserver
    public void notifyVerkeerstorenObservers(){
        //Naar alle observers een statusupdate versturen.
    }
    public void addVerkeerstorenObserver(Verkeerstoren verkeerstorenObserver){
        //lijst met observers wordt aangepast door toevoegen van observer.
    }
    public void removeVerkeerstorenObserver(Verkeerstoren verkeerstorenObserver){
        //lijst met observers wordt aangepast door verwijderen van observer.
    }
    //endregion


}
