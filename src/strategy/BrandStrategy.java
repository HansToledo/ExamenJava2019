package strategy;

import model.IHulpdienstStrategy;

/**
 * @Autor: Hans Van De Weyer & Peter Raes
 * @Project: Examen Januari 2019
 * @Purpose: Strategy klasse
 */

public class BrandStrategy implements IHulpdienstStrategy {

    public String Reddingstype() { return "Blussen brandhaarden + opvarenden redden."; }

    public String toString() { return "brandStrategy"; }

}
