import java.util.Stack;

/**
 * ================================================================
 * MAIN CLASS - UseCase5PalindromeCheckerApp
 * ================================================================
 *
 * Use Case 5: Stack-Based Validation
 *
 * Description:
 * This class validates a palindrome by pushing characters
 * into a stack and then popping them to compare with the
 * original string. The stack naturally reverses the order
 * of elements, making it suitable for palindrome validation.
 *
 * Key Concepts:
 * - Stack (LIFO principle)
 * - Push operation (insert characters)
 * - Pop operation (remove characters in reverse order)
 * - Reversal logic
 *
 * @author Developer
 * @version 5.0
 */
public class PalindromeCheckerApp {

    /**
     * Application entry point for UC5.
     *
     * @param args Command-Line arguments
     */
    public static void main(String[] args) {
        // Declare and initialize the input string.
        String input = "noon";

        // Create a Stack to store characters.
        Stack<Character> stack = new Stack<>();

        // Push each character of the string into the stack.
        for (char c : input.toCharArray()) {
            stack.push(c);
        }

        // Assume palindrome initially.
        boolean isPalindrome = true;

        for (char c : input.toCharArray()) {
            if (c != stack.pop()) {
                isPalindrome = false;
                break;
            }
        }

        // Print result.
        System.out.println("Input : " + input);
        System.out.println("Is Palindrome? : " + isPalindrome);
    }
}
