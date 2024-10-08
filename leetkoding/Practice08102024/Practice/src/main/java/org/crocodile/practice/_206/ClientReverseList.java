package org.crocodile.practice._206;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
class ListNode {
    int val;
    ListNode next;

    public ListNode() {    }
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class ClientReverseList {
    public static void main(String[] args) {
        ListNode head = getSingleLinkedList();
        printListNode(head);
        // ListNode reverseListNode = reverseListNode(head);
        ListNode reverseListNode = reverseList(head);
        printListNode(reverseListNode);
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    private static ListNode reverseListNode(ListNode head) {
        ListNode prev = null;
        ListNode next = null;
        ListNode current = head;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    private static void printListNode(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    private static ListNode getSingleLinkedList() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        return head;
    }
}
