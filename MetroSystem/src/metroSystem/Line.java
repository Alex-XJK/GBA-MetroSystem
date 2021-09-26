package metroSystem;

import java.util.ArrayList;

public class Line {
    private final int identifier;
    private final String englishName, traditionalChineseName, simplifiedChineseName;
    private final Administrator admin;
    private final ArrayList<Station> viaStations = new ArrayList<>();

    public Line(int id, String englishName, String traditionalChineseName, String simplifiedChineseName, Administrator admin, ArrayList<Integer> tempStations) {
        this.identifier = id;
        this.englishName = englishName;
        this.traditionalChineseName = traditionalChineseName;
        this.simplifiedChineseName = simplifiedChineseName;
        this.admin = admin;
        for(int i = 0; i < tempStations.size(); i++)
            viaStations.add(Database.getInstance().getAllStations().get(tempStations.get(i) - 1));
    }

    public String getName() {
        if(MetroSystem.getInstance().getSystemLanguage() instanceof LanguageEnglish)
            return englishName;
        if(MetroSystem.getInstance().getSystemLanguage() instanceof LanguageTraditionalChinese)
            return traditionalChineseName;
        if(MetroSystem.getInstance().getSystemLanguage() instanceof LanguageSimplifiedChinese)
            return simplifiedChineseName;
        return "Error";
    }

    public String getNameInSpecificLanguage(Language language) {
        if(language instanceof LanguageEnglish)
            return englishName;
        if(language instanceof LanguageTraditionalChinese)
            return traditionalChineseName;
        if(language instanceof LanguageSimplifiedChinese)
            return simplifiedChineseName;
        return "Error";
    }

    public void getViaStations() {
        for(Station s : viaStations) {
            System.out.printf("%s ", s.getName());
        }
    }
}
