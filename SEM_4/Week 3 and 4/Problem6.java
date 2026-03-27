public class Problem6 {
    static int linearSearch(int[] arr, int target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; ++i) {
            ++comparisons;
            if (arr[i] == target) {
                System.out.println("Linear Found at index: " + i + ", Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear Not Found, Comparisons: " + comparisons);
        return -1;
    }

    static int binarySearchInsert(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            ++comparisons;
            if (arr[mid] == target) {
                System.out.println("Binary Found at index: " + mid + ", Comparisons: " + comparisons);
                return mid;
            }

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Insertion Point: " + low + ", Comparisons: " + comparisons);
        return low;
    }

    static Integer floor(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        Integer floorVal = null;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] <= target) {
                floorVal = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return floorVal;
    }

    static Integer ceiling(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        Integer ceilVal = null;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= target) {
                ceilVal = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ceilVal;
    }

    public static void main(String[] args) {
        int[] data = {10, 25, 50, 100};

        linearSearch(data, 30);
        binarySearchInsert(data, 30);

        Integer floorVal = floor(data, 30);
        Integer ceilVal = ceiling(data, 30);

        System.out.println("Floor: " + floorVal);
        System.out.println("Ceiling: " + ceilVal);
    }
}