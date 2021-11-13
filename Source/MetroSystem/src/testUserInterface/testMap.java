package testUserInterface;

import org.junit.jupiter.api.AfterEach;
import userInterface.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class testMap {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setStreams() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @AfterEach
    public void restoreInitialStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    @DisplayName("Map Class test - Map Contructor test - 1")
    public void test_map_constructor_1()
    {
        Map mtr_en_hk = new Map("en", "HK");
        assertEquals("HK", mtr_en_hk.getArea());
        assertEquals("en", mtr_en_hk.getLan());
        assertEquals(97, mtr_en_hk.getButtonNumber());
        assertEquals(97, mtr_en_hk.getLabelNumber());
    }

    @Test
    @DisplayName("Map Class test - Map Constructor test - 2")
    public void test_map_constructor_2() {
        Map mtr_en_sz = new Map("en", "SZ");
        assertEquals("SZ", mtr_en_sz.getArea());
        assertEquals("en", mtr_en_sz.getLan());
        assertEquals(237, mtr_en_sz.getButtonNumber());
        assertEquals(237, mtr_en_sz.getLabelNumber());
    }

    @Test
    @DisplayName("Map Class test - Map Constructor test - 3")
    public void test_map_constructor_3() {
        Map mtr_hk_hk = new Map("hk", "HK");
        assertEquals("HK", mtr_hk_hk.getArea());
        assertEquals("hk", mtr_hk_hk.getLan());
        assertEquals(97, mtr_hk_hk.getButtonNumber());
        assertEquals(97, mtr_hk_hk.getLabelNumber());
    }

    @Test
    @DisplayName("Map Class test - Map Constructor test - 4")
    public void test_map_constructor_4() {
        Map mtr_hk_sz = new Map("hk", "SZ");
        assertEquals("SZ", mtr_hk_sz.getArea());
        assertEquals("hk", mtr_hk_sz.getLan());
        assertEquals(237, mtr_hk_sz.getButtonNumber());
        assertEquals(237, mtr_hk_sz.getLabelNumber());
    }

    @Test
    @DisplayName("Map Class test - Map Contructor test - 5")
    public void test_map_constructor_5()
    {
        Map mtr_ch_hk = new Map("ch", "HK");
        assertEquals("HK", mtr_ch_hk.getArea());
        assertEquals("ch", mtr_ch_hk.getLan());
        assertEquals(97, mtr_ch_hk.getButtonNumber());
        assertEquals(97, mtr_ch_hk.getLabelNumber());
    }

    @Test
    @DisplayName("Map Class test - Map Constructor test - 6")
    public void test_map_constructor_6() {
        Map mtr_ch_sz = new Map("ch", "SZ");
        assertEquals("SZ", mtr_ch_sz.getArea());
        assertEquals("ch", mtr_ch_sz.getLan());
        assertEquals(237, mtr_ch_sz.getButtonNumber());
        assertEquals(237, mtr_ch_sz.getLabelNumber());
    }

    @Test
    @DisplayName("Map Class test - linkTextFieldComponent Method test - 1")
    public void test_map_linkTextFieldComponent() {
        Map testMap = new Map("en", "HK");
        JTextField from = new JTextField();
        JTextField to = new JTextField();
        testMap.linkTextFieldComponent(from, to);
        assertEquals(from, testMap.getFrom_station());
        assertEquals(to, testMap.getTo_station());
    }

    @Test
    @DisplayName("Map Class test - actionPerformed Method Test - 1")
    public void test_actionPerformed_1() {
        class stubActionEvent extends ActionEvent {
            public stubActionEvent(Object source, int id, String command) {
                super(source, id, command);
            }
            @Override
            public Object getSource() {
                JButton testButton = new JButton();
                testButton.setName("test msg");
                return testButton;
            }
        }
        Map testMap = new Map("en", "HK");
        JTextField from = new JTextField();
        JTextField to = new JTextField();
        testMap.linkTextFieldComponent(from, to);

        testMap.actionPerformed(new stubActionEvent(new Object(), 0, "click"));
        assertEquals("test msg", testMap.getFrom_station().getText());
    }

    @Test
    @DisplayName("Map Class test - actionPerformed Method Test - 1")
    public void test_actionPerformed_2() {
        class stubActionEvent extends ActionEvent {
            public stubActionEvent(Object source, int id, String command) {
                super(source, id, command);
            }
            @Override
            public Object getSource() {
                JButton testButton = new JButton();
                testButton.setName("test msg");
                return testButton;
            }
        }
        Map testMap = new Map("en", "HK");
        JTextField from = new JTextField("some text");
        JTextField to = new JTextField();
        testMap.linkTextFieldComponent(from, to);

        testMap.actionPerformed(new stubActionEvent(new Object(), 0, "click"));
        assertEquals(testMap.getTo_station().getText(), "test msg");
    }

    /*@Test
    @DisplayName("Map Class test - paint Method Test")
    public void test_paint() {
        JFrame win = new JFrame();
        Map testMap = new Map("en", "HK");
        win.add(testMap);
        assertEquals("successfully painting!", out.toString());
    }*/

    @Test
    @DisplayName("Map Class test - showResult Method Test")
    public void test_showResult() {
        JFrame win = new JFrame();
        Map testMap = new Map("en", "HK");
        win.add(testMap);
        win.setBounds(300, 300, 600, 300);
        win.setVisible(true);
        ArrayList<Integer> testResult = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            testResult.add(i);
        }
        String responseMsg = testMap.showResult(testResult);

        new Timer(1, (e) -> { win.setVisible(false); win.dispose(); }).start();
        assertEquals("successfully showing the result!", responseMsg);
    }
}
