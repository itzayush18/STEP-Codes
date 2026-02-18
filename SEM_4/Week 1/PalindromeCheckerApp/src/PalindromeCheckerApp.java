/*
 * NAME: UseCase8PalindromeCheckerApp
 *
 * Use Case 8: Linked List Based Palindrome Checker
 *
 * Description:
 * This class validates a palindrome using a Singly Linked List.
 *
 * The algorithm follows these steps:
 * 1. Convert input string to linked list (each char as Node)
 * 2. Use fast/slow pointer technique to find middle
 * 3. Reverse second half of the list in-place
 * 4. Compare first half with reversed second half
 *
 * Key Concepts:
 * - Singly Linked List with Node class
 * - Fast & Slow Pointer for middle detection
 * - In-place reversal of second half
 * - Node traversal for comparison
 *
 * This demonstrates efficient O(n) time, O(1) space palindrome checking.
 *
 * Author: Developer
 * Version: 1.0
 */

import java.util.Scanner;

// Node class for singly linked list
class Node {
    char data;
    Node next;
    
    Node(char data) {
        this.data = data;
        this.next = null;
    }
}

public class PalindromeCheckerApp {

    /**
     * Application entry point for UC8.
     * Linked List using fast/slow pointers + reverse second half
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string to check palindrome: ");
        String input = scanner.nextLine().toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        
        // Convert string to linked list
        Node head = buildLinkedList(input);
        
        if (isPalindrome(head)) {
            System.out.println("\"" + input + "\" is a palindrome!");
        } else {
            System.out.println("\"" + input + "\" is NOT a palindrome.");
        }
        
        scanner.close();
    }
    
    /**
     * Converts string to singly linked list
     */
    private static Node buildLinkedList(String str) {
        Node head = null;
        Node tail = null;
        
        for (char c : str.toCharArray()) {
            Node newNode = new Node(c);
            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
        return head;
    }
    
    /**
     * Core palindrome checking algorithm using linked list
     */
    private static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // Step 1: Find middle using fast/slow pointers
        Node slow = head;
        Node fast = head;
        Node prevSlow = null;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prevSlow = slow;
            slow = slow.next;
        }
        
        // Step 2: Reverse second half
        prevSlow.next = null;  // Split list
        Node secondHalf = reverseList(slow);
        
        // Step 3: Compare halves
        return compareLists(head, secondHalf);
    }
    
    /**
     * In-place reverse of singly linked list
     */
    private static Node reverseList(Node head) {
        Node prev = null;
        Node current = head;
        Node next = null;
        
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;  // New head of reversed list
    }
    
    /**
     * Compare two linked lists character by character
     */
    private static boolean compareLists(Node head1, Node head2) {
        Node ptr1 = head1;
        Node ptr2 = head2;
        
        while (ptr1 != null && ptr2 != null) {
            if (ptr1.data != ptr2.data) {
                return false;
            }
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        
        return ptr1 == null && ptr2 == null;
    }
}
