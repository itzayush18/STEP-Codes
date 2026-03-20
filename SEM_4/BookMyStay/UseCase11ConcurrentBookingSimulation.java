/**
 * ==========================================================
 * Book My Stay App - Hotel Booking Management System
 * ==========================================================
 *
 * Use Case 11: Concurrent Booking Simulation (Thread Safety)
 *
 * Description:
 * This program simulates multiple guests submitting booking
 * requests concurrently. Synchronization ensures thread-safe
 * access to shared inventory and prevents double allocation.
 *
 * @author Developer
 * @version 11.1
 */

import java.util.*;

public class UseCase11ConcurrentBookingSimulation {

    /**
     * Application entry point
     */
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Book My Stay - Concurrent Booking   ");
        System.out.println("              Version 11.1             ");
        System.out.println("=======================================\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single", 5);
        inventory.addRoomType("Double", 3);
        inventory.addRoomType("Suite", 2);

        // Initialize booking processor
        ConcurrentBookingProcessor processor = new ConcurrentBookingProcessor(inventory);

        // Create guest threads
        Thread t1 = new Thread(() -> processor.bookRoom("Abhi", "Single"));
        Thread t2 = new Thread(() -> processor.bookRoom("Vanmathi", "Double"));
        Thread t3 = new Thread(() -> processor.bookRoom("Kural", "Suite"));
        Thread t4 = new Thread(() -> processor.bookRoom("Subha", "Single"));

        // Start threads simultaneously
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Wait for all threads to finish
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display remaining inventory
        System.out.println("\nRemaining Inventory:");
        System.out.println("Single: " + inventory.getAvailability("Single"));
        System.out.println("Double: " + inventory.getAvailability("Double"));
        System.out.println("Suite: " + inventory.getAvailability("Suite"));

        System.out.println("\nApplication terminated.");
    }
}


/**
 * ==========================================================
 * RoomInventory Class
 * ==========================================================
 *
 * Manages centralized room availability with synchronized access.
 *
 * @version 11.0
 */
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public synchronized void addRoomType(String roomType, int availability) {
        inventory.put(roomType, availability);
    }

    public synchronized int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public synchronized boolean allocateRoom(String roomType) {
        int current = getAvailability(roomType);
        if (current > 0) {
            inventory.put(roomType, current - 1);
            return true;
        }
        return false;
    }
}


/**
 * ==========================================================
 * ConcurrentBookingProcessor Class
 * ==========================================================
 *
 * Handles concurrent booking requests safely using synchronization.
 *
 * @version 11.0
 */
class ConcurrentBookingProcessor {
    private RoomInventory inventory;
    private Map<String, Set<String>> allocatedRooms;

    public ConcurrentBookingProcessor(RoomInventory inventory) {
        this.inventory = inventory;
        allocatedRooms = new HashMap<>();
    }

    /**
     * Book a room safely in a synchronized block
     */
    public void bookRoom(String guestName, String roomType) {
        synchronized (this) {
            if (inventory.allocateRoom(roomType)) {
                allocatedRooms.putIfAbsent(roomType, new HashSet<>());
                int roomNumber = allocatedRooms.get(roomType).size() + 1;
                String roomId = roomType + "-" + roomNumber;
                allocatedRooms.get(roomType).add(roomId);

                System.out.println("Booking confirmed for Guest: "
                                   + guestName + ", Room ID: " + roomId);
            } else {
                System.out.println("Booking failed for Guest: "
                                   + guestName + " (No availability for " + roomType + ")");
            }
        }
    }
}