package model;

/**
 * @Autor: Hans Van De Weyer
 * @Date: 16/12/2018
 * @Project: Examen Januari 2019
 * @Purpose: Observer voor doorgeven van status schepen.
 */

public interface IStatusSubject {
    public void addVerkeerstorenObserver(Actor actor);
    public void removeVerkeerstorenObserver(Actor actor);
    public void notifyVerkeerstorenObservers(String status);
}