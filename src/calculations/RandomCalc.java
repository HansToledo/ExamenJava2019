package calculations;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 20/12/2018<br/>
 * Time: 20:03<br/>
 * To change this template use File | Settings | File Templates.
 */
public class RandomCalc {


    public static double getRandomDoubleBetweenRange(double min, double max){ // gebruikt worden voor ramdom met range
        double x = (Math.random()*((max-min)+1))+min;
        return x;
    }
}
