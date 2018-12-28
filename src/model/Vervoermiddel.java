package model;

import calculations.Coördinaten;
import enums.StatusVervoermiddel;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 16/12/2018<br/>
 * Time: 19:17<br/>
 * Abstracte klasse voor vervoermiddelen
 */
public abstract class Vervoermiddel extends Actor implements INoodObserver {

    private String enumNaam;
    private String naam;
    private double afstand;
    private double snelheid;
    private double reactieTijd;
    private double wendbaarheid;
    private double grootte;
    private double capaciteit;
    private int koers;
    private Coördinaten coördinaten;
    private String status;

    public Vervoermiddel() {

    }

    public Vervoermiddel(String enumNaam, String naam, Coördinaten coördinaten, double snelheid, double grootte, double capaciteit, int koers, IHulpdienstStrategy hulpdienstStrategy, String status) {
        this.enumNaam = enumNaam;
        this.naam = naam;
        this.coördinaten = coördinaten;
        this.snelheid = snelheid;
        this.grootte = grootte;
        //setGrootte(grootte);
        this.capaciteit = capaciteit;
        this.koers = koers;
        //setKoers(koers);
        this.status = status;
        super.setHulpdienstStrategy(hulpdienstStrategy);
    }

    //TODO algemeen limieten instellen op setters ook controle op double

    //region Getters & Setters


    public void setStatus(String status) {

        if (!status.isEmpty()) {

            this.status = status;
        } else {

            throw new IllegalArgumentException("Opslaan mislukt, geen status ingegeven");
        }

    }

    public void setEnumNaam(String enumNaam) {

        if (!enumNaam.isEmpty()) {

            this.enumNaam = enumNaam;
        } else {

            throw new IllegalArgumentException("Opslaan mislukt, geen EnumNaam ingegeven");
        }

    }

    public void setNaam(String naam) {

        if (!naam.isEmpty()) {

            this.naam = naam;
        } else {

            throw new IllegalArgumentException("Opslaan mislukt, geen naam ingegeven");
        }

    }

    public void setSnelheid(double snelheid) {

        if (snelheid != 0) {

            this.snelheid = snelheid;
        } else {

            throw new IllegalArgumentException("Opslaan mislukt, geen geldige snelheid ingegeven");
        }

    }

    public void setGrootte(double grootte) {

        if (grootte != 0.0) {

            this.grootte = grootte;
        } else {

            throw new IllegalArgumentException("Opslaan mislukt, geen geldige grootte ingegeven");
        }

    }

    public void setCapaciteit(double capaciteit) {

        if (capaciteit != 0.0) {

            this.capaciteit = capaciteit;
        } else {

            throw new IllegalArgumentException("Opslaan mislukt, geen geldige capaciteit ingegeven");
        }

    }

    public void setKoers(int koers) {

        if (koers < 360) {

            this.koers = koers;
        } else {

            throw new IllegalArgumentException("Opslaan mislukt, geen geldige koers ingegeven");
        }

    }

    public void setReactieTijd(double reactieTijd) {

        if (reactieTijd != 0.0) {

            this.reactieTijd = reactieTijd;
        } else {

            throw new IllegalArgumentException("Opslaan mislukt, geen geldige reactietijd ingegeven");
        }

    }

    public String getStatus() {

        return status;

    }

    public String getEnumNaam() {

        return this.enumNaam;

    }

    public Coördinaten getCoördinaten() {
        return coördinaten;
    }

    public String getNaam() {

        return this.naam;
    }

    public double getAfstand() {

        return super.getAfstand();
    }

    public double getSnelheid() {

        return this.snelheid;
    }

    public double getReactieTijd() {

        return this.reactieTijd;
    }

    public double getWendbaarheid() {

        return this.wendbaarheid;
    }

    public double getGrootte() {

        return this.grootte;

    }

    public double getCapaciteit() {

        return this.capaciteit;
    }

    public int getKoers() {

        return this.koers;
    }


    //endregion


    @Override
    public void ontvangNoodsignaal(IHulpdienstStrategy reddingsType, Coördinaten coördinaten, String naam) {

        System.out.println("\nReddingsactie is geslaagd\n");
    }

    @Override
    public String toString() {
        return naam;
    }
}

