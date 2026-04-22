package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(double value1, Length.LengthUnit unit1,
                                                      double value2, Length.LengthUnit unit2) {
        Length l1 = new Length(value1, unit1);
        Length l2 = new Length(value2, unit2);
        return demonstrateLengthEquality(l1, l2);
    }

    public static Length demonstrateLengthConversion(double value,
                                                     Length.LengthUnit fromUnit,
                                                     Length.LengthUnit toUnit) {
        double converted = Length.convert(value, fromUnit, toUnit);
        return new Length(converted, toUnit);
    }

    public static Length demonstrateLengthConversion(Length length,
                                                     Length.LengthUnit toUnit) {
        return length.convertTo(toUnit);
    }

    public static void main(String[] args) {

        System.out.println(demonstrateLengthComparison(1.0, Length.LengthUnit.FEET,
                12.0, Length.LengthUnit.INCHES));

        System.out.println(demonstrateLengthComparison(1.0, Length.LengthUnit.YARDS,
                3.0, Length.LengthUnit.FEET));

        Length converted = demonstrateLengthConversion(3.0,
                Length.LengthUnit.FEET,
                Length.LengthUnit.INCHES);

        System.out.println(converted);

        Length yards = new Length(2.0, Length.LengthUnit.YARDS);
        System.out.println(demonstrateLengthConversion(yards, Length.LengthUnit.INCHES));
    }
}