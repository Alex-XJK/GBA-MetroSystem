package metroSystem;
import java.util.*;

public class Station
{
	private final int identifier;
	private final String englishName, traditionalChineseName, simplifiedChineseName;
	private final Administrator admin;
	private final ArrayList<Edge> edgeTo;

	public Station(int id, String englishName, String traditionalChineseName, String simplifiedChineseName, Administrator admin) {
		this.identifier = id;
		this.englishName = englishName;
		this.traditionalChineseName = traditionalChineseName;
		this.simplifiedChineseName = simplifiedChineseName;
		this.admin = admin;
		edgeTo = new ArrayList<>();
	}

	public void addEdgeTo(Edge e) {
		edgeTo.add(e);
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

	public ArrayList<Edge> getEdgeTo() {
		return edgeTo;
	}
}