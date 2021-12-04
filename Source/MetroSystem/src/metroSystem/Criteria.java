package metroSystem;

import java.util.ArrayList;

/**
 * The Criteria Abstract Class used to hold for a kind of searching criteria.
 * <br>
 * By calling {@link metroSystem.Criteria#findRoute(int, int)} function
 * it will invoke {@link metroSystem.Algorithm#findRoute(int, int, DataList)} for you,
 * then you can easily get the optimal route under your requirement.
 * @since Sept. 30, 2021
 * @version 1
 */
public abstract class Criteria {
    private final String criteriaName;
    protected Algorithm algorithm;
    protected DataList adjacencyList;

    public Criteria(String name){
        this.criteriaName = name;
        this.adjacencyList = createDataList();
    }

    /**
     * Read specified data from Database and create our own AdjacencyList.
     */
    protected abstract DataList createDataList();

    /**
     * Find the optimal route using the pre-defined algorithm.
     * @param startStationId    A integer id that refers to the starting MTR station
     * @param endStationId      A integer id that refers to the ending MTR station
     * @return  A ArrayList that starts with {@code startStationId}, all the passing station, and ending with {@code endStationId}.
     */
    public ArrayList<Integer> findRoute(int startStationId, int endStationId){
        return algorithm.findRoute(startStationId, endStationId, adjacencyList);
    }

    /**
     * Display the basic information of this criteria
     * @return The information message
     */
    @Override
    public String toString(){
        return String.format("The criteria : %s", this.criteriaName);
    }

    /**
     * Display the graph structure which the current criteria is using.
     * @return The information message string
     */
    public void reportGraph(){
        this.adjacencyList.debugPrint();
    }
}
