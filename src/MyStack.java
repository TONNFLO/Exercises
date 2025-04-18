import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

class LinkedListStack{
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }    
    }
    
    private ListNode top;
    private int size;
    public LinkedListStack(){
        top = null;
        size = 0;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void push(int value){
        ListNode newNode = new ListNode(value);
        newNode.next = top;
        top = newNode;
        size++;
    }
    public int peek(){
        if(isEmpty()){
            throw new RuntimeException("栈为空，无法获取栈顶元素");
        }
        return top.val;
    }
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈为空，无法弹出元素");
        }
        int value = top.val;
        top = top.next;
        size--;
        return value;
    }
    public int[] toArray(){
        int[] arr = new int[size];
        ListNode cur = top;
        for(int i=size-1; i>=0;i--){
            arr[i] = cur.val;
            cur = cur.next;
        }
        return arr;
    }
}

class ArrayStack{
    private ArrayList<Integer> stack;

    public ArrayStack(){
        stack = new ArrayList<>();
    }
    public int size(){
        return stack.size();
    }
    public boolean isEmpty(){
        return stack.isEmpty();
    }  
    public void push(int value){
        stack.add(value);
    }
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈为空，无法弹出元素");
        }
        return stack.remove(stack.size()-1);
    }
    public int peek(){
        if(isEmpty()){
            throw new RuntimeException("栈为空，无法获取栈顶元素");
        }
        return stack.get(stack.size()-1);
    }
    public Object[] toArray(){
        return stack.toArray();
    }

}

public class MyStack {
    boolean isValid (String s){
        if (s.length()%2 != 0) {
            return false;
        }
        Deque<Character> deque = new LinkedList<>();
        char ch;
        for(int i = 0;i<s.length();i++){
            ch = s.charAt(i);
            if (ch == '(') {
                deque.push(')');
            } else if (ch =='[') {
                deque.push(']');
            } else if (ch=='{') {
                deque.push('}');
            } else if (deque.isEmpty()||deque.peek()!=ch) {
                return false;
            } else {
                deque.pop();
            }
        }
        return deque.isEmpty();
    }

    public String removeDuplicates(String s){
        StringBuilder res = new StringBuilder();

        for(char c:s.toCharArray()){
            if (res.length() !=0 && res.charAt(res.length()-1) == c) {
                res.deleteCharAt(res.length()-1);
            } else {
                res.append(c);
            }
        }
        
        return res.toString();
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(String s:tokens){
            if ("+".equals(s)) {
                stack.push(stack.pop()+stack.pop());
            }else if ("-".equals(s)) {
                stack.push(-stack.pop()+stack.pop());
            }else if ("*".equals(s)) {
                stack.push(stack.pop()*stack.pop());
            }else if ("/".equals(s)) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2/num1);
            }else {
                stack.push(Integer.valueOf(s));
            }
        }
        return stack.pop();
    }    

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        String str = "abbaca";
        String res = myStack.removeDuplicates(str);
        System.out.println(res);
    }
}
