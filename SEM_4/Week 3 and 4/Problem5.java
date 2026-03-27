import java.util.Arrays;

public class Problem5 {
    static int linearFirst(String[] arr, String target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; ++i) {
            ++comparisons;
            if (arr[i].equals(target)) {
                System.out.println("Linear First Index: " + i + ", Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear First Index: -1, Comparisons: " + comparisons);
        return -1;
    }

    static int linearLast(String[] arr, String target) {
        int comparisons = 0;

        for (int i = arr.length - 1; i >= 0; --i) {
            ++comparisons;
            if (arr[i].equals(target)) {
                System.out.println("Linear Last Index: " + i + ", Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear Last Index: -1, Comparisons: " + comparisons);
        return -1;
    }

    static int binarySearch(String[] arr, String target) {
        int low = 0;
        int high = arr.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            ++comparisons;
            int cmp = arr[mid].compareTo(target);
            if (cmp == 0) {
                System.out.println("Binary Found Index: " + mid + ", Comparisons: " + comparisons);
                return mid;
            }

            if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary Found Index: -1, Comparisons: " + comparisons);
        return -1;
    }

    static int firstOccurrence(String[] arr, String target) {
        int low = 0;
        int high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid].compareTo(target) >= 0) {
                if (arr[mid].equals(target)) {
                    result = mid;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    static int lastOccurrence(String[] arr, String target) {
        int low = 0;
        int high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid].compareTo(target) <= 0) {
                if (arr[mid].equals(target)) {
                    result = mid;
                }
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    static int countOccurrences(String[] arr, String target) {
        int first = firstOccurrence(arr, target);
        int last = lastOccurrence(arr, target);
        return (first == -1) ? 0 : last - first + 1;
    }

    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC"};

        linearFirst(logs, "accB");
        linearLast(logs, "accB");

        Arrays.sort(logs);
        System.out.println("Sorted Logs: " + Arrays.toString(logs));

        binarySearch(logs, "accB");
        int count = countOccurrences(logs, "accB");
        System.out.println("Count of accB: " + count);
    }
}