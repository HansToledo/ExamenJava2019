package strategy;

/**
 * @Autor: Hans Van De Weyer & Peter Raes
 * @Project: Examen Januari 2019
 * @Purpose: Strategy klasse
 */

import model.IHulpdienstStrategy;

public class GeenStrategy implements IHulpdienstStrategy {

    public String Reddingstype(){
        return "Geen";
    }

    public String toString(){
        return "geenStrategy";
    }
}
