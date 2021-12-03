package metroSystem;
import java.util.*;

/**
 * The facade of the back-end system.
 * <p>
 *     As a facade, the highest-level superclass (especially for data related section),
 *     used to govern all databases and related functions,
 *     simplifying the call process.
 * </p>
 */
public class MetroSystem
{
    private volatile static MetroSystem instance;
    private Language systemLanguage;
    private Database database;
    

    private MetroSystem() {
        systemLanguage = Language.English;
        database = Database.getInstance();
        database.loadStations();
        database.loadEdges();
        database.loadLines();
        database.loadPrice();
    }

    /**
     * Apply double-checked locking method to create this important singleton object.
     * @return  the unique MetroSystem instance
     * @since   Nov. 12, 2021 (Refactored)
     */
    public static MetroSystem getInstance() {
        if(instance == null) {
            synchronized (MetroSystem.class) {
                if(instance == null) {
                    instance = new MetroSystem();
                }
            }
        }
        return instance;
    }

    /**
     * Set or change the current system language
     * @param l An object of the new language you want
     */
    public void setSystemLanguage(Language l) {
        systemLanguage = l;
    }

    /**
     * Getter function of the current system language
     * @return The current in effect language setting
     */
    public Language getSystemLanguage() {
        return systemLanguage;
    }

    /**
     * Getter function of the current database instance we use
     * @return The current in use database
     */
    public Database getDatabase() { return database; }

    /**
     * Get a subway station according to its name.
     * @param name      The name of the station you are looking for
     * @param language  The language type that parameter {@code name} in
     * @param admin     The administration area we need to search within
     * @return  The resulted station object; null if not found
     */
    public Station getStationByName(String name, Language language, Administrator admin) {
        try {
            return database.getStationByName(name, language, admin);
        } catch (ExStationNotFound e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Get a subway line according to its name.
     * @param name      The name of the line you are looking for
     * @param language  The language type that parameter {@code name} in
     * @return The result line object; null if not found
     */
    public Line getLineByName(String name, Language language) {
        try {
            return database.getLineByName(name, language);
        } catch (ExLineNotFound e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}