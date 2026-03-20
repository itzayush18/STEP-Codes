/**
 * ==========================================================
 * Book My Stay App - Hotel Booking Management System
 * ==========================================================
 *
 * Use Case 5: Booking Request Queue (First-Come-First-Served)
 *
 * Description:
 * This program demonstrates fair handling of booking requests
 * using a Queue data structure. Requests are stored in arrival
 * order and processed sequentially, ensuring fairness.
 * No inventory updates occur at this stage.
 *
 * @author Developer
 * @version 5.1
 */

import java.util.LinkedList;
import java.util.Queue;

public class UseCase5BookingRequestQueue {

    /**
     * Application entry point
     */
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Book My Stay - Booking Requests     ");
        System.out.println("              Version 5.1              ");
        System.out.println("=======================================\n");

        // Initialize booking request queue
        BookingRequestQueue requestQueue = new BookingRequestQueue();

        // Guests submit booking requests
        requestQueue.addRequest(new Reservation("Abhi", "Single Room"));
        requestQueue.addRequest(new Reservation("Subha", "Double Room"));
        requestQueue.addRequest(new Reservation("Vanmathi", "Suite Room"));

        // Process requests in FIFO order
        requestQueue.processRequests();

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
 * Ensures requests are processed in arrival order.
 *
 * @version 5.0
 */
class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    /**
     * Add a booking request to the queue
     */
    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Booking request added for Guest: " 
                           + reservation.getGuestName() 
                           + ", Room Type: " + reservation.getRoomType());
    }

    /**
     * Process requests in FIFO order
     */
    public void processRequests() {
        System.out.println("\nProcessing Booking Requests:");
        System.out.println("---------------------------------------");

        while (!requestQueue.isEmpty()) {
            Reservation reservation = requestQueue.poll();
            System.out.println("Processing booking for Guest: " 
                               + reservation.getGuestName() 
                               + ", Room Type: " + reservation.getRoomType());
        }
    }
}