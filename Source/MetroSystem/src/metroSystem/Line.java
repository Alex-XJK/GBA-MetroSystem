package metroSystem;

import java.util.ArrayList;

/**
 * A class that encapsulates information about a subway line.
 * <p>
 *     Encapsulate all the information required by the "line",
 *     accept the management of the {@link metroSystem.Database},
 *     used for more detailed function implementation.
 * </p>
 * @since Sept. 26, 2021
 * @version 1
 */
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

    /**
     * Get the subway line name in default the system language.
     * @return  The line name in string format;
     *          "Error" if the line does not have a name in system language
     */
    public String getName() {
        if(MetroSystem.getInstance().getSystemLanguage() == Language.English)
            return englishName;
        if(MetroSystem.getInstance().getSystemLanguage() == Language.TraditionalChinese)
            return traditionalChineseName;
        if(MetroSystem.getInstance().getSystemLanguage() == Language.SimplifiedChinese)
            return simplifiedChineseName;
        return "Error";
    }

    /**
     * Get the subway line name in a specified language.
     * @param language Your desired language object to select language
     * @return  The line name in string format;
     *          "Error" if the line does not have a name in your specified language
     */
    public String getNameInSpecificLanguage(Language language) {
        if(language == Language.English)
            return englishName;
        if(language == Language.TraditionalChinese)
            return traditionalChineseName;
        if(language == Language.SimplifiedChinese)
            return simplifiedChineseName;
        return "Error";
    }

    /**
     * Getter function of edge information
     * @return An ArrayList that contains all the edges on this line
     */
    public ArrayList<Edge> getEdges() {
        return edges;
    }

    /**
     * Find a line according to its line name.
     * Involve {@code Line} to handle core searching.
     * @param allLines  All the subway lines we have
     * @param name      The name of your target subway line
     * @param language  The language you are using
     * @return The reference of your target line
     */
    public static Line searchLineByName(ArrayList<Line> allLines, String name, Language language) {
        for(Line l : allLines) {
            if(l.getNameInSpecificLanguage(language).equals(name))
                return l;
        }
        return null;
    }
}
