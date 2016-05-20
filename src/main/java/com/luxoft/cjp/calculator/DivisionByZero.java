package com.luxoft.cjp.calculator;

/**
 * Created by YPrivezentsev on 2016-04-27.
 */
public class DivisionByZero extends Exception{
    public DivisionByZero(String message, Throwable cause) {
        super(message, cause);
    }

    public DivisionByZero(Throwable cause) {
        super(cause);
    }

    public DivisionByZero(String message) {

        super(message);
    }
}
