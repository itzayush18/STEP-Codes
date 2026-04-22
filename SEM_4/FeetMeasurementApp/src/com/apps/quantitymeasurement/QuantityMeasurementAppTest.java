package com.apps.quantitymeasurement;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    // ================= LENGTH (UC1–UC8) =================

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

    // ---------- Conversion ----------

    @Test
    public void convertFeetToInches() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPS);
    }

    @Test
    public void convertYardsToInches() {
        Length result = new Length(2.0, LengthUnit.YARDS)
                .convertTo(LengthUnit.INCHES);

        assertEquals(72.0, result.getValue(), EPS);
    }

    // ---------- Addition UC6 ----------

    @Test
    public void addFeetAndInches() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES));

        assertEquals(new Length(2.0, LengthUnit.FEET), result);
    }

    @Test
    public void addFeetAndFeet() {
        Length result = new Length(2.0, LengthUnit.FEET)
                .add(new Length(3.0, LengthUnit.FEET));

        assertEquals(new Length(5.0, LengthUnit.FEET), result);
    }

    // ---------- Addition UC7 (target unit) ----------

    @Test
    public void addFeetAndInches_TargetFeet() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES), LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPS);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void addFeetAndInches_TargetInches() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES), LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPS);
    }

    @Test
    public void addFeetAndInches_TargetYards() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES), LengthUnit.YARDS);

        assertEquals(0.666666, result.getValue(), 1e-3);
    }

    // ================= WEIGHT (UC9) =================

    @Test
    public void testWeight_KgEquality() {
        assertTrue(new Weight(1.0, WeightUnit.KILOGRAM)
                .equals(new Weight(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testWeight_KgToGramEquality() {
        assertTrue(new Weight(1.0, WeightUnit.KILOGRAM)
                .equals(new Weight(1000.0, WeightUnit.GRAM)));
    }

    @Test
    public void testWeight_KgToPoundEquality() {
        assertTrue(new Weight(1.0, WeightUnit.KILOGRAM)
                .equals(new Weight(2.20462, WeightUnit.POUND)));
    }

    @Test
    public void testWeight_Inequality() {
        assertFalse(new Weight(1.0, WeightUnit.KILOGRAM)
                .equals(new Weight(2.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testWeight_Conversion_KgToGram() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPS);
    }

    @Test
    public void testWeight_Conversion_PoundToKg() {
        Weight result = new Weight(2.20462, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 1e-3);
    }

    @Test
    public void testWeight_Addition_SameUnit() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(2.0, WeightUnit.KILOGRAM));

        assertEquals(3.0, result.getValue(), EPS);
    }

    @Test
    public void testWeight_Addition_CrossUnit() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM));

        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    public void testWeight_Addition_TargetUnit() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), EPS);
    }

    // ---------- Category Safety ----------

    @Test
    public void testWeightVsLength_Incompatible() {
        assertFalse(new Weight(1.0, WeightUnit.KILOGRAM)
                .equals(new Length(1.0, LengthUnit.FEET)));
    }

    // ---------- Validation ----------

    @Test
    public void testInvalidValueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(Double.NaN, LengthUnit.FEET);
        });
    }

    @Test
    public void testNullUnitThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Weight(1.0, null);
        });
    }
}