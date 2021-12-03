package metroSystem;

import java.util.ArrayList;

/**
 * A kind of {@link metroSystem.DataList} that focused on whether two stations are connected.
 * @since Oct. 3, 2021
 * @version 1
 */
public class DLConnectivity extends DataList{

    public DLConnectivity(){
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
            data[sta1].add(new NodeEntry<Integer, Integer>(sta2, 1));
            data[sta2].add(new NodeEntry<Integer, Integer>(sta1, 1));
        }
    }
}
