/**
 * ==========================================================
 * Book My Stay App - Hotel Booking Management System
 * ==========================================================
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * Description:
 * This program demonstrates centralized inventory management
 * using HashMap to store and manage room availability.
 * It eliminates scattered state variables and ensures
 * consistency across the system.
 *
 * @author Developer
 * @version 3.1
 */

import java.util.HashMap;
import java.util.Map;

public class UseCase3InventorySetup {

    /**
     * Application entry point
     */
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Book My Stay - Centralized Inventory");
        System.out.println("              Version 3.1              ");
        System.out.println("=======================================\n");

        // Initialize centralized inventory
        RoomInventory inventory = new RoomInventory();

        // Register room types with availability
        inventory.addRoomType("Single Room", 5);
        inventory.addRoomType("Double Room", 3);
        inventory.addRoomType("Suite Room", 2);

        // Display current inventory state
        inventory.displayInventory();

        // Controlled update: booking a room
        System.out.println("\nBooking one Double Room...");
        inventory.updateAvailability("Double Room", -1);

        // Display updated inventory state
        inventory.displayInventory();

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

    /**
     * Constructor initializes the inventory map
     */
    public RoomInventory() {
        inventory = new HashMap<>();
    }

    /**
     * Add a room type with initial availability
     */
    public void addRoomType(String roomType, int availability) {
        inventory.put(roomType, availability);
    }

    /**
     * Update availability for a given room type
     */
    public void updateAvailability(String roomType, int change) {
        if (inventory.containsKey(roomType)) {
            int current = inventory.get(roomType);
            int updated = current + change;

            if (updated < 0) {
                System.out.println("Error: Cannot reduce availability below zero for " + roomType);
            } else {
                inventory.put(roomType, updated);
                System.out.println("Updated availability for " + roomType + ": " + updated);
            }
        } else {
            System.out.println("Error: Room type not found in inventory.");
        }
    }

    /**
     * Display current inventory state
     */
    public void displayInventory() {
        System.out.println("\nCurrent Room Inventory:");
        System.out.println("---------------------------------------");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Room Type: " + entry.getKey());
            System.out.println("Available Rooms: " + entry.getValue());
            System.out.println("---------------------------------------");
        }
    }
}
