/**
 * ==========================================================
 * Book My Stay App - Hotel Booking Management System
 * ==========================================================
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Description:
 * This program demonstrates safe room allocation by confirming
 * booking requests, generating unique room IDs, and updating
 * inventory immediately. Double-booking is prevented using
 * a Set data structure to enforce uniqueness.
 *
 * @author Developer
 * @version 6.1
 */

import java.util.*;

public class UseCase6RoomAllocationService {

    /**
     * Application entry point
     */
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Book My Stay - Room Allocation      ");
        System.out.println("              Version 6.1              ");
        System.out.println("=======================================\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 2);
        inventory.addRoomType("Suite Room", 1);

        // Initialize booking request queue
        BookingRequestQueue requestQueue = new BookingRequestQueue();
        requestQueue.addRequest(new Reservation("Abhi", "Single Room"));
        requestQueue.addRequest(new Reservation("Subha", "Single Room"));
        requestQueue.addRequest(new Reservation("Vanmathi", "Suite Room"));

        // Initialize booking service
        BookingService bookingService = new BookingService(inventory);

        // Process requests with allocation
        bookingService.processRequests(requestQueue);

        System.out.println("\nApplication terminated.");
    }
}


/**
 * ==========================================================
 * Reservation Class
 * ==========================================================
 *
 * Represents a guest’s intent to book a room.
 *
 * @version 5.0
 */
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}


/**
 * ==========================================================
 * BookingRequestQueue Class
 * ==========================================================
 *
 * Manages booking requests using a FIFO queue.
 *
 * @version 5.0
 */
class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Booking request added for Guest: "
                           + reservation.getGuestName()
                           + ", Room Type: " + reservation.getRoomType());
    }

    public Reservation pollRequest() {
        return requestQueue.poll();
    }

    public boolean isEmpty() {
        return requestQueue.isEmpty();
    }
}


/**
 * ==========================================================
 * RoomInventory Class
 * ==========================================================
 *
 * Manages centralized room availability using HashMap.
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

    public boolean decrementAvailability(String roomType) {
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
 * BookingService Class
 * ==========================================================
 *
 * Confirms reservations by allocating unique room IDs
 * and updating inventory immediately.
 *
 * @version 6.0
 */
class BookingService {
    private RoomInventory inventory;
    private Map<String, Set<String>> allocatedRooms;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        allocatedRooms = new HashMap<>();
    }

    /**
     * Process queued requests with allocation
     */
    public void processRequests(BookingRequestQueue requestQueue) {
        System.out.println("\nRoom Allocation Processing:");
        System.out.println("---------------------------------------");

        while (!requestQueue.isEmpty()) {
            Reservation reservation = requestQueue.pollRequest();
            allocateRoom(reservation);
        }
    }

    /**
     * Allocate a room safely
     */
    private void allocateRoom(Reservation reservation) {
        String roomType = reservation.getRoomType();

        if (inventory.decrementAvailability(roomType)) {
            // Generate unique room ID
            int roomNumber = allocatedRooms
                                .getOrDefault(roomType, new HashSet<>())
                                .size() + 1;
            String roomId = roomType.split(" ")[0] + "-" + roomNumber;

            // Ensure uniqueness
            allocatedRooms.putIfAbsent(roomType, new HashSet<>());
            if (allocatedRooms.get(roomType).add(roomId)) {
                System.out.println("Booking confirmed for Guest: "
                                   + reservation.getGuestName()
                                   + ", Room ID: " + roomId);
            }
        } else {
            System.out.println("Booking failed for Guest: "
                               + reservation.getGuestName()
                               + " (No availability for " + roomType + ")");
        }
    }
}