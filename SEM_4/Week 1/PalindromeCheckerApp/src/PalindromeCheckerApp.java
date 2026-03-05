import java.util.*;

/**
 * ==========================================================
 * MAIN CLASS - UseCase12PalindromeCheckerApp
 * ==========================================================
 *
 * Use Case 12: Strategy Pattern for Palindrome Algorithms
 *
 * Description:
 * This program demonstrates how different palindrome
 * algorithms can be selected dynamically using the
 * Strategy Design Pattern.
 *
 * Strategies Implemented:
 * 1. StackStrategy
 * 2. DequeStrategy
 *
 * Concepts Used:
 * - Interface
 * - Polymorphism
 * - Strategy Pattern
 */

public class PalindromeCheckerApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Palindrome Checker (Strategy Pattern) =====");
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        System.out.println("\nChoose Algorithm:");
        System.out.println("1. Stack Strategy");
        System.out.println("2. Deque Strategy");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();

        PalindromeStrategy strategy;

        // Inject strategy dynamically
        if (choice == 1) {
            strategy = new StackStrategy();
        } else {
            strategy = new DequeStrategy();
        }

        PalindromeService service = new PalindromeService(strategy);

        boolean result = service.checkPalindrome(input);

        if (result) {
            System.out.println("Result: The string is a PALINDROME.");
        } else {
            System.out.println("Result: The string is NOT a palindrome.");
        }

        scanner.close();
    }
}

/**
 * Strategy Interface
 */
interface PalindromeStrategy {
    boolean checkPalindrome(String input);
}

/**
 * Context class
 */
class PalindromeService {

    private PalindromeStrategy strategy;

    public PalindromeService(PalindromeStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean checkPalindrome(String input) {
        return strategy.checkPalindrome(input);
    }
}

/**
 * Stack-based palindrome strategy
 */
class StackStrategy implements PalindromeStrategy {

    public boolean checkPalindrome(String input) {

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
}

/**
 * Deque-based palindrome strategy
 */
class DequeStrategy implements PalindromeStrategy {

    public boolean checkPalindrome(String input) {

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