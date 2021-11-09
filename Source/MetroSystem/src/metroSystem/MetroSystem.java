package metroSystem;
import java.util.*;

public class MetroSystem
{
	private static MetroSystem instance = new MetroSystem();
    private Language systemLanguage;
    private Database database;
    

    private MetroSystem() {
        systemLanguage = Language.English;
        database = Database.getInstance();
        database.loadStations();
        database.loadEdges();
        database.loadLines();
        database.loadPrice();
    }

    public static MetroSystem getInstance() {
        return instance;
    }

    public void setSystemLanguage(Language l) {
        systemLanguage = l;
    }

    public Language getSystemLanguage() {
        return systemLanguage;
    }

    public Database getDatabase() {
        return database;
    }

    /**
     * Get all edges in a line.
     * @return  All edges in a line
     * @since   Nov. 9, 2021
     */
    public void getAllEdgesInALine(String name, Language language) {
        Line testLine = null;
        try {
            testLine = database.getLineByName(name, language);
            System.out.println(testLine.getName());
            ArrayList<Edge> edges = testLine.getEdges();
            for (Edge e : edges)
                System.out.println(e.toString());
            System.out.println();
        } catch (ExLineNotFound e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get all edges in a station.
     * @return  All edges in a station
     * @since   Nov. 9, 2021
     */
    public void getAllEdgesInAStation(String name, Language language, Administrator admin) {
        Station testStation = null;
        try {
            testStation = database.getStationByName(name, language, admin);
            System.out.println(testStation.getName());
            ArrayList<Edge> edges = testStation.getEdgeTo();
            for (Edge e : edges) {
                System.out.println(e.toString() + ' ' + e.getLine());
            }
            System.out.println();
        } catch (ExStationNotFound e) {
            System.out.println(e.getMessage());
        }
    }

    public Station getStationByName(String name, Language language, Administrator admin) {
        try {
            return database.getStationByName(name, language, admin);
        } catch (ExStationNotFound e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void computePrice(Station st_Station, Station ed_Station) {
        // Demo of "Price" function
        if(st_Station == null || ed_Station == null)
            return;
        Station startStation = st_Station;
        Station endStation = ed_Station;

        Station.getStationPrice(startStation, endStation);
    }

    public void computeShortestPath(Station st_Station, Station ed_Station, Criteria myCriteria) {
        if(st_Station == null || ed_Station == null)
            return;
        Station startStation2 = st_Station;
        Station endStation2 = ed_Station;

        //Declare new criteria
        Criteria criteria = myCriteria;
        System.out.println(criteria);

        //Compute the shortest path
        ArrayList<Integer> result2 = criteria.findRoute(startStation2.getId(), endStation2.getId());

        //Print out the final result
        ArrayList<String> nameResult2 = database.translateId2Name(result2);
        System.out.println(nameResult2);
    }
}