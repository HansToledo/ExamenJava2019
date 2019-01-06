package strategy;

import model.IHulpdienstStrategy;

/**
 * @Autor: Hans Van De Weyer & Peter Raes
 * @Project: Examen Januari 2019
 * @Purpose: Strategy klasse
 */

public class ZiekteStrategy implements IHulpdienstStrategy {

    public String Reddingstype(){
        return "Dokters & medicijnen brengen.";
    }

    public String toString(){
        return "ziekteStrategy";
    }
}
