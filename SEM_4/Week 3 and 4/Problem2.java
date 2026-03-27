import java.util.Arrays;

class Client {
    public final String name;
    public final int riskScore;
    public final double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return String.format("Client{name='%s', riskScore=%d, accountBalance=%.2f}",
                             name, riskScore, accountBalance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client that = (Client) o;
        return riskScore == that.riskScore &&
               Double.compare(that.accountBalance, accountBalance) == 0 &&
               name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, riskScore, accountBalance);
    }
}

public class Problem2 {
    static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; ++i) {
            for (int j = 0; j < n - i - 1; ++j) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    ++swaps;
                    System.out.println("Swap: " + Arrays.toString(arr));
                }
            }
        }

        System.out.println("Bubble Sort Result: " + Arrays.toString(arr));
        System.out.println("Total Swaps: " + swaps);
    }

    static void insertionSort(Client[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            Client key = arr[i];
            int j;
            for (j = i - 1;
                 j >= 0 &&
                 (arr[j].riskScore < key.riskScore ||
                  arr[j].riskScore == key.riskScore &&
                  arr[j].accountBalance < key.accountBalance);
                 --j) {

                arr[j + 1] = arr[j];
            }
            arr[j + 1] = key;
        }

        System.out.println("Insertion Sort Result: " + Arrays.toString(arr));
    }

    static void topRisks(Client[] arr, int k) {
        System.out.print("Top " + k + " risks: ");

        for (int i = 0; i < Math.min(k, arr.length); ++i) {
            System.out.print(arr[i].name + "(" + arr[i].riskScore + ") ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Client[] clients = {
            new Client("clientC", 80, 2000.0),
            new Client("clientA", 20, 5000.0),
            new Client("clientB", 50, 3000.0)
        };

        // Bubble sort (ascending by riskScore)
        Client[] bubbleArr = Arrays.copyOf(clients, clients.length);
        bubbleSort(bubbleArr);

        // Insertion sort (descending by riskScore, then by accountBalance)
        Client[] insertionArr = Arrays.copyOf(clients, clients.length);
        insertionSort(insertionArr);

        // Print top k risks (highest risk scores first, since insertionSort goes descending)
        topRisks(insertionArr, 3);
    }
}