package MetroSystem.src.metroSystem;

import java.util.ArrayList;

public class AlgBFS implements Algorithm{

    public AlgBFS(){

    }

    /**
     * A dummy placeholder for testing only
     * @return  ArrayList: [startId, 1, 2, 3, endId]
     */
    private ArrayList<Integer> dummyArrayList(int startId, int endId){
        ArrayList<Integer> result = new ArrayList<>();
        result.add(startId);
        result.add(1);
        result.add(2);
        result.add(3);
        result.add(endId);
        return result;
    }

    @Override
    public ArrayList<Integer> findRoute(int startId, int endId, ArrayList<ArrayList<Double>> data) {
        //Todo: Implement BFS algorithm
        return dummyArrayList(startId, endId);
    }
}
