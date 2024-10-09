package org.crocodile.practice.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListNode {
    int val;
    public ListNode next;

    public ListNode() {    }
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
