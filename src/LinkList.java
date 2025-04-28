
public class LinkList {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public static class doublyListNode {
        int val;
        doublyListNode next;
        doublyListNode pre;
        doublyListNode(int x) { val = x; }      
    }
    public static void printList(ListNode headNode){
        ListNode curNode = headNode;
        while (curNode != null){
            System.out.println(curNode.val);
            curNode = curNode.next;
        }
        System.out.println("输出完成");
    }
    public static void insert(ListNode heaNode,ListNode newNode){
        ListNode n1ListNode = heaNode.next;
        heaNode.next = newNode;
        newNode.next = n1ListNode;
    }
    public static void delete(ListNode headNode){
        if(headNode.next == null){
            return;
        }
        ListNode p = headNode.next;
        headNode.next = p.next;
    }
    public static void access(ListNode headNode,int index){
        for (int i = 0; i < index; i++){
            if (headNode.next == null){
                System.out.println("超出链表长度");
                return;
            }
            headNode = headNode.next;
        }
        System.out.println(headNode.val);
    }
    public static void find(ListNode headNode,int target){
        int index = 0;
        while (headNode.next != null){
            if (headNode.val == target){
                System.out.println("找到了，下标为"+index);
                return;
            }
            headNode = headNode.next;
            index++;
        }
        System.out.println("未找到");
    }
    public static ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
    public static ListNode removeElements_2(ListNode head,int val){
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode cur = dummyNode;
        while (cur.next != null){
            if (cur.next.val == val){
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }
        }
        return dummyNode.next;
    }
    

    public static void main(String[] args) {
        ListNode n0 = new ListNode(0);
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n0.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = null;

    }

}