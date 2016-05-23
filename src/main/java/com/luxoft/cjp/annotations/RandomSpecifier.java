package com.luxoft.cjp.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by lucky on 23.05.16.
 */
public class RandomSpecifier {
    public static void main(String[] args) throws IllegalAccessException {
        RandomValues randomValues = new RandomValues();
        serviceAnnotation(randomValues);
        System.out.println("randomValues = " + randomValues);
    }

    private static void serviceAnnotation(RandomValues randomValues) throws IllegalAccessException {
        Field[] fields = randomValues.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println("field = " + field);
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println("annotation = " + annotation);
                if(annotation.annotationType() == Random.class){
                    int randomValue = new java.util.Random(((Random) annotation).value()).nextInt();
                    field.setAccessible(true);
                    field.set(randomValues,randomValue);
                }
            }
        }
    }
}
