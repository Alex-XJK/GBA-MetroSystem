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
}
