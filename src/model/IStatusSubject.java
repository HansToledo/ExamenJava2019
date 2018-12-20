package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * @Autor: Hans Van De Weyer
 * @Date: 16/12/2018
 * @Project: Examen Januari 2019
 * @Purpose: Observer voor doorgeven van status schepen.
 */

public interface IStatusSubject {

    public void addVerkeerstorenObserver(IStatusObserver statusObserver);
    public void removeVerkeerstorenObserver(IStatusObserver statusObserver);
    public void notifyVerkeerstorenObservers();
}