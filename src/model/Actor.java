package model;

import com.sun.xml.internal.bind.v2.runtime.Coordinator;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:10<br/>
 * To change this template use File | Settings | File Templates.
 */
public abstract class Actor   {
    private IHulpdienstStrategy hulpdienstStrategy;


    public abstract double getAfstand();


    public Coördinaten getLocatie(){ return new Coördinaten(); }

    public IHulpdienstStrategy getHulpdienstStrategy() {
        return hulpdienstStrategy;
    }

    public void setStrategy(IHulpdienstStrategy hulpdienstStrategy) {
        this.hulpdienstStrategy = hulpdienstStrategy;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "hulpdienstStrategy=" + hulpdienstStrategy +
                '}';
    }
}
