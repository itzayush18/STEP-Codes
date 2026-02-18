/*
 * NAME: UseCase7PalindromeCheckerApp
 *
 * Use Case 7: Deque Based Optimized Palindrome Checker
 *
 * Description:
 * This class validates a palindrome using a Deque
 * (Double Ended Queue).
 *
 * Characters are inserted into the Deque and then
 * compared by removing elements from both ends:
 *
 *   - removeFirst()
 *   - removeLast()
 *
 * This avoids reversing the string and provides an
 * efficient front-to-back comparison approach.
 *
 * This use case demonstrates optimal bidirectional
 * traversal using Deque.
 *
 * Author: Developer
 * Version: 1.0
 */

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Scanner;

public class PalindromeCheckerApp {

    /**
     * Application entry point for UC7.
     * Deque using removeFirst() and removeLast()
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Define the input string
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Normalize the string (ignore spaces and case)
        input = input.replaceAll("\\s+", "").toLowerCase();

        // Create a Deque to store characters
        Deque<Character> deque = new ArrayDeque<>();

        // Add each character to the Deque
        for (char ch : input.toCharArray()) {
            deque.addLast(ch);
        }

        // Flag to track palindrome result
        boolean isPalindrome = true;

        // Continue comparison while more than one element exists
        while (deque.size() > 1) {

            char front = deque.removeFirst();
            char rear = deque.removeLast();

            if (front != rear) {
                isPalindrome = false;
                break;
            }
        }

        // Display result
        if (isPalindrome) {
            System.out.println("The given string is a Palindrome.");
        } else {
            System.out.println("The given string is NOT a Palindrome.");
        }

        scanner.close();
    }
}
