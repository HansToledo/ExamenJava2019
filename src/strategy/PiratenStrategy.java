package strategy;

import model.IHulpdienstStrategy;

/**
 * @Autor: Hans Van De Weyer & Peter Raes
 * @Project: Examen Januari 2019
 * @Purpose: Strategy klasse
 */

public class PiratenStrategy implements IHulpdienstStrategy {

    public String Reddingstype(){
        return "Houten been van de piraat doorzagen.";
    }

    public String toString(){
        return "piratenStrategy";
    }
}
