package org.crocodile.practice.common;

public class LinkedListUtils {
    public static ListNode getSingleLinkedList() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        return head;
    }

    public static void printListNode(ListNode head) {
       ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static ListNode getSinglePalindromeLinkedList() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next= new ListNode(1);
        return head;
    }
}
