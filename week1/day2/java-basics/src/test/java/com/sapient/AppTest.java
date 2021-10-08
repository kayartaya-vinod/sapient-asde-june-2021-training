package com.sapient;

import static org.junit.Assert.assertEquals;

import com.sapient.programs.App;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test.
     */

    @Test
    public void testFactorialForNumbersBelowTen() {
        App app = new App();
        long expected = 120;
        long actual = app.factorial(5);
        assertEquals(expected, actual);
    }
}
