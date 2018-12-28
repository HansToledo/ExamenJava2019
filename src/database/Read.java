package database;

import enums.Hulpdiensten;
import enums.Schepen;
import enums.StatusVervoermiddel;

import java.util.List;

public class Read {
    private static final DBqueries kustwachtQueries = new DBqueries();

    public static void addEnumHulpdienstenEnSchepenToDB() {
        //Alle mogelijke enums toevoegen aan database
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

    public static void addEnumStatusVervoermiddelToDB() {
        //Alle mogelijke enums toevoegen aan database
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
}
