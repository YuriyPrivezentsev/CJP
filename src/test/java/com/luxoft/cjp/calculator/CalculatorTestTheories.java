package com.luxoft.cjp.calculator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * Created by lucky on 26.04.16.
 */
@RunWith(Theories.class)
public class CalculatorTestTheories {
    private Calculator calculator = new Calculator();

    @DataPoints
    public static int[] integerPoints = {1, 2, 18, 42, 0};

    @Theory
    public void testForTwoNumbers(Integer a, Integer b) {
        assertThat(calculator.sum(a, b), equalTo(a + b));
    }

    @Theory
    public void testForDivision(Integer a, Integer b) throws DivisionByZero {
        assertThat(calculator.divide(a, b), equalTo(a / b));
    }


}
