package com.luxoft.cjp.calculator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

import org.junit.*;

/**
 * Created by YPrivezentsev on 2016-04-
 * 27.
 */

public class CalculatorTest {
    private Calculator calculatorEngine = new Calculator();

    @BeforeClass
    public static void initAll(){}
    @AfterClass
  public void tearDownAll(){}

    @Before
    public void init(){}
    @After
    public void tearDown(){}

    @Test
    public void testSubtraction(){
        int difference = calculatorEngine.difference(42, 18);
        assertThat(difference, equalTo(24));
        assertThat(difference, not(4));
    }

    @Test
    public void testDivision () throws DivisionByZero {
        assertThat(calculatorEngine.divide(5,2),equalTo(2));
    }

    @Test(expected = DivisionByZero.class)
    public void testDivisionException () throws DivisionByZero {
        calculatorEngine.divide(5,0);
    }

//    @Test
    @Test(timeout = 20)
    public void testDuration(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted\r\n" + e);
        }
    }

    @Test
    public void testOneCall(){
        calculatorEngine.sum(1);
        assertThat(calculatorEngine.getCalls(), equalTo(1));
    }
}
