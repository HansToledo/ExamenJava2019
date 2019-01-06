package model;

/**
 * @Autor: Peter Raes & Hans Van De Weyer
 * @Project: Examen Januari 2019
 * @Purpose: Observer voor doorgeven van status schepen. Van schip naar verkeerstoren het dichtste bij. Subject is hier het schip in nood.
 */

public interface IStatusSubject {

    public void addStatusObserver(IStatusObserver statusObserver);
    public void removeStatusObserver(IStatusObserver statusObserver);
    public void doNotifyStatusObservers();

}