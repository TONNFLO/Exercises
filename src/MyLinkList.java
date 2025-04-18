public class MyLinkList {
    
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    private int size;
    private ListNode head;

    public MyLinkList(){
        this.size = 0;
        this.head = new ListNode(0);
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode curr = head;
        for (int i = 0; i <= index; i++) {
            curr = curr.next;
        }
        return curr.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        size++;
        ListNode pred = head;
        for (int i = 0; i < index; i++) {
            pred = pred.next;
        }
        ListNode toAdd = new ListNode(val);
        toAdd.next = pred.next;
        pred.next = toAdd;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        size--;
        ListNode pred = head;
        for (int i = 0; i < index; i++) {
            pred = pred.next;
        }
        pred.next = pred.next.next;
    }

    public void printList() {
        ListNode curr = head.next;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println("输出完成");
    }
    public ListNode reverListNode(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp = null;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
    class solution {
        public ListNode reverList(ListNode head) {
           return reverseList(null,head);
        }
    
        private ListNode reverseList(ListNode pre,ListNode cur) {
            if (cur == null) {
                return pre;
            }
            ListNode temp = cur.next;
            cur.next = pre;
            return reverseList(cur,temp);
        }

        public ListNode revListBack(ListNode head) {
            if (head == null) return null;
            if (head.next == null) return head;
            ListNode newHead = revListBack(head.next);
            head.next.next = head;
            head.next = null;
            return newHead;
        }
    }
    public ListNode swapListNode(ListNode head) {
        ListNode cur = head;
        while (cur.next != null && cur.next.next != null) {
            ListNode temp = cur.next;
            ListNode temp2 = cur.next.next.next;
            cur.next = cur.next.next;
            cur.next.next = temp;
            cur.next.next.next = temp2;
            cur = cur.next.next;
        }
        return head;
    }
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        while (n-- > 0&&fast!=null) {
            fast = fast.next;            
        }
        fast = fast.next;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;            
        }
        slow.next = slow.next.next;
        return head;
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        int lenA = 0;
        int lenB = 0;
        while (curA != null) {
            lenA++;
            curA = curA.next;
        }
        while (curB != null) {
            lenB++;
            curB = curB.next;
        }
        curA = headA;
        curB = headB;
        if (lenA<lenB) {
            int temp = lenA;
            lenA = lenB;
            lenB = temp;
            ListNode tempNode = curA;
            curA = curB;
            curB = tempNode;           
        }
        int gap = lenA - lenB;
        while (gap-- > 0) {
            curA = curA.next;
        }
        while(curA != null && curB!=null){
            if (curA == curB) {
                return curA;
                
            }
            curA = curA.next;
            curB = curB.next;
        }
        return null;
    }
    public ListNode detectCysle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        MyLinkList obj = new MyLinkList();
        obj.addAtIndex(0,1);
        obj.addAtIndex(1,2);
        obj.addAtIndex(2,3);
        obj.addAtIndex(3,4);
        obj.addAtIndex(4,5);
        obj.printList();

    }

}

