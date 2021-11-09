package testMetroSystem;

import metroSystem.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class testMain {
    private MetroSystem m;
    private Database db;

    @BeforeEach
    public void setUp() {
        m = MetroSystem.getInstance();
        m.setSystemLanguage(Language.English);
        db = m.getDatabase();
    }

    @Test
    @DisplayName("Integral test - Main.java")
    public void test_main() {
        Main.main(null);
    }

}
