package testMetroSystem;

import metroSystem.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class testDataList {
    private MetroSystem m;
    private Database db;

    @BeforeEach
    public void setUp() throws Exception {
        m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.English);
        db = m.getDatabase();
    }

    @Test
    @DisplayName("DLConnectivity Class test - getGraph() & createGraph() Methods")
    public void test_getGraph_Connectivity() {
        DLConnectivity dlConnectivity = new DLConnectivity();
        ArrayList<NodeEntry>[] result = dlConnectivity.getGraph();
        assertNotNull(result);
    }

    @Test
    @DisplayName("DLConnectivity Class test - getNeighbors() Method")
    public void test_getNeighbors_Connectivity() {
        DLConnectivity dlConnectivity = new DLConnectivity();
        ArrayList<NodeEntry> result = dlConnectivity.getNeighbors(1);
        assertNotNull(result);
    }

    @Test
    @DisplayName("DLConnectivity Class test - getSize() Method")
    public void test_getSize_Connectivity() {
        DLConnectivity dlConnectivity = new DLConnectivity();
        Integer size = dlConnectivity.getSize();
        assertEquals(Integer.valueOf(334),size);
    }

    @Test
    @DisplayName("DLTime Class test - getGraph() & createGraph() Methods")
    public void test_getGraph_Time() {
        DLTime dlTime = new DLTime();
        ArrayList<NodeEntry>[] result = dlTime.getGraph();
        assertNotNull(result);
    }

    @Test
    @DisplayName("DLTime Class test - getNeighbors() Method")
    public void test_getNeighbors_Time() {
        DLTime dlTime = new DLTime();
        ArrayList<NodeEntry> result = dlTime.getNeighbors(1);
        assertNotNull(result);
    }

    @Test
    @DisplayName("DLTime Class test - getSize() Method")
    public void test_getSize_Time() {
        DLTime dlTime = new DLTime();
        Integer size = dlTime.getSize();
        assertEquals(Integer.valueOf(334),size);
    }

    @Test
    @DisplayName("DLConnectivity & DLTime Class test - createGraph() Methods creates different graphs")
    public void test_createGraph_DLBoth() {
        DLConnectivity dlConnectivity = new DLConnectivity();
        DLTime dlTime = new DLTime();
        ArrayList<NodeEntry>[] resultCon = dlConnectivity.getGraph();
        ArrayList<NodeEntry>[] resultTime = dlTime.getGraph();
        assertNotNull(resultCon);
        assertNotNull(resultTime);
        assertNotEquals(resultCon,resultTime);
    }
}
