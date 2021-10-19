package MetroSystem.src.metroSystem;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) {
        MetroSystem m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.SimplifiedChinese);

        // Demo of "edgeTo"
        Line testLine = m.getDatabase().getLineByName("Line 7", Language.English);
        System.out.println(testLine.getName());
        ArrayList<Edge> edges = testLine.getEdges();
        for (Edge e : edges)
            System.out.println(e.toString());
        System.out.println();

        Station testStation = m.getDatabase().getStationByName("Kowloon Tong", Language.English, AdministratorHK.getInstance());
        System.out.println(testStation.getName());
        edges = testStation.getEdgeTo();
        for (Edge e : edges) {
            System.out.println(e.toString() + ' ' + e.getLine());
        }
        System.out.println();

        //Declare new criteria
        Criteria c = new CrtStation();
        System.out.println(c.toString());

        //Compute the shortest path
        Station startStation = m.getDatabase().getStationByName("Tuen Mun", Language.English, AdministratorHK.getInstance());
        Station endStation = m.getDatabase().getStationByName("Children's Palace", Language.English, AdministratorSZ.getInstance());
        ArrayList<Integer> result = c.findRoute(startStation.getId(), endStation.getId());

        //Print out the final result
        ArrayList<String> nameResult = m.getDatabase().translateId2Name(result);
        System.out.println(nameResult);

        // Demo of "Price" function
        System.out.println();
        m.getDatabase().getPrice(startStation, endStation);
        endStation = m.getDatabase().getStationByName("Lo Wu", Language.English, AdministratorHK.getInstance());
        m.getDatabase().getPrice(startStation, endStation);
        startStation = m.getDatabase().getStationByName("Luohu", Language.English, AdministratorSZ.getInstance());
        endStation = m.getDatabase().getStationByName("Bitou", Language.English, AdministratorSZ.getInstance());
        m.getDatabase().getPrice(startStation, endStation);

    }
   
}