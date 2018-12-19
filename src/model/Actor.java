package model;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:10<br/>
 * To change this template use File | Settings | File Templates.
 */
public abstract class Actor  implements  IHulpdienstStrategy {

    public abstract double getAfstand();


    public  Coördinaten getLocatie(){

        return new Coördinaten();

    };

    //region Strategy Code
    private IHulpdienstStrategy hulpdienstStrategy;

    public IHulpdienstStrategy getHulpdienstStrategy() {
        return hulpdienstStrategy;
    }

    public void setHulpdienstStrategy(IHulpdienstStrategy hulpdienstStrategy) {
        this.hulpdienstStrategy = hulpdienstStrategy;
    }
    //endregion

    @Override
    public String toString() {
        return "Actor{" +
                "hulpdienstStrategy=" + hulpdienstStrategy +
                '}';
    }
}
