package calculations;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Autor: Hans Van De Weyer & Peter Raes
 * @Project: Examen Januari 2019
 * @Purpose: JUnit test op de klasse GPSDistance
 */
public class GPSDistanceTest {

    private double breedteA = 10.0;
    private double lengteA = 10.0;
    private double breedteB = 50.0;
    private double lengteB = 50.0;
    private double afstand;


    @Before
    public void setUp() throws Exception {

        GPSDistance berekenAfstand = new GPSDistance();
        afstand = berekenAfstand.GPSDistance(breedteA,lengteA, breedteB,lengteB);

    }

    @Test
    public void GPSDistance() {

        assertEquals(Math.floor(afstand),3112,0);

    }
}