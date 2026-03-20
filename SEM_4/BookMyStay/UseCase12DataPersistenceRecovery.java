/**
 * ==========================================================
 * Book My Stay App - Hotel Booking Management System
 * ==========================================================
 *
 * Use Case 12: Data Persistence & System Recovery
 *
 * Description:
 * This program demonstrates persistence by saving inventory
 * state to a file during shutdown and restoring it during
 * startup. Serialization ensures durable state across runs.
 *
 * @author Developer
 * @version 12.1
 */

import java.io.*;
import java.util.*;

public class UseCase12DataPersistenceRecovery {

    private static final String INVENTORY_FILE = "inventory.dat";

    /**
     * Application entry point
     */
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Book My Stay - System Recovery      ");
        System.out.println("              Version 12.1             ");
        System.out.println("=======================================\n");

        // Initialize persistence service
        PersistenceService persistenceService = new PersistenceService(INVENTORY_FILE);

        // Attempt to restore inventory
        RoomInventory inventory = persistenceService.loadInventory();

        if (inventory == null) {
            System.out.println("System Recovery\nNo valid inventory data found. Starting fresh.\n");

            // Initialize fresh inventory
            inventory = new RoomInventory();
            inventory.addRoomType("Single", 5);
            inventory.addRoomType("Double", 3);
            inventory.addRoomType("Suite", 2);
        }

        // Display current inventory
        System.out.println("Current Inventory:");
        System.out.println("Single: " + inventory.getAvailability("Single"));
        System.out.println("Double: " + inventory.getAvailability("Double"));
        System.out.println("Suite: " + inventory.getAvailability("Suite"));

        // Save inventory state
        persistenceService.saveInventory(inventory);
        System.out.println("Inventory saved successfully.");

        System.out.println("\nApplication terminated.");
    }
}


/**
 * ==========================================================
 * RoomInventory Class
 * ==========================================================
 *
 * Serializable inventory class for persistence.
 *
 * @version 12.0
 */
class RoomInventory implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public Map<String, Integer> getInventorySnapshot() {
        return inventory;
    }
}


/**
 * ==========================================================
 * PersistenceService Class
 * ==========================================================
 *
 * Handles saving and loading inventory state using serialization.
 *
 * @version 12.0
 */
class PersistenceService {
    private String filePath;

    public PersistenceService(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Save inventory to file
     */
    public void saveInventory(RoomInventory inventory) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(inventory);
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    /**
     * Load inventory from file
     */
    public RoomInventory loadInventory() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (RoomInventory) ois.readObject();
        } catch (FileNotFoundException e) {
            return null; // No file found, start fresh
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
            return null;
        }
    }
}