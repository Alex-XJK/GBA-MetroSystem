package testMetroSystem;

import metroSystem.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <h2>Junit test for {@code Administrator} related functions.</h2>
 * <p>
 *     This test case runs three tests on three different types of {@link metroSystem.Administrator},
 *     e.g., {@link metroSystem.AdministratorHK}, {@link metroSystem.AdministratorSZ} and {@link metroSystem.AdministratorBorder}.
 *     With testing strategy designed by <i>Chuwei</i> and implemented by <i>Alex</i>.
 *     <br>
 *     <ul>
 *         Mainly test their basic function {@code getInstance()}, and whether the returned result is:
 *         <li>successfully returned</li>
 *         <li>an object of class {@code Administrator}</li>
 *         <li>an specified object of its desired type</li>
 *     </ul>
 *     <b>
 *         Passed all the 9 test cases on Nov. 8.
 *     </b>
 * </p>
 * <hr>
 * @author Alex_XU
 * @author Chuwei
 * @since Nov. 8, 2021
 * @version 1.0
 */
public class testAdministrator {
    @Test
    @DisplayName("test_getInstance HK - isGet")
    public void test_getInstanceHK1() {
        Object adm;
        adm = AdministratorHK.getInstance();
        boolean result = adm == null;
        assertFalse(result);
    }

    @Test
    @DisplayName("test_getInstance SZ - isGet")
    public void test_getInstanceSZ1() {
        Object adm;
        adm = AdministratorSZ.getInstance();
        boolean result = adm == null;
        assertFalse(result);
    }

    @Test
    @DisplayName("test_getInstance Border - isGet")
    public void test_getInstanceBorder1() {
        Object adm;
        adm = AdministratorBorder.getInstance();
        boolean result = adm == null;
        assertFalse(result);
    }

    @Test
    @DisplayName("test_getInstance HK - isAdmin")
    public void test_getInstanceHK2() {
        Administrator adm = AdministratorHK.getInstance();
        boolean result = adm instanceof Administrator;
        assertTrue(result);
    }

    @Test
    @DisplayName("test_getInstance SZ - isAdmin")
    public void test_getInstanceSZ2() {
        Administrator adm = AdministratorSZ.getInstance();
        boolean result = adm instanceof Administrator;
        assertTrue(result);
    }

    @Test
    @DisplayName("test_getInstance Border - isAdmin")
    public void test_getInstanceBorder2() {
        Administrator adm = AdministratorBorder.getInstance();
        boolean result = adm instanceof Administrator;
        assertTrue(result);
    }

    @Test
    @DisplayName("test_getInstance HK - isItself")
    public void test_getInstanceHK3() {
        Administrator adm = AdministratorHK.getInstance();
        boolean result = adm instanceof AdministratorHK;
        assertTrue(result);
    }

    @Test
    @DisplayName("test_getInstance SZ - isItself")
    public void test_getInstanceSZ3() {
        Administrator adm = AdministratorSZ.getInstance();
        boolean result = adm instanceof AdministratorSZ;
        assertTrue(result);
    }

    @Test
    @DisplayName("test_getInstance Border - isItself")
    public void test_getInstanceBorder3() {
        Administrator adm = AdministratorBorder.getInstance();
        boolean result = adm instanceof AdministratorBorder;
        assertTrue(result);
    }
}
