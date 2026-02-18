/*
 * NAME: UseCase9PalindromeCheckerApp
 *
 * Use Case 9: Recursive Palindrome Checker
 *
 * Description:
 * This class validates a palindrome using recursion.
 *
 * The algorithm works as follows:
 * 1. Base case: empty string or single char = palindrome
 * 2. Recursive case: compare first & last characters
 * 3. If they match, recurse on substring (excluding first/last)
 * 4. Call stack manages the recursion depth automatically
 *
 * Key Concepts:
 * - Recursion with start/end pointers
 * - Base condition to prevent stack overflow
 * - Implicit call stack management
 * - Tail recursion optimization potential
 *
 * Time: O(n), Space: O(n) due to call stack
 *
 * Author: Developer
 * Version: 1.0
 */

import java.util.Scanner;

public class PalindromeCheckerApp {

    /**
     * Application entry point for UC9.
     * Pure recursion with start/end pointers
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string to check palindrome: ");
        String input = scanner.nextLine();

        if (check(input, 0, input.length() - 1)) {
            System.out.println(input + " is a palindrome!");
        } else {
            System.out.println(input + " is NOT a palindrome.");
        }
        
        scanner.close();
    }
    
    /**
     * Core recursive palindrome checker
     * @param str input string
     * @param start left pointer
     * @param end right pointer
     * @return true if palindrome
     */
    private static boolean check(String str, int start, int end) {
        if (start >= end) {
            return true;  // Empty or single character
        }
        
        // Compare first and last characters
        if (str.charAt(start) != str.charAt(end)) {
            return false;
        }
        
        // Recursive call: check inner substring
        return check(str, start + 1, end - 1);
    }
}
