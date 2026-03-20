/**
 * ==========================================================
 * Book My Stay App - Hotel Booking Management System
 * ==========================================================
 *
 * Use Case 7: Add-On Service Selection
 *
 * Description:
 * This program demonstrates how optional services can be
 * attached to reservations without modifying core booking
 * or inventory logic. Services are mapped to reservation IDs
 * and additional costs are calculated separately.
 *
 * @author Developer
 * @version 7.1
 */

import java.util.*;

public class UseCase7AddOnServiceSelection {

    /**
     * Application entry point
     */
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Book My Stay - Add-On Services      ");
        System.out.println("              Version 7.1              ");
        System.out.println("=======================================\n");

        // Example reservation IDs (from Use Case 6 allocations)
        String reservationId1 = "Single-1";
        String reservationId2 = "Suite-1";

        // Initialize Add-On Service Manager
        AddOnServiceManager serviceManager = new AddOnServiceManager();

        // Guest selects services
        serviceManager.addService(reservationId1, new AddOnService("Breakfast", 500.0));
        serviceManager.addService(reservationId1, new AddOnService("Airport Pickup", 1000.0));
        serviceManager.addService(reservationId2, new AddOnService("Spa Access", 1500.0));

        // Display services and total cost for reservationId1
        System.out.println("Add-On Service Selection");
        System.out.println("Reservation ID: " + reservationId1);
        System.out.println("Total Add-On Cost: " + serviceManager.calculateTotalCost(reservationId1));

        // Display services and total cost for reservationId2
        System.out.println("\nReservation ID: " + reservationId2);
        System.out.println("Total Add-On Cost: " + serviceManager.calculateTotalCost(reservationId2));

        System.out.println("\nApplication terminated.");
    }
}


/**
 * ==========================================================
 * AddOnService Class
 * ==========================================================
 *
 * Represents an optional service with a name and cost.
 *
 * @version 7.0
 */
class AddOnService {
    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}


/**
 * ==========================================================
 * AddOnServiceManager Class
 * ==========================================================
 *
 * Manages mapping between reservations and selected services.
 * Uses Map<String, List<AddOnService>> to store associations.
 *
 * @version 7.0
 */
class AddOnServiceManager {
    private Map<String, List<AddOnService>> reservationServices;

    public AddOnServiceManager() {
        reservationServices = new HashMap<>();
    }

    /**
     * Attach a service to a reservation
     */
    public void addService(String reservationId, AddOnService service) {
        reservationServices.putIfAbsent(reservationId, new ArrayList<>());
        reservationServices.get(reservationId).add(service);
        System.out.println("Service added: " + service.getServiceName()
                           + " (Cost: " + service.getCost()
                           + ") to Reservation ID: " + reservationId);
    }

    /**
     * Calculate total cost of services for a reservation
     */
    public double calculateTotalCost(String reservationId) {
        List<AddOnService> services = reservationServices.getOrDefault(reservationId, new ArrayList<>());
        double total = 0.0;
        for (AddOnService service : services) {
            total += service.getCost();
        }
        return total;
    }
}