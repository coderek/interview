package me.derekzeng.code;

import java.io.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void regex() {
        FourSum fs = new FourSum();
//         assertTrue(fs.fourSum(new int[]{1,1,2,2,2,2,2,2,2,3,3}, 8) == null);
        assertTrue(fs.fourSum(new int[]{0,4,-5,2,-2,4,2,-1,4}, 12) == null);
    }
}
