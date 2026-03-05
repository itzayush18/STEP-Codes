/*
 * NAME: UseCase10PalindromeCheckerApp
 *
 * Use Case 10: Case-Insensitive & Space-Ignored Palindrome
 *
 * Description:
 * This class validates palindromes while ignoring spaces, case, 
 * and non-alphanumeric characters using comprehensive preprocessing.
 *
 * Processing Flow:
 * 1. Convert to lowercase: "A man a plan" → "a man a plan"
 * 2. Remove non-alphanumeric: "a man a plan" → "amanaplan"
 * 3. Apply two-pointer comparison on cleaned string
 *
 * Key Concepts:
 * - String normalization (toLowerCase())
 * - Regular expressions (regex): [^a-zA-Z0-9]
 * - Character filtering with StringBuilder
 * - Two-pointer technique on processed string
 *
 * Real-world example: "A man, a plan, a canal: Panama" ✓
 *
 * Author: Developer
 * Version: 1.0
 */

import java.util.Scanner;

public class PalindromeCheckerApp {

    /**
     * Application entry point for UC10.
     * Real-world palindrome with preprocessing
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string to check palindrome (ignores spaces/case): ");
        String input = scanner.nextLine();
        
        String cleaned = preprocessString(input);
        System.out.println("Cleaned: \"" + cleaned + "\"");
        
        if (isPalindrome(cleaned)) {
            System.out.println(input + " is a palindrome!");
        } else {
            System.out.println(input + " is NOT a palindrome.");
        }
        
        scanner.close();
    }
    
    /**
     * Comprehensive string preprocessing for palindrome checking
     */
    private static String preprocessString(String str) {
        // Step 1: Convert to lowercase
        String lower = str.toLowerCase();
        
        // Step 2: Remove non-alphanumeric characters using regex
        // [^a-zA-Z0-9] matches anything that's NOT alphanumeric
        return lower.replaceAll("[^a-zA-Z0-9]", "");
    }
    
    /**
     * Two-pointer palindrome check on preprocessed string
     */
    private static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
