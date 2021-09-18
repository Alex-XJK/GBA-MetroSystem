package src;
import java.util.*;

public class Database {

    private static Database instance;
    private ArrayList<Station> allStations;

    private Database() {
        instance = new Database();
    }

    public Database getInstance() {
        return instance;
    }

    private void load() {
        //Scanner sc_HK = new Scanner("./stations_HK.txt"), sc_SZ = new Scanner("./stations_SZ.txt");
        //sc_HK.close();
    }
}