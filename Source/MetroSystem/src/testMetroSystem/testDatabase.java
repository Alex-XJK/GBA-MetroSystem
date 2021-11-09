package testMetroSystem;

import metroSystem.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class testDatabase {
    private MetroSystem m;
    private Database db;

    @BeforeEach
    public void setUp() {
        m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.English);
        db = m.getDatabase();
    }

    @Test
    @DisplayName("Database Class test - getInstance Method")
    public void test_getInstance() {
        assertNotNull(Database.getInstance());
    }

    @Test
    @DisplayName("Database Class test - getLineByName Method 1")
    public void test_getLineByName_1() throws ExLineNotFound {
        Line line = db.getLineByName("港岛线", Language.SimplifiedChinese);
        assertNotNull(line);
    }

    @Test
    @DisplayName("Database Class test - getLineByName Method 2")
    public void test_getLineByName_2() throws ExLineNotFound {
        Line line = db.getLineByName("Island Line", Language.English);
        assertNotNull(line);
    }

    @Test
    @DisplayName("Database Class test - getLineByName Method 3")
    public void test_getLineByName_3() throws ExLineNotFound {
        Line line = db.getLineByName("港島線", Language.TraditionalChinese);
        assertNotNull(line);
    }

    @Test
    @DisplayName("Database Class test - getLineByName Method 4")
    public void test_getLineByName_4() {
        ExLineNotFound thrown = assertThrows(
                ExLineNotFound.class,
                () -> db.getLineByName("Island Lin", Language.English),
                "The line you look for does not exist."
        );
    }

    @Test
    @DisplayName("Database Class test - getStationByName Method 1")
    public void test_getStationByName_1() throws ExStationNotFound {
        Station station = db.getStationByName("Tuen Mun", Language.English, AdministratorHK.getInstance());
        assertNotNull(station);
    }

    @Test
    @DisplayName("Database Class test - getStationByName Method 2")
    public void test_getStationByName_2() throws ExStationNotFound {
        Station station = db.getStationByName("屯门", Language.SimplifiedChinese, AdministratorHK.getInstance());
        assertNotNull(station);
    }

    @Test
    @DisplayName("Database Class test - getStationByName Method 3")
    public void test_getStationByName_3() throws ExStationNotFound {
        Station station = db.getStationByName("屯門", Language.TraditionalChinese, AdministratorHK.getInstance());
        assertNotNull(station);
    }

    @Test
    @DisplayName("Database Class test - getStationByName Method 4 - exception handling")
    public void test_getStationByName_4() {
        ExStationNotFound thrown = assertThrows(
                ExStationNotFound.class,
                () -> db.getStationByName("Tuen Mu", Language.English, AdministratorHK.getInstance()),
                "Station you look for does not exist."
        );
        assertTrue(thrown.getMessage().contains("Tuen Mu"));
    }

    @Test
    @DisplayName("Database Class test - getStationById Method 1")
    public void test_getStationById_1() throws ExStationNotFound {
        Station station = db.getStationById(64);
        assertNotNull(station);
    }

    @Test
    @DisplayName("Database Class test - getStationById Method 2 - exception handling")
    public void test_getStationById_2() {
        ExStationNotFound thrown = assertThrows(
                ExStationNotFound.class,
                () -> db.getStationById(-1),
                "Station you look for does not exist."
        );
        assertTrue(thrown.getMessage().contains("-1"));
    }

    @Test
    @DisplayName("Database Class test - translateId2Name Method")
    public void test_translateId2Name() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ArrayList<String> names = db.translateId2Name(ids);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Kennedy Town");
        expected.add("HKU");
        assertEquals(expected, names);
    }

    @Test
    @DisplayName("Database Class test - getPrice Method 1")
    public void test_getPrice_1() {
        float price = db.getPrice(1, 2, AdministratorHK.getInstance());
        assertEquals(5, price);
    }

    @Test
    @DisplayName("Database Class test - getPrice Method 2")
    public void test_getPrice_2() {
        float price = db.getPrice(98, 99, AdministratorSZ.getInstance());
        assertEquals(2, price);
    }

    @Test
    @DisplayName("Database Class test - getPrice Method 3")
    public void test_getPrice_3() {
        float price = db.getPrice(1, 2, null);
        assertEquals(-1 , price);
    }


}
