import java.util.Arrays;

class Asset {
    public final String id;
    public final double returnRate;
    public final double volatility;

    public Asset(String id, double returnRate, double volatility) {
        this.id = id;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return String.format("Asset{id='%s', returnRate=%.1f, volatility=%.1f}",
                             id, returnRate, volatility);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asset)) return false;
        Asset that = (Asset) o;
        return Double.compare(that.returnRate, returnRate) == 0 &&
               Double.compare(that.volatility, volatility) == 0 &&
               id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, returnRate, volatility);
    }
}

public class Problem4 {
    static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    static void merge(Asset[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Asset[] leftArr = new Asset[n1];
        Asset[] rightArr = new Asset[n2];

        for (int i = 0; i < n1; ++i) {
            leftArr[i] = arr[left + i];
        }
        for (int i = 0; i < n2; ++i) {
            rightArr[i] = arr[mid + 1 + i];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i].returnRate <= rightArr[j].returnRate) {
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

    static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            if (high - low < 10) {
                insertionSort(arr, low, high);
                return;
            }

            int pivotIndex = medianOfThree(arr, low, high);
            int split = partition(arr, low, high, pivotIndex);
            quickSort(arr, low, split - 1);
            quickSort(arr, split + 1, high);
        }
    }

    static int medianOfThree(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;
        if (arr[low].returnRate > arr[mid].returnRate) {
            swap(arr, low, mid);
        }
        if (arr[low].returnRate > arr[high].returnRate) {
            swap(arr, low, high);
        }
        if (arr[mid].returnRate > arr[high].returnRate) {
            swap(arr, mid, high);
        }
        return mid;
    }

    static int partition(Asset[] arr, int low, int high, int pivotIndex) {
        swap(arr, pivotIndex, high);
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; ++j) {
            if (arr[j].returnRate > pivot.returnRate ||
                arr[j].returnRate == pivot.returnRate &&
                arr[j].volatility < pivot.volatility) {
                ++i;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    static void insertionSort(Asset[] arr, int low, int high) {
        for (int i = low + 1; i <= high; ++i) {
            Asset key = arr[i];
            int j;
            for (j = i - 1;
                 j >= low &&
                 (arr[j].returnRate < key.returnRate ||
                  arr[j].returnRate == key.returnRate &&
                  arr[j].volatility > key.volatility);
                 --j) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = key;
        }
    }

    static void swap(Asset[] arr, int i, int j) {
        Asset tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        Asset[] assets = {
            new Asset("AAPL", 12.0, 5.0),
            new Asset("TSLA", 8.0, 9.0),
            new Asset("GOOG", 15.0, 4.0)
        };

        Asset[] mergeSorted = Arrays.copyOf(assets, assets.length);
        mergeSort(mergeSorted, 0, mergeSorted.length - 1);
        System.out.println("Merge: " + Arrays.toString(mergeSorted));

        Asset[] quickSorted = Arrays.copyOf(assets, assets.length);
        quickSort(quickSorted, 0, quickSorted.length - 1);
        System.out.println("Quick (desc): " + Arrays.toString(quickSorted));
    }
}