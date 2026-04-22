import com.apps.quantitymeasurement.Length;

public static void main(String[] args) {

    Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
    Length l2 = new Length(3.0, Length.LengthUnit.FEET);
    System.out.println(l1.equals(l2));

    Length l3 = new Length(1.0, Length.LengthUnit.YARDS);
    Length l4 = new Length(36.0, Length.LengthUnit.INCHES);
    System.out.println(l3.equals(l4));

    Length l5 = new Length(1.0, Length.LengthUnit.CENTIMETERS);
    Length l6 = new Length(0.393701, Length.LengthUnit.INCHES);
    System.out.println(l5.equals(l6));
}