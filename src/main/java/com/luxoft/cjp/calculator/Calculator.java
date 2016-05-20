package com.luxoft.cjp.calculator;

/**
 * Created by lucky on 26.04.16.
 */
public class Calculator {
    private int calls = 0;

    public int sum(int... var) {
        calls++;
        int sum = 0;
        for (int i = 0; i < var.length; i++) {
            sum += var[i];
        }
        return sum;
    }


    public int difference (int minuend, int subtrahend){
        calls++;
        return minuend - subtrahend;
    }

    public int divide (int dividend, int divisor) throws DivisionByZero{
        calls++;
        if(divisor == 0){
            throw new DivisionByZero("Argument cannot be zero");
        }
        return dividend/divisor;
    }

    public int getCalls() {
        return calls;
    }
}
