package testMetroSystem;

import metroSystem.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class testCriteria {
    private MetroSystem m;
    private Database db;

    @BeforeEach
    public void setUp() throws Exception {
        m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.English);
        db = m.getDatabase();
    }

    @Test
    @DisplayName("CrtStation Class test - findRoute Method")
    public void test_findRoute_station() {
        CrtStation crtStation = new CrtStation();
        ArrayList<Integer> route = crtStation.findRoute(64, 160);
        Integer[] expected = {64, 65, 66, 67, 68, 136, 288, 108, 147, 160};
        assertArrayEquals(expected, route.toArray(new Integer[0]));
    }

    @Test
    @DisplayName("CrtStation Class test - toString Method")
    public void test_toString_station() {
        CrtStation crtStation = new CrtStation();
        String result = crtStation.toString();
        String expected = "The criteria : Minimal Station Number";
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("CrtTime Class test - findRoute Method")
    public void test_findRoute_time() {
        CrtTime crtTime = new CrtTime();
        ArrayList<Integer> route = crtTime.findRoute(64, 160);
        Integer[] expected = {64, 65, 66, 67, 68, 136, 288, 108, 147, 160};
        assertArrayEquals(expected, route.toArray(new Integer[0]));
    }

    @Test
    @DisplayName("CrtStation Class test - toString Method")
    public void test_toString() {
        CrtTime crtTime = new CrtTime();
        String result = crtTime.toString();
        String expected = "The criteria : Minimal Travelling Time";
        assertEquals(expected, result);
    }
}
