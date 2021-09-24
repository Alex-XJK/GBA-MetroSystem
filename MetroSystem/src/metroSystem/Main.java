package metroSystem;

import java.util.ArrayList;

public class Main
{
   public static void main(String[] args) {
       MetroSystem m = MetroSystem.getInstance();
       m.setSystemLanguage(LanguageTraditionalChinese.getInstance());

       ArrayList<Station> allStations = m.getDatabase().getAllStations();
       for(Station s : allStations) {
           System.out.println(s.getName());
       }
   }
}