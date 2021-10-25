package MetroSystem.src.testMetroSystem;

import MetroSystem.src.metroSystem.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MetroSystemTest {
    private MetroSystem m;
    private Database db;

    @BeforeEach
    public void setUp() throws Exception {
        m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.English);
        db = m.getDatabase();
    }

    @Test
    @DisplayName("Declare Criteria-Station")
    public void testCriteriaStaBasic1() {
        Criteria c = new CrtStation();
        System.out.println(c);
        c.reportGraph();
        // How to assert such a large item?
    }

    @Test
    @DisplayName("Declare Criteria-Time")
    public void testCriteriaTimBasic1() {
        Criteria c = new CrtTime();
        System.out.println(c);
        c.reportGraph();
        // How to assert such a large item?
    }

    @Test
    @DisplayName("Searching Station Eng")
    public void testSearchStation1() throws ExStationNotFound {
        Station s = null;
        s = db.getStationByName("Tuen Mun", Language.English, AdministratorHK.getInstance());
        int id = s.getId();
        assertEquals(64, id);
    }

    @Test
    @DisplayName("Searching Station SC")
    public void testSearchStation2() throws ExStationNotFound {
        Station s = null;
        s = db.getStationByName("长沙湾", Language.SimplifiedChinese, AdministratorHK.getInstance());
        int id = s.getId();
        assertEquals(24, id);
    }

    @Test
    @DisplayName("Run CrtStation 1")
    public void testCriteriaStaRun1() throws ExStationNotFound {
        Criteria c = new CrtStation();
        ArrayList<Integer> result = new ArrayList<>();
        Station startStation = m.getDatabase().getStationByName("Tuen Mun", Language.English, AdministratorHK.getInstance());
        Station endStation = m.getDatabase().getStationByName("Children's Palace", Language.English, AdministratorSZ.getInstance());
        result = c.findRoute(startStation.getId(), endStation.getId());
        Integer[] expected = {64, 65, 66, 67, 68, 136, 288, 108, 147, 160};
        assertArrayEquals(expected, result.toArray(new Integer[0]));
    }

    @Test
    @DisplayName("Run CrtStation 2")
    public void testCriteriaStaRun2() throws ExStationNotFound {
        Criteria c = new CrtStation();
        ArrayList<Integer> result = new ArrayList<>();
        Station startStation = m.getDatabase().getStationByName("长沙湾", Language.SimplifiedChinese, AdministratorHK.getInstance());
        Station endStation = m.getDatabase().getStationByName("九龙塘", Language.SimplifiedChinese, AdministratorHK.getInstance());
        result = c.findRoute(startStation.getId(), endStation.getId());
        Integer[] expected = {24, 23, 22, 34, 35};
        assertArrayEquals(expected, result.toArray(new Integer[0]));
    }

    @Test
    @DisplayName("Price Retrieve 1")
    public void testPrice1() {
        // Assertion of the stdout stream is ok but seems to be difficult,
        // maybe we can let it return a string instead of directly printout?
    }
    
    @Test
    @DisplayName("Run CrtTime")
    public void testCriteriaTimeRun() throws ExStationNotFound {
    	Criteria c = new CrtTime();
        ArrayList<Integer> result = new ArrayList<>();
        Station startStation = m.getDatabase().getStationByName("Tuen Mun", Language.English, AdministratorHK.getInstance());
        Station endStation = m.getDatabase().getStationByName("Children's Palace", Language.English, AdministratorSZ.getInstance());
        result = c.findRoute(startStation.getId(), endStation.getId());
        Integer[] expected = {64, 65, 66, 67, 68, 136, 288, 108, 147, 160};
        assertArrayEquals(expected, result.toArray(new Integer[0]));
    }
    
    @Test
    @DisplayName("Station getName Method 1")
    public void testStaGetName1() throws ExStationNotFound {
    	String result = null;
        Station station = m.getDatabase().getStationByName("Tuen Mun", Language.English, AdministratorHK.getInstance());
        result = station.getName();
        assertEquals("Tuen Mun", result);
    }
    
    @Test
    @DisplayName("Station getName Method 2")
    public void testStaGetName2() throws ExStationNotFound {
    	String result = null;
        m.setSystemLanguage(Language.TraditionalChinese);
        Station station = m.getDatabase().getStationByName("屯門", Language.TraditionalChinese, AdministratorHK.getInstance());
        result = station.getName();
        assertEquals("屯門", result);
    }
    
    @Test
    @DisplayName("Station getName Method 3")
    public void testStaGetName3() throws ExStationNotFound {
    	String result = null;
        m.setSystemLanguage(Language.SimplifiedChinese);
        Station station = m.getDatabase().getStationByName("屯门", Language.SimplifiedChinese, AdministratorHK.getInstance());
        result = station.getName();
        assertEquals("屯门", result);
    }
    
    @Test
    @DisplayName("Station getName Method 4")
    public void testStaGetName4() throws ExStationNotFound {
    	String result = null;
        m.setSystemLanguage(null);
        Station station = m.getDatabase().getStationByName("屯门", Language.SimplifiedChinese, AdministratorHK.getInstance());
        result = station.getName();
        assertEquals("Error", result);
    }
    
    @Test
    @DisplayName("Line getName Method 1")
    public void testLineGetName1() throws ExLineNotFound {
    	String result = null;
        Line line = m.getDatabase().getLineByName("Island Line", Language.English);
        result = line.getName();
        assertEquals("Island Line", result);
    }
    
    @Test
    @DisplayName("Line getName Method 2")
    public void testLineGetName3() throws ExLineNotFound {
    	String result = null;
        m.setSystemLanguage(Language.SimplifiedChinese);
        Line line = m.getDatabase().getLineByName("港岛线", Language.SimplifiedChinese);
        result = line.getName();
        assertEquals("Island Line", result);
    }
    
    @Test
    @DisplayName("Line getName Method 3")
    public void testLineGetName4() throws ExLineNotFound {
    	String result = null;
        m.setSystemLanguage(Language.TraditionalChinese);
        Line line = m.getDatabase().getLineByName("港島線", Language.TraditionalChinese);
        result = line.getName();
        assertEquals("Island Line", result);
    }
    
    @Test
    @DisplayName("Line getName Method 4")
    public void testLineGetName2() throws ExLineNotFound {
    	String result = null;
        m.setSystemLanguage(null);
        Line line = m.getDatabase().getLineByName("Island Line", Language.English);
        result = line.getName();
        assertEquals("Error", result);
    }
    
    @Test
    @DisplayName("Line getEdges Method")
    public void testLineGetEdges() throws ExLineNotFound {
    	ArrayList<Edge> result = null;
        Line line = m.getDatabase().getLineByName("Island Line", Language.English);
        result = line.getEdges();String expected = "[Kennedy Town->HKU, HKU->Kennedy Town, HKU->Sai Ying Pun, Sai Ying Pun->HKU, Sai Ying Pun->Sheung Wan, Sheung Wan->Sai Ying Pun, Sheung Wan->Central, Central->Sheung Wan, Central->Admiralty, Admiralty->Central, Admiralty->Wan Chai, Wan Chai->Admiralty, Wan Chai->Causeway Bay, Causeway Bay->Wan Chai, Causeway Bay->Tin Hau, Tin Hau->Causeway Bay, Tin Hau->Fortress Hill, Fortress Hill->Tin Hau, Fortress Hill->North Point, North Point->Fortress Hill, North Point->Quarry Bay, Quarry Bay->North Point, Quarry Bay->Tai Koo, Tai Koo->Quarry Bay, Tai Koo->Sai Wan Ho, Sai Wan Ho->Tai Koo, Sai Wan Ho->Shau Kei Wan, Shau Kei Wan->Sai Wan Ho, Shau Kei Wan->Heng Fa Chuen, Heng Fa Chuen->Shau Kei Wan, Heng Fa Chuen->Chai Wan, Chai Wan->Heng Fa Chuen]";
        assertEquals(expected, result.toString());
    }
    
    @Test
    @DisplayName("ExLineNotFound")
    public void testExLineNotFound() {
    	try {
			Line line = m.getDatabase().getLineByName("Islan Line", Language.English);
		} catch (ExLineNotFound e) {
			assertEquals("The line you look for does not exist.", e.getMessage());
		}
    }
    
    @Test
    @DisplayName("ExLineNotFound")
    public void testExStationNotFound() {
    	try {
    		Station station = m.getDatabase().getStationByName("Tue Mun", Language.English, AdministratorHK.getInstance());
		} catch (ExStationNotFound e) {
			assertEquals("Station with name Tue Mun cannot be found in our database!", e.getMessage());
		}
    }
    
    @Test
    @DisplayName("Database translateId2Name method")
    public void testTranslateId2Name() {
    	ArrayList<Integer> ids = new ArrayList();
    	ids.add(1);
    	ArrayList<String> result = db.translateId2Name(ids);
    	assertEquals("[Kennedy Town]", result.toString());
    }
    
    @Test
    @DisplayName("Database getPrice method 1")
    public void getPrice1() {
    	float result = db.getPrice(0, 1, AdministratorHK.getInstance());
    	assertEquals(0.0, result);
    }
    
    @Test
    @DisplayName("Database getPrice method 2")
    public void getPrice2() {
    	float result = db.getPrice(0, 1, null);
    	assertEquals(-1, result);
    }
    
}

