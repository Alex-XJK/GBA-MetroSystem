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

        Criteria c = new CrtStation();
        ArrayList<Integer> result = c.findRoute(5, 88);
        System.out.println(result);
   }
   
}