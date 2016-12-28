package me.derekzeng.code;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    public void testApp() {
        Atoi a = new Atoi();
        assertTrue(a.myAtoi("123123") == 123123);
    }
}
