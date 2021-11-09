package testMetroSystem;

import metroSystem.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class testEdge {
    private MetroSystem m;
    private Database db;
    private Station startStation;
    private Station endStation;
    private Edge edge;

    @BeforeEach
    public void setUp() throws Exception {
        m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.English);
        db = m.getDatabase();
        startStation = m.getDatabase().getStationByName("Kennedy Town", Language.English, AdministratorHK.getInstance());
        endStation = m.getDatabase().getStationByName("HKU", Language.English, AdministratorHK.getInstance());
        edge = new Edge(0, startStation, endStation, 10, AdministratorHK.getInstance());
    }

    @Test
    @DisplayName("Edge Class test - getIsConnect Method")
    public void test_getIsConnect() {
        assertTrue(edge.getIsConnect());
    }

    @Test
    @DisplayName("Edge Class test - setIsOpen & getIsConnect Methods")
    public void test_setIsOpen() {
        edge.setIsOpen(false);
        assertFalse(edge.getIsConnect());
    }

    @Test
    @DisplayName("Edge Class test - toString Method")
    public void  test_toString() {
        String expected = "Kennedy Town->HKU";
        String result = edge.toString();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Edge Class test - getLine Method 1")
    public void test_getLine_1() {
        String result = edge.getLine();
        assertEquals("null", result);
    }

    @Test
    @DisplayName("Edge Class test - getLine Method 2")
    public void test_getLine_2() throws ExLineNotFound {
        edge.setLine(m.getDatabase().getLineByName("Island Line", Language.English));
        String result = edge.getLine();
        assertEquals("Island Line", result);
    }

    @Test
    @DisplayName("Edge Class test - getStartSta Method")
    public void test_getStartSta() {
        assertEquals(this.startStation, edge.getStartSta());
    }

    @Test
    @DisplayName("Edge Class test - getEndSta Method")
    public void test_getEndSta() {
        assertEquals(this.endStation, edge.getEndSta());
    }

    @Test
    @DisplayName("Edge Class test - getTimeSpend Method")
    public void test_getTimeSpend() {
        assertEquals(10, edge.getTimeSpend());
    }
}
