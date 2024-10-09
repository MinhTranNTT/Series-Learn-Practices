package org.crocodile.practice._206;

import org.crocodile.practice.common.LinkedListUtils;
import org.crocodile.practice.common.ListNode;

public class ClientReverseList {
    public static void main(String[] args) {
        ListNode head = LinkedListUtils.getSingleLinkedList();
        LinkedListUtils.printListNode(head);
        ListNode reverseListNode = reverseListNode(head);
        // ListNode reverseListNode = reverseList(head);
        LinkedListUtils.printListNode(reverseListNode);
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
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

}
