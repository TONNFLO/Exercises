import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

class LinkedListQueue {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private ListNode head;
    private ListNode tail;
    private int size;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(int x) {
        ListNode newNode = new ListNode(x);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public int pop() {
        if (head == null) {
            throw new IllegalStateException("Queue is empty");
        }
        int val = head.val;
        head = head.next;
        size--;
        if (head == null) {
            tail = null;
        }
        return val;
    }

    public int peek() {
        if (head == null) {
            throw new IllegalStateException("Queue is empty");
        }
        return head.val;
    }

    public int[] toArray() {
        int[] arr = new int[size];
        ListNode curr = head;
        for (int i = 0; i < size; i++) {
            arr[i] = curr.val;
            curr = curr.next;
        }
        return arr;
    }
}

class ArrayQueue {
    private int[] arr;
    private int head;
    private int size;

    public ArrayQueue(int capacity) {
        arr = new int[capacity];
        head = 0;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int capacity() {
        return arr.length;
    }

    public void push(int x) {
        if (size == capacity()) {
            throw new IllegalStateException("Queue is full");
        }
        int tail = (head + size) % capacity();
        arr[tail] = x;
        size++;
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        int val = arr[head];
        head = (head + 1) % capacity();
        size--;
        return val;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return arr[head];
    }

    public int[] toArray() {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = arr[(head + i) % capacity()];
        }
        return result;
    }
}

class LinkedDeque {
    class ListNode {
        int val;
        ListNode next;
        ListNode prev;

        ListNode(int x) {
            this.val = x;
            this.next = null;
            this.prev = null;
        }
    }

    private ListNode head;
    private ListNode tail;
    private int size;

    public LinkedDeque() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(int x, boolean isHead) {
        ListNode newNode = new ListNode(x);
        if (isEmpty()) {
            head = tail = newNode;
        } else if (isHead) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public int pushFirst(int x) {
        push(x, true);
        return x;
    }

    public int pushLast(int x) {
        push(x, false);
        return x;
    }

    public int pop(boolean isHead) {
        int val;
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        } else if (isHead) {
            val = head.val;
            ListNode next = head.next;
            if (next != null) {
                next.prev = null;
                head.next = null;
            }
            head = next;
            size--;
            return val;
        } else {
            val = tail.val;
            ListNode prev = tail.prev;
            if (prev != null) {
                prev.next = null;
                tail.prev = null;
            }
            tail = prev;
            size--;
            return val;
        }
    }

    public int popFirst() {
        return pop(true);
    }

    public int popLast() {
        return pop(false);
    }

    public int peekFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return head.val;
    }

    public int peekLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return tail.val;
    }

    public int[] toArray() {
        int[] arr = new int[size];
        ListNode curr = head;
        for (int i = 0; i < size; i++) {
            arr[i] = curr.val;
            curr = curr.next;
        }
        return arr;
    }
}

class ArrayDeque {
    private int[] nums;
    private int head;
    private int size;

    public ArrayDeque(int capacity) {
        this.nums = new int[capacity];
        this.head = 0;
        this.size = 0;
    }

    public int capacity() {
        return nums.length;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int index(int i) {
        return (i + capacity()) % capacity();
    }

    public void pushFirst(int num) {
        if (size == capacity()) {
            throw new IllegalStateException("Deque is full");
        }
        head = index(head - 1);
        nums[head] = num;
        size++;
    }

    public void pushLast(int i) {
        if (size == capacity()) {
            throw new IllegalStateException("Deque is full");
        }
        int tail = index(head + size);
        nums[tail] = i;
        size++;
    }

    public int peekFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return nums[head];
    }

    public int peekLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        int last = index(head + size - 1);
        return nums[last];
    }

    public int popFirst() {
        int num = peekFirst();
        head = index(head + 1);
        size--;
        return num;
    }

    public int popLast() {
        int num = peekLast();
        size--;
        return num;
    }

    public int[] toArray() {
        int[] res = new int[size];
        for (int i = 0, j = head; i < size; i++, j++) {
            res[i] = nums[index(j)];
        }
        return res;
    }
}

class Solution {
    class MyDequeue {
        private Deque<Integer> que;

        public MyDequeue() {
            this.que = new LinkedList<>();
        }

        private void pop(int val) {
            if (!que.isEmpty() && que.peekLast() == val) {
                que.pollLast();
            }
        }

        private void push(int val) {
            while (!que.isEmpty() && val > que.peekFirst()) {
                que.pollFirst();
            }
            que.offerFirst(val);
        }

        private int last() {
            return que.peekLast();
        }

    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length ==1) {
            return nums;
        }
        int[] res = new int[nums.length-k+1];
        MyDequeue que = new MyDequeue();
        for (int i = 0; i < k; i++) {
            que.push(nums[i]);
        }
        int num =0;
        res[num++] =que.last();
        for(int i = k;i<nums.length;i++){
            que.pop(nums[i-k]);
            que.push(nums[i]);
            res[num++] =que.last();
        }
        return res;
    }
}

public class MyQueue {
    class StackQueue {
        Stack<Integer> stIn;
        Stack<Integer> stOut;

        public StackQueue() {
            this.stIn = new Stack<>();
            this.stOut = new Stack<>();
        }

        public void push(int x) {
            stIn.push(x);
        }

        public int pop() {
            if (stOut.empty()) {
                while (!stOut.empty()) {
                    stOut.push(stIn.peek());
                    stIn.pop();
                }
            }
            int res = stOut.peek();
            stOut.pop();
            return res;
        }

        public int peek() {
            int res = this.pop();
            stOut.pop();
            return res;
        }

        boolean empty() {
            return stIn.empty() && stOut.empty();
        }

    }

    class QueueStack {
        Queue<Integer> queue1;
        Queue<Integer> queue2;

        QueueStack() {
            this.queue1 = new LinkedList<Integer>();
            this.queue2 = new LinkedList<Integer>();
        }

        public void push(int x) {
            queue1.offer(x);
        }

        public int pop() {
            int res;
            int size = queue1.size();
            size--;
            while (size > 0) {
                queue2.offer(queue1.peek());
                queue1.poll();
            }
            res = queue1.peek();
            queue1.poll();
            queue1 = queue2;
            while (!queue2.isEmpty()) {
                queue2.poll();
            }
            return res;
        }

        int top() {
            int size = queue1.size();
            size--;
            for (int i = size; i > 0; i--) {
                queue2.offer(queue1.peek());
                queue1.poll();
            }
            int res = queue1.peek();
            queue2.offer(queue1.peek());
            queue1.poll();
            queue1 = queue2;
            while (!queue2.isEmpty()) {
                queue2.poll();
            }
            return res;
        }

        boolean empty() {
            return queue1.isEmpty();
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();

        for(int i:nums){
            map.put(i, map.getOrDefault(i, 0)+1);
        }

        PriorityQueue<int[]> pQueue = new PriorityQueue<>((o1,o2)->o1[1]-o2[1]);
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            pQueue.add(new int[]{entry.getKey(),entry.getValue()});
            if (pQueue.size()>k) {
                pQueue.poll();
            }
        }
        int[] res = new int[k];
        for(int i=0;i<k;i++){
            res[i] = pQueue.poll()[0];
        }
        return res;
    }
    
    public static void main(String[] args) {
        /*
         * Queue<Integer> queue = new LinkedList<>();
         * queue.offer(1);
         * queue.offer(2);
         * queue.offer(3);
         * queue.offer(4);
         * queue.offer(5);
         * int size = queue.size();
         * int peek = queue.peek();
         * int pop = queue.poll();
         * boolean isEmpty = queue.isEmpty();
         * System.out.println("Queue: " + queue);
         * System.out.println("Queue size: " + size);
         * System.out.println("Peek: " + peek);
         * System.out.println("Pop: " + pop);
         * System.out.println("Is empty: " + isEmpty);
         * System.out.println("Queue after pop: " + queue);
         */
        Deque<Integer> deque = new LinkedList<>();
        deque.offerFirst(1);
        deque.offerFirst(2);
        deque.offerFirst(3);
        deque.offerLast(4);
        deque.offerLast(5);
        System.out.println("Deque: " + deque);
        System.out.println("Deque size: " + deque.size());
        System.out.println("Peek first: " + deque.peekFirst());
        System.out.println("Peek last: " + deque.peekLast());
        System.out.println("Pop first: " + deque.pollFirst());
        System.out.println("Pop last: " + deque.pollLast());
        System.out.println("Is empty: " + deque.isEmpty());
        System.out.println("Deque after pop: " + deque);

    }
}
