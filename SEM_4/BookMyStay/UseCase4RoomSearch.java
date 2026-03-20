/**
 * ==========================================================
 * Book My Stay App - Hotel Booking Management System
 * ==========================================================
 *
 * Use Case 4: Room Search & Availability Check
 *
 * Description:
 * This program demonstrates safe, read-only access to
 * centralized inventory data. Guests can search for
 * available rooms and view their details without
 * modifying system state.
 *
 * @author Developer
 * @version 4.1
 */

import java.util.HashMap;
import java.util.Map;

public class UseCase4RoomSearch {

    /**
     * Application entry point
     */
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Book My Stay - Room Search Service  ");
        System.out.println("              Version 4.1              ");
        System.out.println("=======================================\n");

        // Initialize centralized inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 5);
        inventory.addRoomType("Double Room", 0); // deliberately set to 0
        inventory.addRoomType("Suite Room", 2);

        // Initialize room objects (domain model)
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Initialize search service
        SearchService searchService = new SearchService(inventory);

        // Guest initiates search
        System.out.println("Guest is searching for available rooms...\n");
        searchService.displayAvailableRooms(new Room[]{singleRoom, doubleRoom, suiteRoom});

        System.out.println("\nApplication terminated.");
    }
}


/**
 * ==========================================================
 * RoomInventory Class
 * ==========================================================
 *
 * Manages centralized room availability using HashMap.
 * Provides controlled methods for updates and retrieval.
 *
 * @version 3.0
 */
class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String roomType, int availability) {
        inventory.put(roomType, availability);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}


/**
 * ==========================================================
 * SearchService Class
 * ==========================================================
 *
 * Provides read-only access to inventory and room details.
 * Ensures system state is not modified during search.
 *
 * @version 4.0
 */
class SearchService {

    private RoomInventory inventory;

    public SearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Display available rooms with details
     */
    public void displayAvailableRooms(Room[] rooms) {
        System.out.println("Available Room Options:");
        System.out.println("---------------------------------------");

        for (Room room : rooms) {
            int availability = inventory.getAvailability(room.roomType);

            // Defensive check: only show rooms with availability > 0
            if (availability > 0) {
                room.displayRoomDetails(availability);
            }
        }
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