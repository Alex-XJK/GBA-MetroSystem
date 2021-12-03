package metroSystem;

import java.util.ArrayList;

/**
 * Data list, just like an adjacency list.
 * <p>
 *     We apply Factory Method Pattern on it.<br>
 *     At the sametime, the Hollywood principle has also been applied successfully.<br>
 *     The key core is an array of arraylist which contains {@link metroSystem.NodeEntry} as its cells.
 * </p>
 * @since Oct 3, 2021
 * @version 2
 */
public abstract class DataList {
    protected ArrayList<NodeEntry>[] data;
    protected Integer size;

    public DataList() {
        Database db = Database.getInstance();
        size = db.getStationCount();
        data = new ArrayList[size+1];
        for (int i = 0; i < size+1; i++) {
        	data[i] = new ArrayList<NodeEntry>();
        }
        createGraph();
    }

    /**
     * Load the data from Database and Create the data graph.
     */
    protected abstract void createGraph();

    /**
     * Retrieve back all the neighbors of the station {@code index}.
     * @return  An array of ArrayList holds all the adjacency nodes information
     */
    public ArrayList<NodeEntry>[] getGraph(){
        return data;
    }

    /**
     * Retrieve back all the neighbors of the station {@code index}.
     * @param index The index number of the target station
     * @return  A ArrayList holds all its neighbors information
     */
    public ArrayList<NodeEntry> getNeighbors(int index){
        return data[index];
    }
    
    /**
     * Retrieve back the size of the stations.
     * @return  A Integer.
     */
    public Integer getSize() {
    	return size;
    }
    
    /**
     * Print out the Data_List as an Adjacency_List for debug use only.
     */
    public void debugPrint(){
        for (int i = 0; i < data.length; i++ ) {
            System.out.printf("Station %d >>>\t", i);
            ArrayList<NodeEntry> array = data[i];
            for (NodeEntry node : array) {
                System.out.print("<" + node.getKey() + " (" + node.getValue() + ")> - ");
            }
            System.out.println();
        }
    }
}
