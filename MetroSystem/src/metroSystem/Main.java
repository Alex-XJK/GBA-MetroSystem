package MetroSystem.src.metroSystem;

import java.util.ArrayList;

public class Main
{
   public static void main(String[] args) {
       MetroSystem m = MetroSystem.getInstance();
       m.setSystemLanguage(LanguageSimplifiedChinese.getInstance());

       Line testLine = m.getDatabase().getLineByName("11号线", LanguageSimplifiedChinese.getInstance());
       System.out.println(testLine.getName());
       testLine.getViaStations();
   }
}