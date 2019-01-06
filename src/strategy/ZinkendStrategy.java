package strategy;

import model.IHulpdienstStrategy;

/**
 * @Autor: Hans Van De Weyer & Peter Raes
 * @Project: Examen Januari 2019
 * @Purpose: Strategy klasse
 */

public class ZinkendStrategy implements IHulpdienstStrategy {
    public String Reddingstype() {

        return "Lassers ter plaatse brengen en mogelijks opvarenden en drenkelingen redden.";
    }

    public String toString() {
        return "zinkendStrategy";
    }
}
