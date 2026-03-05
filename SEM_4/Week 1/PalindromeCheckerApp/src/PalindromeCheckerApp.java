import java.util.*;

/**
 * ==========================================================
 * MAIN CLASS - UseCase13PalindromeCheckerApp
 * ==========================================================
 *
 * Use Case 13: Performance Comparison
 *
 * Description:
 * This program compares the execution performance of
 * different palindrome checking algorithms.
 *
 * Algorithms Compared:
 * 1. Two Pointer Method
 * 2. Stack Method
 * 3. Deque Method
 *
 * Key Concepts:
 * - System.nanoTime()
 * - Algorithm comparison
 */

public class PalindromeCheckerApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Palindrome Performance Comparison =====");
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        PalindromeAlgorithms algo = new PalindromeAlgorithms();

        // Two Pointer Method
        long start1 = System.nanoTime();
        boolean result1 = algo.twoPointerCheck(input);
        long end1 = System.nanoTime();

        // Stack Method
        long start2 = System.nanoTime();
        boolean result2 = algo.stackCheck(input);
        long end2 = System.nanoTime();

        // Deque Method
        long start3 = System.nanoTime();
        boolean result3 = algo.dequeCheck(input);
        long end3 = System.nanoTime();

        System.out.println("\n===== Results =====");

        System.out.println("Two Pointer Method: " + result1 +
                " | Time: " + (end1 - start1) + " ns");

        System.out.println("Stack Method: " + result2 +
                " | Time: " + (end2 - start2) + " ns");

        System.out.println("Deque Method: " + result3 +
                " | Time: " + (end3 - start3) + " ns");

        scanner.close();
    }
}

/**
 * Class containing different palindrome algorithms
 */
class PalindromeAlgorithms {

    // Method 1: Two Pointer Technique
    public boolean twoPointerCheck(String input) {

        int start = 0;
        int end = input.length() - 1;

        while (start < end) {
            if (input.charAt(start) != input.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    // Method 2: Stack Based
    public boolean stackCheck(String input) {

        Stack<Character> stack = new Stack<>();

        for (char c : input.toCharArray()) {
            stack.push(c);
        }

        for (char c : input.toCharArray()) {
            if (c != stack.pop()) {
                return false;
            }
        }

        return true;
    }

    // Method 3: Deque Based
    public boolean dequeCheck(String input) {

        Deque<Character> deque = new ArrayDeque<>();

        for (char c : input.toCharArray()) {
            deque.addLast(c);
        }

        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) {
                return false;
            }
        }

        return true;
    }
}