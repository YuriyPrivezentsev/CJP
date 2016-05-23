package com.luxoft.cjp.annotations;

import com.sun.istack.internal.NotNull;

import javax.inject.Inject;

/**
 * Created by lucky on 23.05.16.
 */
public class RandomValues {
    @Random
    private int value;

    @Override
    public String toString() {
        return "RandomValues{" +
                "value=" + value +
                '}';
    }
}
