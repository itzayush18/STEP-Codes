package com.apps.quantitymeasurement;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    private double resultValue(Length l) {
        return Double.parseDouble(l.toString().split(" ")[0]);
    }

    private Length.LengthUnit resultUnit(Length l) {
        return Length.LengthUnit.valueOf(l.toString().split(" ")[1]);
    }

    @Test
    public void testConversion_FeetToInches() {
        double result = Length.convert(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        assertEquals(12.0, result, EPS);
    }

    @Test
    public void testConversion_InchesToFeet() {
        double result = Length.convert(24.0, Length.LengthUnit.INCHES, Length.LengthUnit.FEET);
        assertEquals(2.0, result, EPS);
    }

    @Test
    public void testConversion_YardsToInches() {
        double result = Length.convert(1.0, Length.LengthUnit.YARDS, Length.LengthUnit.INCHES);
        assertEquals(36.0, result, EPS);
    }

    @Test
    public void testConversion_InchesToYards() {
        double result = Length.convert(72.0, Length.LengthUnit.INCHES, Length.LengthUnit.YARDS);
        assertEquals(2.0, result, EPS);
    }

    @Test
    public void testConversion_CentimetersToInches() {
        double result = Length.convert(2.54, Length.LengthUnit.CENTIMETERS, Length.LengthUnit.INCHES);
        assertEquals(1.0, result, EPS);
    }

    @Test
    public void testConversion_FeetToYards() {
        double result = Length.convert(6.0, Length.LengthUnit.FEET, Length.LengthUnit.YARDS);
        assertEquals(2.0, result, EPS);
    }

    @Test
    public void testConversion_ZeroValue() {
        double result = Length.convert(0.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        assertEquals(0.0, result, EPS);
    }

    @Test
    public void testConversion_NegativeValue() {
        double result = Length.convert(-1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        assertEquals(-12.0, result, EPS);
    }

    @Test
    public void testConversion_RoundTrip() {
        double original = 5.0;
        double converted = Length.convert(original, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        double back = Length.convert(converted, Length.LengthUnit.INCHES, Length.LengthUnit.FEET);
        assertEquals(original, back, EPS);
    }

    @Test
    public void testConversion_SameUnit() {
        double result = Length.convert(5.0, Length.LengthUnit.FEET, Length.LengthUnit.FEET);
        assertEquals(5.0, result, EPS);
    }

    @Test
    public void testConversion_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            Length.convert(1.0, null, Length.LengthUnit.FEET);
        });
    }

    @Test
    public void testConversion_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            Length.convert(Double.NaN, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        });
    }

    @Test
    public void testInstanceConversion() {
        Length length = new Length(3.0, Length.LengthUnit.FEET);
        Length converted = length.convertTo(Length.LengthUnit.INCHES);
        assertEquals(new Length(36.0, Length.LengthUnit.INCHES), converted);
    }

    @Test
    public void testOverloadedConversionMethod() {
        Length length = new Length(2.0, Length.LengthUnit.YARDS);
        Length converted = QuantityMeasurementApp.demonstrateLengthConversion(
                length, Length.LengthUnit.INCHES);
        assertEquals(new Length(72.0, Length.LengthUnit.INCHES), converted);
    }

    @Test
    public void testAddition_FeetPlusFeet() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(2.0, Length.LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(3.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_FeetPlusInches() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertEquals(new Length(2.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_InchesPlusFeet() {
        Length l1 = new Length(12.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(1.0, Length.LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(24.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void testAddition_YardPlusFeet() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(3.0, Length.LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(2.0, Length.LengthUnit.YARDS), result);
    }

    @Test
    public void testAddition_WithZero() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length l2 = new Length(0.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertEquals(new Length(5.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_Negative() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length l2 = new Length(-2.0, Length.LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(3.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_Null() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> {
            l1.add(null);
        });
    }

    @Test
    public void testAddition_TargetUnit_Feet() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2, Length.LengthUnit.FEET);

        assertEquals(2.0, Length.convert(resultValue(result), resultUnit(result), Length.LengthUnit.FEET), EPS);
    }

    @Test
    public void testAddition_TargetUnit_Inches() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2, Length.LengthUnit.INCHES);

        assertEquals(24.0, Length.convert(resultValue(result), resultUnit(result), Length.LengthUnit.INCHES), EPS);
    }

    @Test
    public void testAddition_TargetUnit_Yards() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2, Length.LengthUnit.YARDS);

        assertEquals(0.666666, Length.convert(resultValue(result), resultUnit(result), Length.LengthUnit.YARDS), 1e-3);
    }

    @Test
    public void testAddition_TargetUnit_Centimeters() {
        Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(1.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2, Length.LengthUnit.CENTIMETERS);

        assertEquals(5.08, Length.convert(resultValue(result), resultUnit(result), Length.LengthUnit.CENTIMETERS), 1e-2);
    }

    @Test
    public void testAddition_CommutativeProperty_WithTargetUnit() {
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(12.0, Length.LengthUnit.INCHES);

        Length r1 = a.add(b, Length.LengthUnit.YARDS);
        Length r2 = b.add(a, Length.LengthUnit.YARDS);

        assertTrue(r1.equals(r2));
    }

    @Test
    public void testAddition_TargetUnit_Null() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class, () -> {
            l1.add(l2, null);
        });
    }

    @Test
    public void testAddition_TargetUnit_WithZero() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length l2 = new Length(0.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2, Length.LengthUnit.YARDS);

        assertEquals(1.666666, Length.convert(resultValue(result), resultUnit(result), Length.LengthUnit.YARDS), 1e-3);
    }

    @Test
    public void testAddition_TargetUnit_NegativeValues() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length l2 = new Length(-2.0, Length.LengthUnit.FEET);

        Length result = l1.add(l2, Length.LengthUnit.INCHES);

        assertEquals(36.0, Length.convert(resultValue(result), resultUnit(result), Length.LengthUnit.INCHES), EPS);
    }
}