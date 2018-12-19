package model;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:10<br/>
 * To change this template use File | Settings | File Templates.
 */
public abstract class Actor implements IHulpdienstStrategy {


    //region Strategy Code
    private IHulpdienstStrategy hulpdienstStrategy;

    public IHulpdienstStrategy getHulpdienstStrategy() {
        return hulpdienstStrategy;
    }

    public void setHulpdienstStrategy(IHulpdienstStrategy hulpdienstStrategy) {
        this.hulpdienstStrategy = hulpdienstStrategy;
    }
    //endregion

    public double getAfstand() {

        return 0.0;
    };

    public Coördinaten getLocatie() {

        return new Coördinaten();

    };

    @Override
    public String toString() {
        return "Actor{" +
                "hulpdienstStrategy=" + hulpdienstStrategy +
                '}';
    }
}
