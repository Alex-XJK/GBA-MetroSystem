package testMetroSystem;

import metroSystem.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class testAlgorithm {
    private MetroSystem m;
    private Database db;

    @BeforeEach
    public void setUp() {
        m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.English);
        db = m.getDatabase();
    }

    @Test
    @DisplayName("AlgBFS Class test - findRoute method - normal")
    public void test_findRoute_BFS_1() {
        DLConnectivity dlConnectivity = new DLConnectivity();
        AlgBFS algBFS = new AlgBFS();
        ArrayList<Integer> route = algBFS.findRoute(64, 160, dlConnectivity);
        Integer[] expected = {64, 65, 66, 67, 68, 136, 288, 108, 147, 160};
        /*ArrayList<String> testName = m.getDatabase().translateId2Name(route);
        for (int a=0; a<testName.size();a++) {
            System.out.println(testName.get(a));
        }*/
        assertArrayEquals(expected, route.toArray(new Integer[0]));
    }

    /*@Test
    @DisplayName("AlgBFS Class test - findRoute method - start equals to end")
    public void test_findRoute_BFS_2() {
        DLConnectivity dlConnectivity = new DLConnectivity();
        AlgBFS algBFS = new AlgBFS();
        ArrayList<Integer> route = algBFS.findRoute(64, 64, dlConnectivity);
        Integer[] expected = {64};
        assertArrayEquals(expected, route.toArray(new Integer[0]));
    }*/

    @Test
    @DisplayName("AlgDijkstra Class test - findRoute method - normal")
    public void test_findRoute_Dijkstra_1() {
        DLTime dlTime = new DLTime();
        AlgDijkstra algDijkstra = new AlgDijkstra();
        ArrayList<Integer> route = algDijkstra.findRoute(5, 60, dlTime);
        //Integer[] expected = {64, 65, 66, 67, 68, 136, 288, 108, 147, 160};
        Integer[] expected = {5, 6, 60};
        assertArrayEquals(expected, route.toArray(new Integer[0]));
    }

    @Test
    @DisplayName("AlgDijkstra Class test - findRoute method - start equals to end")
    public void test_findRoute_Dijkstra_2() {
        DLTime dlTime = new DLTime();
        AlgDijkstra algDijkstra = new AlgDijkstra();
        ArrayList<Integer> route = algDijkstra.findRoute(64, 64, dlTime);
        assertNull(route);
    }
}
