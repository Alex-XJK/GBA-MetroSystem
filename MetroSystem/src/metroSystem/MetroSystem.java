package MetroSystem.src.metroSystem;
import java.util.*;

public class MetroSystem
{
	private static MetroSystem instance = new MetroSystem();
    private Language systemLanguage;
    private Database database;
    

    private MetroSystem() {
        systemLanguage = LanguageEnglish.getInstance();
        database = Database.getInstance();
        database.loadStations();
        database.loadEdges();
        database.loadLines();
    }

    public static MetroSystem getInstance() {
        return instance;
    }

    public void setSystemLanguage(Language l) {
        systemLanguage = l;
    }

    public Language getSystemLanguage() {
        return systemLanguage;
    }

    public Database getDatabase() {
        return database;
    }
}