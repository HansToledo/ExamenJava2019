package model;

/**
 * @Autor: Hans Van De Weyer
 * @Date: 16/12/2018
 * @Project: Examen Januari 2019
 * @Purpose: Observer voor doorgeven van status schepen. Van schip naar verkeerstoren het dichtste bij. Observer is hier verkeerstoren.
 */

import calculations.Coördinaten;
import enums.StatusVervoermiddel;

public interface IStatusObserver {

    public int doUpdate(StatusVervoermiddel statusSchip,Schepen schipInNood);

}
