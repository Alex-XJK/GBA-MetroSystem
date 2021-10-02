package metroSystem;

import java.util.ArrayList;

public class Main
{
   public static void main(String[] args) {
       MetroSystem m = MetroSystem.getInstance();
       m.setSystemLanguage(LanguageSimplifiedChinese.getInstance());

       Line testLine = m.getDatabase().getLineByName("東鐵線", LanguageTraditionalChinese.getInstance());
       System.out.println(testLine.getName());
       ArrayList<Edge> edges = testLine.getEdges();
       for (Edge e : edges)
           System.out.println(e.toString());
       System.out.println();

       Station testStation = m.getDatabase().getStationByName("九龍塘", LanguageTraditionalChinese.getInstance(), AdministratorHK.getInstance());
       System.out.println(testStation.getName());
       edges = testStation.getEdgeTo();
       for (Edge e : edges)
           System.out.println(e.toString() + ' ' + e.getLine());
   }
}