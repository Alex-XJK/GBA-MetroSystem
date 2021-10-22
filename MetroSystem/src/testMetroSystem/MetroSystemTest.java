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
        // It seems that this can work, but how to assert arrayList easier?
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(64);
        expected.add(65);
        expected.add(66);
        expected.add(67);
        expected.add(68);
        expected.add(136);
        expected.add(288);
        expected.add(108);
        expected.add(147);
        expected.add(160);
        assertArrayEquals(expected.toArray(), result.toArray());
    }

}

