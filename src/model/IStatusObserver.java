package model;

import java.util.LinkedList;

public interface IStatusObserver {
    public void statusUpdate(LinkedList<ISchip> status);
}
