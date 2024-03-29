package testMetroSystem;

import metroSystem.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * <h2>Junit test for {@code Station} related functions.</h2>
 * <p>
 *     This test case runs all the public functions within {@link metroSystem.Station}.
 *     With testing strategy designed by <i>Chuwei</i> and implemented by <i>Alex & Chuwei</i>.
 *     <br>
 *     <b>
 *         Passed all the 18 test cases on Nov. 8.
 *     </b>
 * </p>
 * <hr>
 * @author Alex_XU
 * @author Chuwei
 * @since Nov. 8, 2021
 * @version 1.0
 */
public class testStation {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    ArrayList<Station> allstation = null;
    Station s1 = null;
    Station s2 = null;
    Station s3 = null;

    @BeforeAll
    static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @BeforeEach
    public void setUp() {
        allstation = new ArrayList<>();
        s1 = new Station(1, "ABC", "壹貳叄", "一二三", AdministratorHK.getInstance());
        s2 = new Station(2, "XYZ", "捌玖拾", "八九十", AdministratorHK.getInstance());
        s3 = new Station(3, "XYZ", "玖拾貳", "九十二", AdministratorSZ.getInstance());
        allstation.add(s1);
        allstation.add(s2);
        allstation.add(s3);
    }

    @AfterAll
    static void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    @DisplayName("test_getName eng1")
    public void test_getName1() {
        String name = s1.getNameInSpecificLanguage(Language.English);
        assertEquals("ABC", name);
    }

    @Test
    @DisplayName("test_getName eng2")
    public void test_getName2() {
        String name = s2.getNameInSpecificLanguage(Language.English);
        assertEquals("XYZ", name);
    }

    @Test
    @DisplayName("test_getName tc1")
    public void test_getName3() {
        String name = s1.getNameInSpecificLanguage(Language.TraditionalChinese);
        assertEquals("壹貳叄", name);
    }

    @Test
    @DisplayName("test_getName tc2")
    public void test_getName4() {
        String name = s2.getNameInSpecificLanguage(Language.TraditionalChinese);
        assertEquals("捌玖拾", name);
    }

    @Test
    @DisplayName("test_getName sc1")
    public void test_getName5() {
        String name = s1.getNameInSpecificLanguage(Language.SimplifiedChinese);
        assertEquals("一二三", name);
    }

    @Test
    @DisplayName("test_getName sc2")
    public void test_getName6() {
        String name = s2.getNameInSpecificLanguage(Language.SimplifiedChinese);
        assertEquals("八九十", name);
    }

    @Test
    @DisplayName("test_getName error")
    public void test_getName7() {
        String name = s2.getNameInSpecificLanguage(null);
        assertEquals("Error", name);
    }

    @Test
    @DisplayName("test_getId 1")
    public void test_getId1() {
        int id = s1.getId();
        assertEquals(1, id);
    }

    @Test
    @DisplayName("test_getId 2")
    public void test_getId2() {
        int id = s2.getId();
        assertEquals(2, id);
    }

    @Test
    @DisplayName("test_EdgeTo")
    public void test_allEdgeTo() {
        Edge e1 = new Edge(1, s1, s2, 30, AdministratorHK.getInstance());
        Edge e2 = new Edge(2, s2, s1, 60, AdministratorHK.getInstance());
        int length = s1.getEdgeTo().size();
        assertEquals(1, length);
    }

    @Test
    @DisplayName("test_searchStationByName 1")
    public void test_searchStationByName1() {
        Station s = Station.searchStationByName(allstation, "ABC", Language.English, AdministratorHK.getInstance());
        int id = s.getId();
        assertEquals(1, id);
    }

    @Test
    @DisplayName("test_searchStationByName 2")
    public void test_searchStationByName2() {
        Station s = Station.searchStationByName(allstation, "八九十", Language.SimplifiedChinese, AdministratorHK.getInstance());
        int id = s.getId();
        assertEquals(2, id);
    }

    @Test
    @DisplayName("test_searchStationByName Not Found")
    public void test_searchStationByName3() {
        Station s = Station.searchStationByName(allstation, "Hello World", Language.English, AdministratorHK.getInstance());
        assertNull(s);
    }

    @Test
    @DisplayName("test_searchStationByName Same Name")
    public void test_searchStationByName4() {
        Station s = Station.searchStationByName(allstation, "XYZ", Language.English, AdministratorSZ.getInstance());
        int id = s.getId();
        assertEquals(3, id);
    }

    @Test
    @DisplayName("test_searchStationById 1")
    public void test_searchStationById1() {
        Station s = Station.searchStationById(allstation,1);
        int id = s.getId();
        assertEquals(1, id);
    }

    @Test
    @DisplayName("test_searchStationById 2")
    public void test_searchStationById2() {
        Station s = Station.searchStationById(allstation,2);
        int id = s.getId();
        assertEquals(2, id);
    }

    @Test
    @DisplayName("test_searchStationById Not Found")
    public void test_searchStationById3() {
        Station s = Station.searchStationById(allstation,9);
        assertNull(s);
    }

    @Test
    @DisplayName("test_getAdmin")
    public void test_getAdmin1() {
        Administrator admin = s1.getAdmin();
        boolean result = admin instanceof AdministratorHK;
        assertTrue(result);
    }

    @Test
    @DisplayName("test_getName i")
    public void test_getNameS1() {
        MetroSystem sys = MetroSystem.getInstance();
        sys.setSystemLanguage(Language.English);
        String name = s1.getName();
        assertEquals("ABC", name);
    }

    @Test
    @DisplayName("test_getName ii")
    public void test_getNameS2() {
        MetroSystem sys = MetroSystem.getInstance();
        sys.setSystemLanguage(Language.SimplifiedChinese);
        String name = s1.getName();
        assertEquals("一二三", name);
    }

    @Test
    @DisplayName("test_getName iii")
    public void test_getNameS3() {
        MetroSystem sys = MetroSystem.getInstance();
        sys.setSystemLanguage(Language.TraditionalChinese);
        String name = s1.getName();
        assertEquals("壹貳叄", name);
    }

    @Test
    @DisplayName("test_getName iv")
    public void test_getNameS4() {
        MetroSystem sys = MetroSystem.getInstance();
        sys.setSystemLanguage(null);
        String name = s1.getName();
        assertEquals("Error", name);
    }

    @Test
    @DisplayName("test_getStationPrice-HK2-Eng")
    public void test_getStationPrice0() throws Exception {

        MetroSystem m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.English);

        setOutput();
        Station startStation, endStation;
        startStation = m.getStationByName("Tuen Mun", Language.English, AdministratorHK.getInstance());
        endStation = m.getStationByName("Lo Wu", Language.English, AdministratorHK.getInstance());
        Station.getStationPrice(startStation, endStation);
        String actual = getOutput();

        setOutput();
        System.out.println("Tuen Mun->Lo Wu: 59.5");
        String expected = getOutput();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test_getStationPrice-HKSZ-eng")
    public void test_getStationPrice1() throws Exception {

        MetroSystem m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.English);

        setOutput();
        Station startStation, endStation;
        startStation = m.getStationByName("Laojie", Language.English, AdministratorSZ.getInstance());
        endStation = m.getStationByName("Lo Wu", Language.English, AdministratorHK.getInstance());
        Station.getStationPrice(startStation, endStation);
        String actual = getOutput();

        setOutput();
        System.out.println("The two stations belong to different administrations, and the cross-segment calculation should be carried out according to the stations through the route");
        String expected = getOutput();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test_getStationPrice-HKSZ-zhhk")
    public void test_getStationPrice2() throws Exception {

        MetroSystem m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.TraditionalChinese);

        setOutput();
        Station startStation, endStation;
        startStation = m.getStationByName("香梅北", Language.TraditionalChinese, AdministratorSZ.getInstance());
        endStation = m.getStationByName("顯徑", Language.TraditionalChinese, AdministratorHK.getInstance());
        Station.getStationPrice(startStation, endStation);
        String actual = getOutput();

        setOutput();
        System.out.println("兩站屬於不同管轄範圍，需根據路線經過站進行跨段計算");
        String expected = getOutput();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test_getStationPrice-HKSZ-zhcn")
    public void test_getStationPrice3() throws Exception {

        MetroSystem m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.SimplifiedChinese);

        setOutput();
        Station startStation, endStation;
        startStation = m.getStationByName("深大", Language.SimplifiedChinese, AdministratorSZ.getInstance());
        endStation = m.getStationByName("尖东", Language.SimplifiedChinese, AdministratorHK.getInstance());
        Station.getStationPrice(startStation, endStation);
        String actual = getOutput();

        setOutput();
        System.out.println("两站属于不同管辖范围，需根据路线经过站进行跨段计算");
        String expected = getOutput();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test_ExStationNotFound")
    public void test_stationException() {
        boolean isThrow = false;
        try {
            throw new ExStationNotFound();
        }
        catch (ExStationNotFound e) {
            isThrow = true;
        }
        finally {
            assertTrue(isThrow);
        }
    }

    PrintStream oldPrintStream;
    ByteArrayOutputStream bos;

    private void setOutput() throws Exception {
        oldPrintStream = System.out;
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
    }

    private String getOutput() { // throws Exception
        System.setOut(oldPrintStream);
        return bos.toString();
    }

}
