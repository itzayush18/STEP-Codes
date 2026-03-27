import java.util.ArrayList;
import java.util.List;

public class TrainConsistManagementSystem {

    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        public String toString() {
            return "Bogie: " + name + ", Capacity: " + capacity;
        }
    }

    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println(" UC10 - Count Total Seats in Train ");
        System.out.println("==================================");

        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Sleeper", 70));

        System.out.println("Bogies in Train:");
        bogies.forEach(System.out::println);

        int totalSeats = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("\nTotal Seating Capacity in Train: " + totalSeats);
    }
}