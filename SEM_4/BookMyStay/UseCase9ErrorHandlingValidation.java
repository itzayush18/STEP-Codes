/**
 * ==========================================================
 * Book My Stay App - Hotel Booking Management System
 * ==========================================================
 *
 * Use Case 9: Error Handling & Validation
 *
 * Description:
 * This program demonstrates structured validation and
 * error handling for booking inputs. Invalid room types
 * or inconsistent states are detected early, and custom
 * exceptions provide clear failure messages.
 *
 * @author Developer
 * @version 9.1
 */

import java.util.Scanner;

public class UseCase9ErrorHandlingValidation {

    /**
     * Application entry point
     */
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Book My Stay - Booking Validation   ");
        System.out.println("              Version 9.1              ");
        System.out.println("=======================================\n");

        Scanner scanner = new Scanner(System.in);

        try {
            // Collect guest input
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            // Validate input
            InvalidBookingValidator.validateRoomType(roomType);

            // If validation passes, simulate booking confirmation
            System.out.println("Booking confirmed for Guest: " + guestName
                               + ", Room Type: " + roomType);

        } catch (InvalidBookingException e) {
            // Handle validation failure gracefully
            System.out.println("Booking failed: " + e.getMessage());
        } finally {
            scanner.close();
        }

        System.out.println("\nApplication terminated.");
    }
}


/**
 * ==========================================================
 * InvalidBookingException Class
 * ==========================================================
 *
 * Custom exception for invalid booking scenarios.
 *
 * @version 9.0
 */
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}


/**
 * ==========================================================
 * InvalidBookingValidator Class
 * ==========================================================
 *
 * Validates booking input before processing.
 *
 * @version 9.0
 */
class InvalidBookingValidator {

    /**
     * Validate room type input
     */
    public static void validateRoomType(String roomType) throws InvalidBookingException {
        if (!(roomType.equals("Single") || roomType.equals("Double") || roomType.equals("Suite"))) {
            throw new InvalidBookingException("Invalid room type selected.");
        }
    }
}