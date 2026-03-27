import java.util.Arrays;

class Trade {
    public final String id;
    public final int volume;

    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return String.format("Trade{id='%s', volume=%d}", id, volume);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trade)) return false;
        Trade that = (Trade) o;
        return volume == that.volume && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, volume);
    }
}

public class Problem3 {
    static void mergeSort(Trade[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    static void merge(Trade[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Trade[] leftArr = new Trade[n1];
        Trade[] rightArr = new Trade[n2];

        for (int i = 0; i < n1; ++i) {
            leftArr[i] = arr[left + i];
        }
        for (int i = 0; i < n2; ++i) {
            rightArr[i] = arr[mid + 1 + i];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i].volume <= rightArr[j].volume) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < n1) {
            arr[k++] = leftArr[i++];
        }
        while (j < n2) {
            arr[k++] = rightArr[j++];
        }
    }

    static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    static int partition(Trade[] arr, int low, int high) {
        int pivotVolume = arr[high].volume;
        int i = low - 1;

        for (int j = low; j < high; ++j) {
            if (arr[j].volume > pivotVolume) {
                ++i;
                Trade tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }

        Trade tmp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = tmp;
        return i + 1;
    }

    static Trade[] mergeTwoSortedLists(Trade[] a, Trade[] b) {
        int i = 0, j = 0, k = 0;
        Trade[] result = new Trade[a.length + b.length];

        while (i < a.length && j < b.length) {
            if (a[i].volume <= b[j].volume) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length) {
            result[k++] = a[i++];
        }
        while (j < b.length) {
            result[k++] = b[j++];
        }

        return result;
    }

    static int totalVolume(Trade[] arr) {
        int sum = 0;
        for (Trade trade : arr) {
            sum += trade.volume;
        }
        return sum;
    }

    public static void main(String[] args) {
        Trade[] trades = {
            new Trade("trade3", 500),
            new Trade("trade1", 100),
            new Trade("trade2", 300)
        };

        Trade[] mergeSorted = Arrays.copyOf(trades, trades.length);
        mergeSort(mergeSorted, 0, mergeSorted.length - 1);
        System.out.println("MergeSort: " + Arrays.toString(mergeSorted));

        Trade[] quickSorted = Arrays.copyOf(trades, trades.length);
        quickSort(quickSorted, 0, quickSorted.length - 1);
        System.out.println("QuickSort (desc): " + Arrays.toString(quickSorted));

        Trade[] list1 = {new Trade("m1", 100), new Trade("m2", 300)};
        Trade[] list2 = {new Trade("a1", 200), new Trade("a2", 400)};
        Trade[] merged = mergeTwoSortedLists(list1, list2);
        System.out.println("Merged: " + Arrays.toString(merged));
        System.out.println("Total Volume: " + totalVolume(merged));
    }
}