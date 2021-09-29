package MetroSystem.src.metroSystem;

import java.util.ArrayList;

public abstract class Criteria {
    private final String criteriaName;
    protected Algorithm algorithm;
    protected ArrayList<ArrayList<Double>> adjacencyList;

    public Criteria(String name){
        this.criteriaName = name;
    }

    /**
     * Read specified data from Database and create our own AdjacencyList.
     */
    public abstract void createAdjList();

    /**
     * Find the optimal route using the pre-defined algorithm.
     * @param startStationId    A integer id that refers to the starting MTR station
     * @param endStationId      A integer id that refers to the ending MTR station
     * @return  A ArrayList that starts with {@code startStationId}, all the passing station, and ending with {@code endStationId}.
     */
    public ArrayList<Integer> findRoute(int startStationId, int endStationId){
        return algorithm.findRoute(startStationId, endStationId, adjacencyList);
    }

    @Override
    public String toString(){
        return String.format("The criteria : %s", this.criteriaName);
    }

}
