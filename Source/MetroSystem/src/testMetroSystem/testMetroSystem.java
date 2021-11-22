package testMetroSystem;

import metroSystem.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class testMetroSystem {

    @Test
    @DisplayName("MetroSystem Class test - getInstance Method")
    public void test_getInstance() {
        MetroSystem m = MetroSystem.getInstance();
        assertNotNull(m);
    }

    @Test
    @DisplayName("MetroSystem Class test - setSystemLanguage & getSystemLanguage Methods")
    public void test_language_related() {
        MetroSystem m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.English);
        Language expected = m.getSystemLanguage();
        assertEquals(Language.English, expected);
    }

    @Test
    @DisplayName("MetroSystem Class test - getDatabase Method")
    public void test_getDatabase() {
        MetroSystem m = MetroSystem.getInstance();
        Database db = m.getDatabase();
        assertNotNull(db);
    }

    @Test
    @DisplayName("MetroSystem Class test - getStationByName Method 1")
    public void  test_getStationByName_1() {
        MetroSystem m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.English);
        Language language = m.getSystemLanguage();
        String name = "HKU";
        Station result = m.getStationByName(name, language, AdministratorHK.getInstance());
        assertNotNull(result);
    }

    @Test
    @DisplayName("MetroSystem Class test - getStationByName Method 2")
    public void  test_getStationByName_2() {
        MetroSystem m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.English);
        Language language = m.getSystemLanguage();
        String name = "HK";
        Station result = m.getStationByName(name, language, AdministratorHK.getInstance());
        assertNull(result);
    }

    @Test
    @DisplayName("MetroSystem Class test - getLineByName Method 1")
    public void  test_getLineByName_1() {
        MetroSystem m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.English);
        Language language = m.getSystemLanguage();
        String name = "Kwun Tong Line";
        Line result = m.getLineByName(name, language);
        assertNotNull(result);
    }

    @Test
    @DisplayName("MetroSystem Class test - getLineByName Method 2")
    public void  test_getLineByName_2() {
        MetroSystem m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.English);
        Language language = m.getSystemLanguage();
        String name = "Kwun Tong";
        Line result = m.getLineByName(name, language);
        assertNull(result);
    }
}
