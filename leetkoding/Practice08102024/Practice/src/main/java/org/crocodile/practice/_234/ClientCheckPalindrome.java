package org.crocodile.practice._234;

import org.crocodile.practice.common.LinkedListUtils;
import org.crocodile.practice.common.ListNode;

import java.util.ArrayList;
import java.util.List;

public class ClientCheckPalindrome {
    public static void main(String[] args) {
        // ListNode head = LinkedListUtils.getSingleLinkedList();
        ListNode head = LinkedListUtils.getSinglePalindromeLinkedList();
        LinkedListUtils.printListNode(head);
        // System.out.println(isPalindromeV2(head));
        System.out.println(isPalindromeV3(head));
        // System.out.println(isPalindrome(head));
    }

    private static boolean isPalindromeV3(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode prev = null;
        ListNode curr = slow;
        while(curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

        while(prev.next != null) {
            if (head.getVal() == prev.getVal()) {
                prev = prev.next;
                head = head.next;
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean isPalindromeV2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode pre = null;
        ListNode p = slow;
        while (p != null) {
            ListNode tem = p.next;
            p.next = pre;
            pre = p;
            p = tem;
        }
        while (pre != null) {
            if (pre.getVal() == head.getVal()) {
                pre = pre.next;
                head = head.next;
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            list.add(curr.getVal());
            curr = curr.next;
        }
        int size = list.size();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != list.get(size - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
