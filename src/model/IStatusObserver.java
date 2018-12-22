package model;

/**
 * @Autor: Hans Van De Weyer
 * @Date: 16/12/2018
 * @Project: Examen Januari 2019
 * @Purpose: Observer voor doorgeven van status schepen.
 */

import enums.StatusVervoermiddel;

public interface IStatusObserver {

    public void doUpdate(StatusVervoermiddel statusSchip, Coördinaten coördinaten, String naam);

}
