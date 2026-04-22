package com.apps.quantitymeasurement;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testEquality_FeetToFeet_SameValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_InchToInch_SameValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(1.0, Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_FeetToInch_Equivalent() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_InchToFeet_Equivalent() {
        Length l1 = new Length(12.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_Feet_DifferentValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(2.0, Length.LengthUnit.FEET);
        assertFalse(l1.equals(l2));
    }

    @Test
    public void testEquality_Inch_DifferentValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(2.0, Length.LengthUnit.INCHES);
        assertFalse(l1.equals(l2));
    }

    @Test
    public void testEquality_NullComparison() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        assertFalse(l1.equals(null));
    }

    @Test
    public void testEquality_SameReference() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue(l1.equals(l1));
    }

    @Test
    public void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(1.0, null);
        });
    }
    @Test
    public void testEquality_YardToFeet() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(3.0, Length.LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_YardToInches() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(36.0, Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_CmToInches() {
        Length l1 = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        Length l2 = new Length(0.393701, Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_YardDifferent() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(2.0, Length.LengthUnit.YARDS);
        assertFalse(l1.equals(l2));
    }

    @Test
    public void testEquality_MultiUnit_Transitive() {
        Length a = new Length(1.0, Length.LengthUnit.YARDS);
        Length b = new Length(3.0, Length.LengthUnit.FEET);
        Length c = new Length(36.0, Length.LengthUnit.INCHES);

        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }
}