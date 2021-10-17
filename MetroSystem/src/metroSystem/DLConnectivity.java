package MetroSystem.src.metroSystem;

import java.util.ArrayList;

public class DLConnectivity extends DataList{

    public DLConnectivity(){
        super();
    }

    @Override
    protected void createGraph() {
        Database db = Database.getInstance();
        ArrayList<Edge> allEdges = db.getEdges();
        for (Edge e : allEdges) {
            int sta1 = e.getStartSta().getId();
            int sta2 = e.getEndSta().getId();
//            if (e.dataIsConnect()){
            data[sta1].add(new NodeEntry<Integer, Integer>(sta2, 1));
            data[sta2].add(new NodeEntry<Integer, Integer>(sta1, 1));
//            }
        }
    }
}
