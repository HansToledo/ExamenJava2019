package model;

/**
 * @Autor: Peter Raes & Hans Van De Weyer
 * @Project: Examen Januari 2019
 * @Purpose: Observer voor doorgeven van status schepen. Van schip naar verkeerstoren het dichtste bij. Observer is hier verkeerstoren.
 */

import calculations.Coördinaten;
import enums.StatusVervoermiddel;

public interface IStatusObserver {

    public void doUpdate(StatusVervoermiddel statusSchip,Schepen schipInNood);

}
