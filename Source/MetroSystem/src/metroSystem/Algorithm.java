package metroSystem;

import java.util.ArrayList;

/**
 * The Algorithm Interface used to implement the specified route finding algorithm.
 * <br>
 * By calling {@link #findRoute(int, int, DataList)} function you can get the desired optimal route.
 * @since Sept. 30, 2021
 */
public interface Algorithm {
    /**
     * Find the optimal route from start_station to end_station.
     * @param startId   A integer id that refers to the starting MTR station
     * @param endId     A integer id that refers to the ending MTR station
     * @param data      A nested ArrayList that contains station data, e.g., AdjacencyList
     * @return  A ArrayList that starts with {@code startId}, all the passing station, and ending with {@code endId}.
     */
    ArrayList<Integer> findRoute(int startId, int endId, DataList data);

}
