package MetroSystem.src.metroSystem;
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

	public int getId(){
		return identifier;
	}

	public Administrator getAdmin() {
		return admin;
	}

	public ArrayList<Edge> getEdgeTo() {
		return edgeTo;
	}
}