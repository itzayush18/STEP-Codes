package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    public static Length demonstrateLengthConversion(double value,
                                                     LengthUnit from,
                                                     LengthUnit to) {
        double base = from.convertToBaseUnit(value);
        double result = to.convertFromBaseUnit(base);
        return new Length(result, to);
    }

    public static Length demonstrateLengthConversion(Length length,
                                                     LengthUnit toUnit) {
        return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        return l1.add(l2);
    }

    public static Length demonstrateLengthAddition(Length l1,
                                                   Length l2,
                                                   LengthUnit targetUnit) {
        return l1.add(l2, targetUnit);
    }

    public static void main(String[] args) {

        System.out.println(new Length(1.0, LengthUnit.FEET)
                .convertTo(LengthUnit.INCHES));

        System.out.println(new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES), LengthUnit.FEET));

        System.out.println(new Length(36.0, LengthUnit.INCHES)
                .equals(new Length(1.0, LengthUnit.YARDS)));
    }

    public static boolean demonstrateWeightEquality(Weight w1, Weight w2) {
        return w1.equals(w2);
    }

    public static Weight demonstrateWeightConversion(double value,
                                                     WeightUnit from,
                                                     WeightUnit to) {
        double base = from.convertToBaseUnit(value);
        double result = to.convertFromBaseUnit(base);
        return new Weight(result, to);
    }

    public static Weight demonstrateWeightConversion(Weight weight,
                                                     WeightUnit toUnit) {
        return weight.convertTo(toUnit);
    }

    public static Weight demonstrateWeightAddition(Weight w1, Weight w2) {
        return w1.add(w2);
    }

    public static Weight demonstrateWeightAddition(Weight w1,
                                                   Weight w2,
                                                   WeightUnit targetUnit) {
        return w1.add(w2, targetUnit);
    }
}