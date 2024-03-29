package metroSystem;
import java.util.*;

/**
 * A class that encapsulates information about an MTR station.
 * <p>
 *     Encapsulate all the information required by the "station",
 *     accept the management of the {@link metroSystem.Database},
 *     used for more detailed function implementation.
 * </p>
 * @since Sept. 21, 2021
 * @version 1
 */
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

	/**
	 * Add a new edge that this station is connected to
	 * @param e the new edge
	 */
	public void addEdgeTo(Edge e) {
		edgeTo.add(e);
	}

	/**
	 * Get the MTR station name in default the system language.
	 * @return  The station name in string format;
	 *          "Error" if the station does not have a name in system language
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
	 * Get the MTR station name in a specified language.
	 * @param language Your desired language object to select language
	 * @return  The station name in string format;
	 *          "Error" if the station does not have a name in your specified language
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
	 * Get the identifier of the current station.
	 * @return station id
	 */
	public int getId(){
		return identifier;
	}

	/**
	 * Get the administrator of the current station.
	 * @return the administrator that this station belongs to
	 */
	public Administrator getAdmin() {
		return admin;
	}

	/**
	 * Get all the edges the current station is connected to.
	 * @return an arraylist of edges
	 */
	public ArrayList<Edge> getEdgeTo() {
		return edgeTo;
	}

	/**
	 * Print out the travelling price between two stations.
	 * Involve {@code Database} to handle price retrieve.
	 * Code Refactoring by Alex using “Extract Method” strategy from xrr's original function inside Database.
	 * @param startStation	The start station object
	 * @param endStation	The end station object
	 * @since Oct. 20, 2021
	 */
	public static void getStationPrice(Station startStation, Station endStation) {
		Database db = Database.getInstance();
		if(startStation.getAdmin() == endStation.getAdmin()) {
			System.out.println(
				startStation.getName() + "->" +
				endStation.getName() + ": " +
				db.getPrice(startStation.getId(), endStation.getId(), startStation.getAdmin())
			);
		}
		else {
			if(MetroSystem.getInstance().getSystemLanguage() == Language.English)
				System.out.println("The two stations belong to different administrations, and the cross-segment calculation should be carried out according to the stations through the route");
			if(MetroSystem.getInstance().getSystemLanguage() == Language.TraditionalChinese)
				System.out.println("兩站屬於不同管轄範圍，需根據路線經過站進行跨段計算");
			if(MetroSystem.getInstance().getSystemLanguage() == Language.SimplifiedChinese)
				System.out.println("两站属于不同管辖范围，需根据路线经过站进行跨段计算");
		}
	}

	/**
	 * Find a station according to its station_name.
	 * Code Refactoring by Alex using “Extract Method” strategy from xrr's original function inside Database.
	 * @param allStations 	The whole arraylist of stations we have.
	 * @param name      	The name of your target station
	 * @param language  	The language you are using
	 * @param admin     	The instance of the administrator of the station.
	 * @return The reference of your target station
	 */
	public static Station searchStationByName(ArrayList<Station> allStations, String name, Language language, Administrator admin) {
		for(Station s : allStations) {
			if(s.getNameInSpecificLanguage(language).equals(name) && s.getAdmin() == admin)
				return s;
		}
		return null;
	}

	/**
	 * Find a station according to its station_id.
	 * Code Refactoring by Alex using “Extract Method” strategy from Alex's original function inside Database.
	 * @param allStations 	The whole arraylist of stations we have.
	 * @param id    		The id of your target station
	 * @return The reference of your target station
	 */
	public static Station searchStationById(ArrayList<Station> allStations, int id) {
		for(Station s : allStations) {
			if(s.getId() == id) {
				return s;
			}
		}
		return null;
	}
}