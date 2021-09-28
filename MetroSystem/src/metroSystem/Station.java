package MetroSystem.src.metroSystem;
import java.util.*;

public class Station
{
	private final int identifier;
	private final String englishName, traditionalChineseName, simplifiedChineseName;
	private final Administrator admin;

	public Station(int id, String englishName, String traditionalChineseName, String simplifiedChineseName, Administrator admin) {
		this.identifier = id;
		this.englishName = englishName;
		this.traditionalChineseName = traditionalChineseName;
		this.simplifiedChineseName = simplifiedChineseName;
		this.admin = admin;
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
}