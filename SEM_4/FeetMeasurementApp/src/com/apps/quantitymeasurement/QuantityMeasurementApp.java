package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static void main(String[] args) {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        System.out.println(l1.equals(l2));
    }
}