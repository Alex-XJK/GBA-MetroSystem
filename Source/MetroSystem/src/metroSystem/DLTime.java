package metroSystem;

import java.util.ArrayList;

/**
 * A kind of {@link metroSystem.DataList} that focused on the time spend between two stations.
 * @since Oct. 3, 2021
 * @version 1
 */
public class DLTime extends DataList{

    public DLTime(){
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createGraph() {
        Database db = Database.getInstance();
        ArrayList<Edge> allEdges = db.getEdges();
        for (Edge e : allEdges) {
            int sta1 = e.getStartSta().getId();
            int sta2 = e.getEndSta().getId();
            int weight = e.getTimeSpend();
            
            // endPoints[parentNode/index, adjacenttNode]
            ArrayList<Integer> endPoints1 = new ArrayList<Integer>();
            endPoints1.add(0, Integer.valueOf(sta1));
            endPoints1.add(1,Integer.valueOf(sta2));
            ArrayList<Integer> endPoints2 = new ArrayList<Integer>();
            endPoints2.add(0, Integer.valueOf(sta2));
            endPoints2.add(1,Integer.valueOf(sta1));
            
            data[sta1].add(new NodeEntry<ArrayList<Integer>, Integer>(endPoints1, weight));
            data[sta2].add(new NodeEntry<ArrayList<Integer>, Integer>(endPoints2, weight));
        }
    }
}
