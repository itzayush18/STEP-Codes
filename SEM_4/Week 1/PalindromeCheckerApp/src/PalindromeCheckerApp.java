
/**
 * MINI CLASS - UseCase6PalindromeCheckerApp
 *
 * Use Case 6: Queue + Stack Fairness Check
 *
 * Description:
 * This class demonstrates palindrome validation using
 * two different data structures:
 *
 * 1) Queue (FIFO - First In First Out)
 * 2) Stack (LIFO - Last In First Out)
 *
 * Characters are inserted into both structures and then
 * compared by removing from the front of the queue and
 * the top of the stack.
 *
 * If all characters match, the input string is confirmed
 * as a palindrome.
 *
 * This use case helps understand how FIFO and LIFO
 * behaviors can be combined for symmetric comparison.
 *
 * Author: Developer
 * Version: 1.0
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class PalindromeCheckerApp {

    /**
     * Application entry point for the use case
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Input : ");
        String input = scanner.nextLine();

        // Define the input string to validate
        String inputStr = input.replaceAll("\\s+", "").toLowerCase();

        // Create a Queue to store characters in FIFO order
        Queue<Character> queue = new LinkedList<>();

        // Create a Stack to store characters in LIFO order
        Stack<Character> stack = new Stack<>();

        // Insert each character into both queue and stack
        for (char c : inputStr.toCharArray()) {
            queue.add(c);
            stack.push(c);
        }

        // Flag to track palindrome status
        boolean isPalindrome = true;

        // Compare characters until the queue becomes empty
        while (!queue.isEmpty()) {
            if (!queue.remove().equals(stack.pop())) {
                isPalindrome = false;
                break;
            }
        }

        System.out.println("Is palindrome : " + isPalindrome);

        scanner.close();
    }
}
