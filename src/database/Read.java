package database;

/**
 * @Autor: Hans Van De Weyer & Peter Raes
 * @Project: Examen Januari 2019
 * @Purpose: Bevat alle read statements met betrekking tot het uitlezen van de actoren uit de database.
 */

import calculations.Coördinaten;
import enums.Actors;
import enums.Hulpdiensten;
import enums.Schepen;
import enums.StatusVervoermiddel;
import model.*;
import strategy.GeenStrategy;
import java.util.ArrayList;
import java.util.List;
import static enums.Hulpdiensten.SCHEEPSVAARTPOLITIE;
import static enums.Hulpdiensten.SEAKING;

public class Read {
    private static final DBqueries kustwachtQueries = new DBqueries();
    static IHulpdienstStrategy geenStrategy = new GeenStrategy();

    public static void addEnumHulpdienstenEnSchepenToDB() {
        //Alle mogelijke enums toevoegen aan database
        try {
            int enumi = 0;
            List alleBestaandeEnums = kustwachtQueries.getAllVervoermiddelTypes();
            List hulpdienstenList = java.util.Arrays.asList(Hulpdiensten.values()); //lijst met hulpdiensten enums
            List schepenList = java.util.Arrays.asList(Schepen.values());   //lijst met schepen enums

            while (enumi < hulpdienstenList.size())  //hulpdiensten enums inlezen in database
            {
                String gezocht = hulpdienstenList.get(enumi).toString();
                if ((alleBestaandeEnums.contains(gezocht)) == false) {
                    kustwachtQueries.addTypeVervoermiddel(hulpdienstenList.get(enumi).toString(), 0);
                    enumi++;
                } else {
                    enumi++;
                }
            }

            enumi = 0;  //enumi terug op 0 zetten voor de volgende lijst te controleren
            while (enumi < schepenList.size())   //schepen enums inlezen in database
            {
                String gezocht = schepenList.get(enumi).toString();
                if ((alleBestaandeEnums.contains(gezocht)) == false) {
                    kustwachtQueries.addTypeVervoermiddel(schepenList.get(enumi).toString(), 1);
                    enumi++;
                } else {
                    enumi++;
                }
            }
        }
        catch (Exception E){
            EventLogger.logger.error(String.format(E.getMessage()));
            throw new IllegalArgumentException(E.getMessage());
        }
    }

    public static void addEnumStatusVervoermiddelToDB() {
        //Alle mogelijke enums toevoegen aan database
        try {
            int enumi = 0;
            List alleBestaandeStatussen = kustwachtQueries.getAllVervoermiddelStatussen();
            List statusVervoermiddelList = java.util.Arrays.asList(StatusVervoermiddel.values()); //lijst met status enums

            while (enumi < statusVervoermiddelList.size())  //hulpdiensten enums inlezen in database
            {
                String gezocht = statusVervoermiddelList.get(enumi).toString();
                if ((alleBestaandeStatussen.contains(gezocht)) == false) {
                    kustwachtQueries.addStatusVervoermiddel(statusVervoermiddelList.get(enumi).toString());
                    enumi++;
                } else {
                    enumi++;
                }
            }
        }
        catch (Exception E){
            EventLogger.logger.error(String.format(E.getMessage()));
            throw new IllegalArgumentException(E.getMessage());
        }
    }

    public static void inlezenVerkeerstorens() {
        try {
            AbstractActorFactory actor = FactoryProducer.getFactory(Actors.HULPDIENST);
            ArrayList<Verkeerstoren> verkeerstorens = kustwachtQueries.getAllVerkeerstorens();

            for (Verkeerstoren item : verkeerstorens) {
                String typeNaam = item.getEnumNaam();
                String naam = item.getNaam();
                Coördinaten coördinaten = item.getCoördinaten();
                IHulpdienstStrategy strategy = geenStrategy;

                Verkeerstoren verkeerstoren = actor.setVerkeersToren(typeNaam, naam, Hulpdiensten.VERKEERSTOREN, coördinaten, strategy);
            }
        }
        catch (Exception E){
            EventLogger.logger.error(String.format(E.getMessage()));
            throw new IllegalArgumentException(E.getMessage());
        }
    }

    public static void inlezenSchepen() {
        try {
            AbstractActorFactory actor = FactoryProducer.getFactory(Actors.SCHIP);
            ArrayList<Vervoermiddel> schepen = kustwachtQueries.getAllSchepen();
            schepen.size();

            for (Vervoermiddel item : schepen) {
                String typeNaam = item.getEnumNaam();
                String naam = item.getNaam();
                Coördinaten coördinaten = item.getCoördinaten();
                double snelheid = item.getSnelheid();
                double grootte = item.getGrootte();
                double capaciteit = item.getCapaciteit();
                int koers = item.getKoers();
                String status = item.getStatus();
                IHulpdienstStrategy strategy = geenStrategy;

                Vervoermiddel vervoermiddel;
                switch (typeNaam) {
                    case "CONTAINERSCHIP":
                        vervoermiddel = actor.setSchip(typeNaam, naam, Schepen.CONTAINERSCHIP, coördinaten, snelheid, grootte, capaciteit, koers, strategy, status);
                        break;
                    case "MOTORBOOT":
                        vervoermiddel = actor.setSchip(typeNaam, naam, Schepen.MOTORBOOT, coördinaten, snelheid, grootte, capaciteit, koers, strategy, status);
                        break;
                    case "TANKER":
                        vervoermiddel = actor.setSchip(typeNaam, naam, Schepen.TANKER, coördinaten, snelheid, grootte, capaciteit, koers, strategy, status);
                        break;
                    case "ZEILBOOT":
                        vervoermiddel = actor.setSchip(typeNaam, naam, Schepen.ZEILBOOT, coördinaten, snelheid, grootte, capaciteit, koers, strategy, status);
                        break;
                }
            }
        }
        catch (Exception E){
            EventLogger.logger.error(String.format(E.getMessage()));
            throw new IllegalArgumentException(E.getMessage());
        }
    }

    public static void inlezenHulpdiensten() {
        try {
            AbstractActorFactory actor = FactoryProducer.getFactory(Actors.HULPDIENST);
            ArrayList<Vervoermiddel> hulpdiensten = kustwachtQueries.getAllHulpdiensten();
            hulpdiensten.size();

            for (Vervoermiddel item : hulpdiensten) {
                String typeNaam = item.getEnumNaam();
                String naam = item.getNaam();
                Coördinaten coördinaten = item.getCoördinaten();
                double snelheid = item.getSnelheid();
                double grootte = item.getGrootte();
                double capaciteit = item.getCapaciteit();
                int koers = item.getKoers();
                String status = item.getStatus();
                IHulpdienstStrategy strategy = geenStrategy;

                Vervoermiddel vervoermiddel;
                switch (typeNaam) {
                    case "SEAKING":
                        vervoermiddel = actor.setHulpDienst(typeNaam, naam, SEAKING, coördinaten, snelheid, grootte, capaciteit, koers, strategy, status);
                        break;
                    case "SCHEEPSVAARTPOLITIE":
                        vervoermiddel = actor.setHulpDienst(typeNaam, naam, SCHEEPSVAARTPOLITIE, coördinaten, snelheid, grootte, capaciteit, koers, strategy, status);
                        break;
                }
            }
        }
        catch (Exception E){
            EventLogger.logger.error(String.format(E.getMessage()));
            throw new IllegalArgumentException(E.getMessage());
        }
    }
}
