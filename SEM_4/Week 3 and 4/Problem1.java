import java.util.*;

class Transaction {
    public final String id;
    public final double fee;
    public final String timestamp;

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("Transaction{id='%s', fee=%.2f, timestamp='%s'}", id, fee, timestamp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.fee, fee) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fee, timestamp);
    }
}

public class Problem1 {
    static void bubbleSort(List<Transaction> list) {
        int size = list.size();
        int passes = 0;
        int swaps = 0;

        for (int i = 0; i < size - 1; ++i) {
            boolean swapped = false;
            ++passes;

            for (int j = 0; j < size - i - 1; ++j) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Transaction tmp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tmp);
                    swapped = true;
                    ++swaps;
                }
            }

            if (!swapped) {
                break;
            }
        }

        System.out.println("BubbleSort result: " + list);
        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
    }

    static void insertionSort(List<Transaction> list) {
        for (int i = 1; i < list.size(); ++i) {
            Transaction key = list.get(i);
            int j;
            for (j = i - 1;
                 j >= 0 &&
                 (list.get(j).fee > key.fee ||
                  list.get(j).fee == key.fee &&
                  list.get(j).timestamp.compareTo(key.timestamp) > 0);
                 --j) {

                list.set(j + 1, list.get(j));
            }
            list.set(j + 1, key);
        }

        System.out.println("InsertionSort result: " + list);
    }

    static void findOutliers(List<Transaction> list) {
        ArrayList<Transaction> outliers = new ArrayList<>();

        for (Transaction t : list) {
            if (t.fee > 50.0) {
                outliers.add(t);
            }
        }

        System.out.println("High-fee outliers: " + outliers);
    }

    public static void main(String[] args) {
        ArrayList<Transaction> list = new ArrayList<>();
        list.add(new Transaction("id1", 10.5, "10:00"));
        list.add(new Transaction("id2", 25.0, "09:30"));
        list.add(new Transaction("id3", 5.0, "10:15"));

        ArrayList<Transaction> list2 = new ArrayList<>(list);
        bubbleSort(list2);

        ArrayList<Transaction> list3 = new ArrayList<>(list);
        insertionSort(list3);

        findOutliers(list);
    }
}