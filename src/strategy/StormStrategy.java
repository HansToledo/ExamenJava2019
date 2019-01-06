package strategy;

import model.IHulpdienstStrategy;

/**
 * @Autor: Hans Van De Weyer & Peter Raes
 * @Project: Examen Januari 2019
 * @Purpose: Strategy klasse
 */

public class StormStrategy implements IHulpdienstStrategy {
    public String Reddingstype(){
        return "Opvarenden ophalen.";
    }

    public String toString(){
        return "stormStrategy";
    }
}
