package MetroSystem.src.metroSystem;

import java.util.ArrayList;
import java.util.Map;

public class DataList {
	
	private volatile static DataList uniqueInstance;
        protected ArrayList<Map.Entry<Integer, Integer>>[] data;

    private DataList() {
        Database db = Database.getInstance();

    }

    /**
     * Apply double-checked locking method to create this important singleton object.
     * @return  the unique DataList instance
     * @since   Oct. 3, 2021
     */
    public static DataList getInstance() {
        if(uniqueInstance == null) {
            synchronized (DataList.class) {
                if(uniqueInstance == null) {
                    uniqueInstance = new DataList();
                }
            }
        }
        return uniqueInstance;
    }
}
