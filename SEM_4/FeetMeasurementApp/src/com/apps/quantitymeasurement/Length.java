package com.apps.quantitymeasurement;

public class Length {

    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public Length convertTo(LengthUnit targetUnit) {
        double base = this.toBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(base);
        return new Length(converted, targetUnit);
    }

    public Length add(Length other) {
        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        double result = this.unit.convertFromBaseUnit(sumBase);
        return new Length(result, this.unit);
    }

    public Length add(Length other, LengthUnit targetUnit) {
        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        double result = targetUnit.convertFromBaseUnit(sumBase);
        return new Length(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Length other = (Length) obj;
        return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}