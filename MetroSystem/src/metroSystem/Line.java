package MetroSystem.src.metroSystem;

import java.util.ArrayList;

public class Line {
    private final int identifier;
    private final String englishName, traditionalChineseName, simplifiedChineseName;
    private final Administrator admin;
    private final ArrayList<Edge> edges;

    public Line(int id, String englishName, String traditionalChineseName, String simplifiedChineseName, Administrator admin, ArrayList<Edge> tempEdges) {
        this.identifier = id;
        this.englishName = englishName;
        this.traditionalChineseName = traditionalChineseName;
        this.simplifiedChineseName = simplifiedChineseName;
        this.admin = admin;
        this.edges = tempEdges;
        for (Edge e : edges)
            e.setLine(this);
    }

    public String getName() {
        if(MetroSystem.getInstance().getSystemLanguage() == Language.English)
            return englishName;
        if(MetroSystem.getInstance().getSystemLanguage() == Language.TraditionalChinese)
            return traditionalChineseName;
        if(MetroSystem.getInstance().getSystemLanguage() == Language.SimplifiedChinese)
            return simplifiedChineseName;
        return "Error";
    }

    public String getNameInSpecificLanguage(Language language) {
        if(language == Language.English)
            return englishName;
        if(language == Language.TraditionalChinese)
            return traditionalChineseName;
        if(language == Language.SimplifiedChinese)
            return simplifiedChineseName;
        return "Error";
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }
}
