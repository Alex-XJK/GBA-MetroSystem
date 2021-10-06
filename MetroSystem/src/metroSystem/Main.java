package MetroSystem.src.metroSystem;

import java.util.ArrayList;

public class Main
{
   public static void main(String[] args) {
       MetroSystem m = MetroSystem.getInstance();
       m.setSystemLanguage(LanguageSimplifiedChinese.getInstance());

       Line testLine = m.getDatabase().getLineByName("–|èF¾€", LanguageTraditionalChinese.getInstance());
       System.out.println(testLine.getName());
       ArrayList<Edge> edges = testLine.getEdges();
       for (Edge e : edges)
           System.out.println(e.toString());
       System.out.println();

       Station testStation = m.getDatabase().getStationByName("¾ÅýˆÌÁ", LanguageTraditionalChinese.getInstance(), AdministratorHK.getInstance());
       System.out.println(testStation.getName());
       edges = testStation.getEdgeTo();
       for (Edge e : edges) {
           System.out.println(e.toString() + ' ' + e.getLine());
       }
       
       Criteria c = new CrtStation();
       ArrayList<Integer> result = c.findRoute(5, 88);
       System.out.println(result);
   }
   
}