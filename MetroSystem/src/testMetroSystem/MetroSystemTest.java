package MetroSystem.src.testMetroSystem;

import MetroSystem.src.metroSystem.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MetroSystemTest {
    private MetroSystem m;

    @BeforeEach
    public void setUp() throws Exception {
        m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.English);
    }

    @Test
    @DisplayName("Declare Criteria-Station")
    public void testCriteriaStaBasic1(){
        Criteria c = new CrtStation();
        System.out.println(c);
        c.reportGraph();
        // How to assert such a large item?
    }

    @Test
    @DisplayName("Declare Criteria-Time")
    public void testCriteriaTimBasic1(){
        Criteria c = new CrtTime();
        System.out.println(c);
        c.reportGraph();
        // How to assert such a large item?
    }

    @Test
    @DisplayName("Run CrtStation 1")
    public void testCriteriaStaRun1(){
        Criteria c = new CrtStation();
        Station startStation = m.getDatabase().getStationByName("Tuen Mun", Language.English, AdministratorHK.getInstance());
        Station endStation = m.getDatabase().getStationByName("Children's Palace", Language.English, AdministratorSZ.getInstance());
        ArrayList<Integer> result = c.findRoute(startStation.getId(), endStation.getId());
        Integer[] expected = {64, 65, 66, 67, 68, 136, 288, 108, 147, 160};
        assertArrayEquals(expected, result.toArray(new Integer[0]));
    }

    @Test
    @DisplayName("Run CrtStation 2")
    public void testCriteriaStaRun2(){
        Criteria c = new CrtStation();
        Station startStation = m.getDatabase().getStationByName("长沙湾", Language.SimplifiedChinese, AdministratorHK.getInstance());
        Station endStation = m.getDatabase().getStationByName("九龙塘", Language.SimplifiedChinese, AdministratorHK.getInstance());
        ArrayList<Integer> result = c.findRoute(startStation.getId(), endStation.getId());
        Integer[] expected = {24, 23, 22, 34, 35};
        assertArrayEquals(expected, result.toArray(new Integer[0]));
    }

    @Test
    @DisplayName("Price Retrieve 1")
    public void testPrice1(){
        Station startStation = m.getDatabase().getStationByName("Tuen Mun", Language.English, AdministratorHK.getInstance());
        Station endStation = m.getDatabase().getStationByName("Lo Wu", Language.English, AdministratorHK.getInstance());
        Station.getStationPrice(startStation, endStation);
        // Assertion of the stdout stream is ok but seems to be difficult,
        // maybe we can let it return a string instead of directly printout?
    }
}

