package testMetroSystem;
import metroSystem.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <h2>Junit test for {@code NodeEntry} related functions.</h2>
 * <p>
 *     This test case runs all the public functions within {@link metroSystem.NodeEntry}.
 *     With testing strategy designed by <i>Chuwei</i> and implemented by <i>Alex</i>.
 *     <br>
 *     JAVA-Generics is well noticed, thus testcases involved different data types.
 *     <br>
 *     <b>
 *         Passed all the 12 test cases on Nov. 8.
 *     </b>
 * </p>
 * <hr>
 * @author Alex_XU
 * @author Chuwei
 * @since Nov. 8, 2021
 * @version 1.0
 */
public class testNodeEntry {
    @Test
    @DisplayName("test_getKey Int")
    public void test_getKey1() {
        NodeEntry<Integer, Integer> node = new NodeEntry<Integer, Integer>(5, 20);
        int key = node.getKey();
        assertEquals(5, key);
    }

    @Test
    @DisplayName("test_getKey Float")
    public void test_getKey2() {
        NodeEntry<Float, Double> node = new NodeEntry<Float, Double>(3.5F, 100.0);
        float key = node.getKey();
        assertEquals(3.5, key, 0);
    }

    @Test
    @DisplayName("test_getKey Char")
    public void test_getKey3() {
        NodeEntry<Character, String> node = new NodeEntry<Character, String>('c', "Hello");
        char key = node.getKey();
        assertEquals('c', key);
    }

    @Test
    @DisplayName("test_getValue Int")
    public void test_getValue1() {
        NodeEntry<Integer, Integer> node = new NodeEntry<Integer, Integer>(5, 20);
        int value = node.getValue();
        assertEquals(20, value);
    }

    @Test
    @DisplayName("test_getValue Double")
    public void test_getValue2() {
        NodeEntry<Float, Double> node = new NodeEntry<Float, Double>(3.5F, 100.0);
        Double value = node.getValue();
        assertEquals(100.0, value, 0);
    }

    @Test
    @DisplayName("test_getValue String")
    public void test_getValue3() {
        NodeEntry<Character, String> node = new NodeEntry<Character, String>('c', "Hello");
        String value = node.getValue();
        assertEquals("Hello", value);
    }

    @Test
    @DisplayName("test_changeValue Int")
    public void test_changeValue1() {
        NodeEntry<Integer, Integer> node = new NodeEntry<Integer, Integer>(5, 20);
        node.changeValue(100);
        int value = node.getValue();
        assertEquals(100, value);
    }

    @Test
    @DisplayName("test_changeValue Double")
    public void test_changeValue2() {
        NodeEntry<Float, Double> node = new NodeEntry<Float, Double>(3.5F, 100.0);
        node.changeValue(-5.5);
        Double value = node.getValue();
        assertEquals(-5.5, value, 0);
    }

    @Test
    @DisplayName("test_changeValue String")
    public void test_changeValue3() {
        NodeEntry<Character, String> node = new NodeEntry<Character, String>('c', "Hello");
        node.changeValue("World");
        String value = node.getValue();
        assertEquals("World", value);
    }

    @Test
    @DisplayName("test_compareTo large")
    public void test_compareTo1() {
        NodeEntry<Integer, Integer> nodeA = new NodeEntry<Integer, Integer>(5, 25);
        NodeEntry<Integer, Integer> nodeB = new NodeEntry<Integer, Integer>(10, 100);
        boolean result = nodeA.compareTo(nodeB) < 0;
        assertTrue(result);
    }

    @Test
    @DisplayName("test_compareTo equal")
    public void test_compareTo2() {
        NodeEntry<Integer, Integer> nodeA = new NodeEntry<Integer, Integer>(5, 50);
        NodeEntry<Integer, Integer> nodeB = new NodeEntry<Integer, Integer>(10, 50);
        boolean result = nodeA.compareTo(nodeB) == 0;
        assertTrue(result);
    }

    @Test
    @DisplayName("test_compareTo small")
    public void test_compareTo3() {
        NodeEntry<Integer, Integer> nodeA = new NodeEntry<Integer, Integer>(5, 500);
        NodeEntry<Integer, Integer> nodeB = new NodeEntry<Integer, Integer>(10, 50);
        boolean result = nodeA.compareTo(nodeB) > 0;
        assertTrue(result);
    }
}
