/**
 * ==========================================================
 * Book My Stay App - Hotel Booking Management System
 * ==========================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * Description:
 * This program demonstrates how confirmed reservations
 * can be stored in booking history and later retrieved
 * for reporting. Historical tracking provides visibility
 * and supports audits without external storage.
 *
 * @author Developer
 * @version 8.1
 */

import java.util.*;

public class UseCase8BookingHistoryReport {

    /**
     * Application entry point
     */
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Book My Stay - Booking History      ");
        System.out.println("              Version 8.1              ");
        System.out.println("=======================================\n");

        // Initialize booking history
        BookingHistory bookingHistory = new BookingHistory();

        // Simulate confirmed reservations (from Use Case 6)
        bookingHistory.addReservation(new Reservation("Abhi", "Single Room"));
        bookingHistory.addReservation(new Reservation("Subha", "Double Room"));
        bookingHistory.addReservation(new Reservation("Vanmathi", "Suite Room"));

        // Initialize report service
        BookingReportService reportService = new BookingReportService(bookingHistory);

        // Generate and display booking history report
        System.out.println("Booking History and Reporting\n");
        reportService.generateReport();

        System.out.println("\nApplication terminated.");
    }
}


/**
 * ==========================================================
 * Reservation Class
 * ==========================================================
 *
 * Represents a confirmed reservation.
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
 * BookingHistory Class
 * ==========================================================
 *
 * Stores confirmed reservations in insertion order.
 *
 * @version 8.0
 */
class BookingHistory {
    private List<Reservation> reservations;

    public BookingHistory() {
        reservations = new ArrayList<>();
    }

    /**
     * Add a confirmed reservation to history
     */
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        System.out.println("Reservation stored: Guest: "
                           + reservation.getGuestName()
                           + ", Room Type: " + reservation.getRoomType());
    }

    /**
     * Retrieve all reservations
     */
    public List<Reservation> getReservations() {
        return reservations;
    }
}


/**
 * ==========================================================
 * BookingReportService Class
 * ==========================================================
 *
 * Generates reports from booking history.
 * Reporting is read-only and does not modify stored data.
 *
 * @version 8.0
 */
class BookingReportService {
    private BookingHistory bookingHistory;

    public BookingReportService(BookingHistory bookingHistory) {
        this.bookingHistory = bookingHistory;
    }

    /**
     * Generate and display booking history report
     */
    public void generateReport() {
        System.out.println("Booking History Report");
        System.out.println("---------------------------------------");

        for (Reservation reservation : bookingHistory.getReservations()) {
            System.out.println("Guest: " + reservation.getGuestName()
                               + ", Room Type: " + reservation.getRoomType());
        }
    }
}