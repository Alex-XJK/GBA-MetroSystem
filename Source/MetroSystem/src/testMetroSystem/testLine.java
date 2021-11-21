package testMetroSystem;

import metroSystem.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class testLine {
    private MetroSystem m;
    private Database db;

    @BeforeEach
    public void setUp() {
        m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.English);
        db = m.getDatabase();
    }

    @Test
    @DisplayName("Line Class test - getName Method 1")
    public void test_getName_1() throws ExLineNotFound {
        Line line = m.getLineByName("Island Line", Language.English);
        String result = line.getName();
        assertEquals("Island Line", result);
    }

    @Test
    @DisplayName("Line Class test - getName Method 2")
    public void test_getName_2() throws ExLineNotFound {
        m.setSystemLanguage(Language.SimplifiedChinese);
        Line line = m.getLineByName("港岛线", Language.SimplifiedChinese);
        String result = line.getName();
        assertEquals("港岛线", result);
    }

    @Test
    @DisplayName("Line Class test - getName Method 3")
    public void test_getName_3() throws ExLineNotFound {
        m.setSystemLanguage(Language.TraditionalChinese);
        Line line = m.getLineByName("港島線", Language.TraditionalChinese);
        String result = line.getName();
        assertEquals("港島線", result);
    }

    @Test
    @DisplayName("Line Class test - getName Method 4")
    public void test_getName_4() throws ExLineNotFound {
        m.setSystemLanguage(null);
        Line line = m.getLineByName("Island Line", Language.English);
        String result = line.getName();
        assertEquals("Error", result);
    }

    @Test
    @DisplayName("Line Class test - getNameInSpecificLanguage Method 1")
    public void test_getNameInSpecificLanguage_1() throws ExLineNotFound {
        Line line = m.getLineByName("Island Line", Language.English);
        String result = line.getNameInSpecificLanguage(Language.English);
        assertEquals("Island Line", result);
    }

    @Test
    @DisplayName("Line Class test - getNameInSpecificLanguage Method 2")
    public void test_getNameInSpecificLanguage_2() throws ExLineNotFound {
        Line line = m.getLineByName("港岛线", Language.SimplifiedChinese);
        String result = line.getNameInSpecificLanguage(Language.SimplifiedChinese);
        assertEquals("港岛线", result);
    }

    @Test
    @DisplayName("Line Class test - getNameInSpecificLanguage Method 3")
    public void test_getNameInSpecificLanguage_3() throws ExLineNotFound {
        Line line = m.getLineByName("港島線", Language.TraditionalChinese);
        String result = line.getNameInSpecificLanguage(Language.TraditionalChinese);
        assertEquals("港島線", result);
    }

    @Test
    @DisplayName("Line Class test - getNameInSpecificLanguage Method 4")
    public void test_getNameInSpecificLanguage_4() throws ExLineNotFound {
        Line line = m.getLineByName("Island Line", Language.English);
        String result = line.getNameInSpecificLanguage(null);
        assertEquals("Error", result);
    }

    @Test
    @DisplayName("Line Class test - getEdges Method")
    public void test_getEdges() throws ExLineNotFound {
        Line line = m.getLineByName("Island Line", Language.English);
        ArrayList<Edge> result = line.getEdges();
        String expected = "[Kennedy Town->HKU, HKU->Kennedy Town, HKU->Sai Ying Pun, Sai Ying Pun->HKU, Sai Ying Pun->Sheung Wan, Sheung Wan->Sai Ying Pun, Sheung Wan->Central, Central->Sheung Wan, Central->Admiralty, Admiralty->Central, Admiralty->Wan Chai, Wan Chai->Admiralty, Wan Chai->Causeway Bay, Causeway Bay->Wan Chai, Causeway Bay->Tin Hau, Tin Hau->Causeway Bay, Tin Hau->Fortress Hill, Fortress Hill->Tin Hau, Fortress Hill->North Point, North Point->Fortress Hill, North Point->Quarry Bay, Quarry Bay->North Point, Quarry Bay->Tai Koo, Tai Koo->Quarry Bay, Tai Koo->Sai Wan Ho, Sai Wan Ho->Tai Koo, Sai Wan Ho->Shau Kei Wan, Shau Kei Wan->Sai Wan Ho, Shau Kei Wan->Heng Fa Chuen, Heng Fa Chuen->Shau Kei Wan, Heng Fa Chuen->Chai Wan, Chai Wan->Heng Fa Chuen]";
        assertEquals(expected, result.toString());
    }
}
