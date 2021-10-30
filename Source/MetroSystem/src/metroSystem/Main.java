package metroSystem;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) {
        MetroSystem m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.SimplifiedChinese);

        // Demo of "edgeTo"
        Line testLine = null;
        try {
            testLine = m.getDatabase().getLineByName("Line 7", Language.English);
        } catch (ExLineNotFound e) {
            System.out.println(e.getMessage());
        }
        System.out.println(testLine.getName());
        ArrayList<Edge> edges = testLine.getEdges();
        for (Edge e : edges)
            System.out.println(e.toString());
        System.out.println();

        Station testStation = null;
        try {
            testStation = m.getDatabase().getStationByName("Kowloon Tong", Language.English, AdministratorHK.getInstance());
        } catch (ExStationNotFound e) {
            System.out.println(e.getMessage());
        }
        System.out.println(testStation.getName());
        edges = testStation.getEdgeTo();
        for (Edge e : edges) {
            System.out.println(e.toString() + ' ' + e.getLine());
        }
        System.out.println();

        // Demo of "Price" function
        Station startStation = null;
        Station endStation = null;
        try {
            startStation = m.getDatabase().getStationByName("Tuen Mun", Language.English, AdministratorHK.getInstance());
            endStation = m.getDatabase().getStationByName("Children's Palace", Language.English, AdministratorSZ.getInstance());
            Station.getStationPrice(startStation, endStation);
            endStation = m.getDatabase().getStationByName("Lo Wu", Language.English, AdministratorHK.getInstance());
            Station.getStationPrice(startStation, endStation);
            startStation = m.getDatabase().getStationByName("Luohu", Language.English, AdministratorSZ.getInstance());
            endStation = m.getDatabase().getStationByName("Bitou", Language.English, AdministratorSZ.getInstance());
            Station.getStationPrice(startStation, endStation);
        } catch (ExStationNotFound e) {
            System.out.println(e.getMessage());
        }

      //Declare new criteria
        Criteria c2 = new CrtTime();
        System.out.println(c2);

        //Compute the shortest path
        Station startStation2 = null;
        Station endStation2 = null;
        try {
            startStation2 = m.getDatabase().getStationByName("Tuen Mun", Language.English, AdministratorHK.getInstance());
            endStation2 = m.getDatabase().getStationByName("Children's Palace", Language.English, AdministratorSZ.getInstance());
        } catch (ExStationNotFound e) {
            System.out.println(e.getMessage());
        }
        ArrayList<Integer> result2 = c2.findRoute(startStation2.getId(), endStation2.getId());

        //Print out the final result
        ArrayList<String> nameResult2 = m.getDatabase().translateId2Name(result2);
        System.out.println(nameResult2);

    }
   
}