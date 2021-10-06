package MetroSystem.src.metroSystem;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) {
        MetroSystem m = MetroSystem.getInstance();
        m.setSystemLanguage(LanguageSimplifiedChinese.getInstance());

        // Commented out due to character cannot be resolved
        // Line testLine = m.getDatabase().getLineByName("�|�F��", LanguageTraditionalChinese.getInstance());
        // System.out.println(testLine.getName());
        // ArrayList<Edge> edges = testLine.getEdges();
        // for (Edge e : edges)
        //     System.out.println(e.toString());
        // System.out.println();

        // Commented out due to character cannot be resolved
        // Station testStation = m.getDatabase().getStationByName("������", LanguageTraditionalChinese.getInstance(), AdministratorHK.getInstance());
        // System.out.println(testStation.getName());
        // edges = testStation.getEdgeTo();
        // for (Edge e : edges) {
        //     System.out.println(e.toString() + ' ' + e.getLine());
        // }

        //Declare new criteria
        Criteria c = new CrtStation();
        System.out.println(c.toString());

        //Compute the shortest path
        int start_station_id = 5;
        int end_station_id = 88;
        ArrayList<Integer> result = c.findRoute(start_station_id, end_station_id);

        //Print out the final result
        ArrayList<String> nameResult = m.getDatabase().translateId2Name(result);
        System.out.println(nameResult);
    }
   
}