package strategy;

import model.IHulpdienstStrategy;

/**
 * @Autor: Hans Van De Weyer & Peter Raes
 * @Project: Examen Januari 2019
 * @Purpose: Strategy klasse
 */

public class GekapseisdStrategy implements IHulpdienstStrategy {

    public String Reddingstype(){
        return "Duikers & reddingsvesten paraat. Drenkelingen redden.";
    }

    public String toString(){
        return "gekapseisdStrategy";
    }
}
