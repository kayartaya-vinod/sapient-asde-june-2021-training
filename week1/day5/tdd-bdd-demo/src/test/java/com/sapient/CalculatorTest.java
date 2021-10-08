package com.sapient;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CalculatorTest {

    Calculator calc;

    @Before
    public void setup() {
        calc = new Calculator();
    }

    @Test
    public void testSumOfTwoNumbers() {
        int expected = 30;
        int actual = calc.sum("10,20");
        assertEquals(expected, actual);
    }

    @Test
    public void testSumOfOneNumber() {
        int expected = 30;
        int actual = calc.sum("30");
        assertEquals(expected, actual);
    }

    @Test
    public void testSumOfZeroNumbers() {
        int expected = 0;
        int actual = calc.sum("");
        assertEquals(expected, actual);
    }

}
