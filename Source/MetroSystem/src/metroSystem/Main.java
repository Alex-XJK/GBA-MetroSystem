package metroSystem;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) {
        MetroSystem m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.SimplifiedChinese);

        // For testing
        /*
        m.getAllEdgesInALine("7号线", Language.SimplifiedChinese);
        m.getAllEdgesInALine("ERROR", Language.English);
        m.getAllEdgesInAStation("九龍塘", Language.TraditionalChinese, AdministratorHK.getInstance());

        Station startStation = m.getStationByName("屯門", Language.TraditionalChinese, AdministratorHK.getInstance());
        Station endStation = m.getStationByName("少年宫", Language.SimplifiedChinese, AdministratorSZ.getInstance());
        m.computePrice(startStation, endStation);
        endStation = m.getStationByName("羅湖", Language.TraditionalChinese, AdministratorHK.getInstance());
        m.computePrice(startStation, endStation);
        startStation = m.getStationByName("罗湖", Language.SimplifiedChinese, AdministratorSZ.getInstance());
        endStation = m.getStationByName("碧头", Language.SimplifiedChinese, AdministratorSZ.getInstance());
        m.computePrice(startStation,endStation);

        startStation = m.getStationByName("屯門", Language.TraditionalChinese, AdministratorHK.getInstance());
        endStation = m.getStationByName("少年宫", Language.SimplifiedChinese, AdministratorSZ.getInstance());
        m.computeShortestPath(startStation, endStation, new CrtTime());
        */
    }
   
}