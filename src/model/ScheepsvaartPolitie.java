package model;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 12:04<br/>
 * To change this template use File | Settings | File Templates.
 */
public class ScheepsvaartPolitie extends Actor implements IHulpdienst {

    @Override
    public Coördinaten getLocatie() {
        return null;
    }

    @Override
    public String verkeersTorenId() {
        return "testscheepsvaartPolitie";
    }
}
