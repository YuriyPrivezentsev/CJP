package com.luxoft.cjp.calculator;

/**
 * Created by lucky on 26.04.16.
 */
public class Calculator {
   public int sum(int ... var){
       int sum = 0;
       for (int i = 0; i < var.length; i++) {
           sum += var[i];
       }
       return sum;
   }
}
