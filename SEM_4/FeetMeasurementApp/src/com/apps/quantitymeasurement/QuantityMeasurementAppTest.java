package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    // ================= UC1 / UC2 / UC3 / UC4 =================

    @Test
    public void testFeetEquality() {
        assertTrue(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(1.0, LengthUnit.FEET)));
    }

    @Test
    public void testInchesEquality() {
        assertTrue(new Length(1.0, LengthUnit.INCHES)
                .equals(new Length(1.0, LengthUnit.INCHES)));
    }

    @Test
    public void testFeetInchesEquality() {
        assertTrue(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(12.0, LengthUnit.INCHES)));
    }

    @Test
    public void testFeetInequality() {
        assertFalse(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(2.0, LengthUnit.FEET)));
    }

    @Test
    public void testInchesInequality() {
        assertFalse(new Length(1.0, LengthUnit.INCHES)
                .equals(new Length(2.0, LengthUnit.INCHES)));
    }

    @Test
    public void testCrossUnitInequality() {
        assertFalse(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(1.0, LengthUnit.INCHES)));
    }

    @Test
    public void testSameReference() {
        Length l = new Length(1.0, LengthUnit.FEET);
        assertTrue(l.equals(l));
    }

    @Test
    public void testNullComparison() {
        assertFalse(new Length(1.0, LengthUnit.FEET).equals(null));
    }

    @Test
    public void testDifferentValuesSameUnitNotEqual() {
        assertFalse(new Length(5.0, LengthUnit.FEET)
                .equals(new Length(6.0, LengthUnit.FEET)));
    }

    @Test
    public void testYardEquals36Inches() {
        assertTrue(new Length(1.0, LengthUnit.YARDS)
                .equals(new Length(36.0, LengthUnit.INCHES)));
    }

    @Test
    public void testCentimeterEqualsInches() {
        assertTrue(new Length(2.54, LengthUnit.CENTIMETERS)
                .equals(new Length(1.0, LengthUnit.INCHES)));
    }

    @Test
    public void testThreeFeetEqualsOneYard() {
        assertTrue(new Length(3.0, LengthUnit.FEET)
                .equals(new Length(1.0, LengthUnit.YARDS)));
    }

    // ================= UC5 (Conversion) =================

    @Test
    public void convertFeetToInches() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPS);
    }

    @Test
    public void convertYardsToInchesUsingOverloadedMethod() {
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(
                        new Length(2.0, LengthUnit.YARDS),
                        LengthUnit.INCHES
                );

        assertEquals(72.0, result.getValue(), EPS);
    }

    // ================= UC6 (Addition) =================

    @Test
    public void addFeetAndInches() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertEquals(new Length(2.0, LengthUnit.FEET), result);
    }

    @Test
    public void addFeetAndFeet() {
        Length result = new Length(2.0, LengthUnit.FEET)
                .add(new Length(3.0, LengthUnit.FEET));

        assertEquals(new Length(5.0, LengthUnit.FEET), result);
    }

    // ================= UC7 (Addition with Target Unit) =================

    @Test
    public void addFeetAndInchesWithTargetUnitFeet() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES), LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPS);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void addFeetAndInchesWithTargetUnitInches() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES), LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPS);
    }

    @Test
    public void addFeetAndInchesWithTargetUnitYards() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES), LengthUnit.YARDS);

        assertEquals(0.666666, result.getValue(), 1e-3);
    }

    // ================= UC8 (Refactored Enum Tests) =================

    @Test
    public void testLengthUnit_FeetToBase() {
        assertEquals(5.0,
                LengthUnit.FEET.convertToBaseUnit(5.0),
                EPS);
    }

    @Test
    public void testLengthUnit_InchesToBase() {
        assertEquals(1.0,
                LengthUnit.INCHES.convertToBaseUnit(12.0),
                EPS);
    }

    @Test
    public void testLengthUnit_YardsToBase() {
        assertEquals(3.0,
                LengthUnit.YARDS.convertToBaseUnit(1.0),
                EPS);
    }

    @Test
    public void testLengthUnit_CmToBase() {
        assertEquals(1.0,
                LengthUnit.CENTIMETERS.convertToBaseUnit(30.48),
                1e-3);
    }

    @Test
    public void testConvertFromBaseUnit_ToInches() {
        assertEquals(12.0,
                LengthUnit.INCHES.convertFromBaseUnit(1.0),
                EPS);
    }

    @Test
    public void testRefactoredEqualityStillWorks() {
        assertTrue(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(12.0, LengthUnit.INCHES)));
    }

    @Test
    public void testRefactoredConversionStillWorks() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPS);
    }

    @Test
    public void testRefactoredAdditionStillWorks() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES), LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPS);
    }

    // ================= Validation =================

    @Test
    public void testNullUnitThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(1.0, null);
        });
    }

    @Test
    public void testInvalidValueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(Double.NaN, LengthUnit.FEET);
        });
    }
}