/**
 * ==========================================================
 * Book My Stay App - Hotel Booking Management System
 * ==========================================================
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * Description:
 * This program demonstrates safe cancellation of confirmed
 * bookings by rolling back inventory state and releasing
 * room IDs using a Stack (LIFO). Validation ensures only
 * existing reservations can be cancelled.
 *
 * @author Developer
 * @version 10.1
 */

import java.util.*;

public class UseCase10BookingCancellation {

    /**
     * Application entry point
     */
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Book My Stay - Cancellation Service ");
        System.out.println("              Version 10.1             ");
        System.out.println("=======================================\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 5);

        // Initialize booking history
        BookingHistory bookingHistory = new BookingHistory();

        // Simulate confirmed reservation
        Reservation reservation1 = new Reservation("Abhi", "Single Room", "Single-1");
        bookingHistory.addReservation(reservation1);
        inventory.decrementAvailability("Single Room");

        // Initialize cancellation service
        CancellationService cancellationService = new CancellationService(inventory, bookingHistory);

        // Guest initiates cancellation
        System.out.println("\nBooking Cancellation");
        cancellationService.cancelReservation("Single-1");

        System.out.println("\nApplication terminated.");
    }
}


/**
 * ==========================================================
 * Reservation Class
 * ==========================================================
 *
 * Represents a confirmed reservation with a unique room ID.
 *
 * @version 10.0
 */
class Reservation {
    private String guestName;
    private String roomType;
    private String roomId;

    public Reservation(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }
}


/**
 * ==========================================================
 * RoomInventory Class
 * ==========================================================
 *
 * Manages centralized room availability.
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

    public void incrementAvailability(String roomType) {
        int current = getAvailability(roomType);
        inventory.put(roomType, current + 1);
    }
}


/**
 * ==========================================================
 * BookingHistory Class
 * ==========================================================
 *
 * Stores confirmed reservations.
 *
 * @version 8.0
 */
class BookingHistory {
    private List<Reservation> reservations;

    public BookingHistory() {
        reservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        System.out.println("Reservation stored: Guest: "
                           + reservation.getGuestName()
                           + ", Room Type: " + reservation.getRoomType()
                           + ", Room ID: " + reservation.getRoomId());
    }

    public Reservation findReservationById(String roomId) {
        for (Reservation r : reservations) {
            if (r.getRoomId().equals(roomId)) {
                return r;
            }
        }
        return null;
    }

    public void removeReservation(String roomId) {
        reservations.removeIf(r -> r.getRoomId().equals(roomId));
    }
}


/**
 * ==========================================================
 * CancellationService Class
 * ==========================================================
 *
 * Handles cancellation requests and performs rollback.
 *
 * @version 10.0
 */
class CancellationService {
    private RoomInventory inventory;
    private BookingHistory bookingHistory;
    private Stack<String> rollbackStack;

    public CancellationService(RoomInventory inventory, BookingHistory bookingHistory) {
        this.inventory = inventory;
        this.bookingHistory = bookingHistory;
        rollbackStack = new Stack<>();
    }

    /**
     * Cancel reservation safely
     */
    public void cancelReservation(String roomId) {
        Reservation reservation = bookingHistory.findReservationById(roomId);

        if (reservation == null) {
            System.out.println("Cancellation failed: Reservation ID not found.");
            return;
        }

        // Perform rollback
        rollbackStack.push(roomId);
        inventory.incrementAvailability(reservation.getRoomType());
        bookingHistory.removeReservation(roomId);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: "
                           + reservation.getRoomType());

        // Display rollback history
        System.out.println("\nRollback History (Most Recent First):");
        for (String releasedId : rollbackStack) {
            System.out.println("Released Reservation ID: " + releasedId);
        }

        System.out.println("\nUpdated " + reservation.getRoomType() + " Availability: "
                           + inventory.getAvailability(reservation.getRoomType()));
    }
}