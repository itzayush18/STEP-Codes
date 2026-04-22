package com.apps.quantitymeasurement;

public class Weight {

    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit) {
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

    public Weight convertTo(WeightUnit targetUnit) {
        double base = this.toBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(base);
        return new Weight(converted, targetUnit);
    }

    public Weight add(Weight other) {
        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        double result = this.unit.convertFromBaseUnit(sumBase);
        return new Weight(result, this.unit);
    }

    public Weight add(Weight other, WeightUnit targetUnit) {
        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        double result = targetUnit.convertFromBaseUnit(sumBase);
        return new Weight(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Weight other = (Weight) obj;
        return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}