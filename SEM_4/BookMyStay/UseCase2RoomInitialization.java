/**
 * ==========================================================
 * Book My Stay App - Hotel Booking Management System
 * ==========================================================
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Description:
 * This program demonstrates object modeling using
 * abstraction and inheritance. Different room types
 * are created and their availability is displayed.
 *
 * @author Developer
 * @version 2.1
 */

public class UseCase2RoomInitialization {

    /**
     * Application entry point
     */
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("       Book My Stay - Room Types       ");
        System.out.println("              Version 2.1              ");
        System.out.println("=======================================\n");

        // Creating room objects (Polymorphism)
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Static availability variables
        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;

        // Display room details
        singleRoom.displayRoomDetails(singleAvailability);
        doubleRoom.displayRoomDetails(doubleAvailability);
        suiteRoom.displayRoomDetails(suiteAvailability);

        System.out.println("\nApplication terminated.");
    }
}


/**
 * ==========================================================
 * Abstract Room Class
 * ==========================================================
 *
 * Defines common attributes shared by all room types.
 *
 * @version 2.0
 */
abstract class Room {

    protected String roomType;
    protected int beds;
    protected double price;

    /**
     * Method to display room details
     */
    public void displayRoomDetails(int availability) {

        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Price per night: $" + price);
        System.out.println("Available Rooms: " + availability);
        System.out.println("---------------------------------------");
    }
}


/**
 * ==========================================================
 * Single Room Class
 * ==========================================================
 *
 * Represents a single occupancy room.
 *
 * @version 2.0
 */
class SingleRoom extends Room {

    public SingleRoom() {
        roomType = "Single Room";
        beds = 1;
        price = 80.0;
    }
}


/**
 * ==========================================================
 * Double Room Class
 * ==========================================================
 *
 * Represents a double occupancy room.
 *
 * @version 2.0
 */
class DoubleRoom extends Room {

    public DoubleRoom() {
        roomType = "Double Room";
        beds = 2;
        price = 120.0;
    }
}


/**
 * ==========================================================
 * Suite Room Class
 * ==========================================================
 *
 * Represents a luxury suite room.
 *
 * @version 2.0
 */
class SuiteRoom extends Room {

    public SuiteRoom() {
        roomType = "Suite Room";
        beds = 3;
        price = 250.0;
    }
}