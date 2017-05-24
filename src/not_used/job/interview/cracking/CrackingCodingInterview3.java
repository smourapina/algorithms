package iurii.job.interview.cracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Stacks and Queues
 */
public class CrackingCodingInterview3 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int n = 5;
        Tower[] towers = new Tower[3];
        for (int i = 0; i < 3; i++) towers[i] = new Tower(i);
        for (int i = n - 1; i >= 0; i--) towers[0].add(i);
        towers[0].moveDisks(n, towers[2], towers[1]);
        
        Stack<Integer> stack = new Stack<Integer>();
        stack.add(4);stack.add(5);stack.add(3);stack.add(1);stack.add(2);stack.add(0);
        Stack<Integer> sortedStack = sort(stack);
        System.out.println(sortedStack);
        while (!sortedStack.isEmpty()) {
            System.out.print(sortedStack.pop() + " ");
        }
        System.out.println();
    }
    
    /**
     * 3.1 implement 3 stacks using one array.
     * This can be done in two ways.
     * 1) just divide array into 3 equal part (we do not know how stacks can be used).
     * 2) have pointers to previous blocks. each stack can grow while there are free space.
     * Problem appears when pop() operations are done and we do not track which blocks are free.
     * Extra array O(n) can be used to track free blocks.
     * 
     * Implementation with three equal 
     */
    
    public static class ArrayThreeStack {
        private int[] array = new int[99];
        private int[] head = new int[3];
        
        {
            head[0] = 0;
            head[1] = 33;
            head[2] = 66;
        }
        
        public void push(int stackNumber, int number) {
            array[head[stackNumber]++] = number;
        }
        
        public int pop(int stackNumber) {
            if (array.length/3*stackNumber < head[stackNumber] - 1) {
                throw new IllegalStateException();
            }
            return array[head[stackNumber]--];
        }
    }
    
    /**
     * 3.2 Implement stack to support min() operation. 
     * Will use additional stack which will be used for adding value if it is min than the head
     * On pop operation we will pop from second stack 
     * if min is poped. So head of the stack will contain correct min 
     */
    
    public static class MinStack extends Stack<Integer> {
        /**
         * 
         */
        private static final long serialVersionUID = -373249160326880405L;
        
        private Stack<Integer> minStack = new Stack<Integer>();
        
        @Override
        public Integer push(Integer value) {
            if (value <= min()) {
                minStack.push(value);
            }
            return super.push(value);
        }
        
        @Override
        public Integer pop() {
            Integer popValue = super.pop();
            if (popValue == minStack.peek()) {
                minStack.pop();
            }
            return popValue;
        }
        
        private Integer min() {
            if (minStack.isEmpty()) {
                return Integer.MAX_VALUE;
            } else {
                return minStack.peek();
            }
        }
    }
    
    /**
     * 3.3 implement Stack of stacks
     */
    
    public static class SetOfStacks {
        private List<Stack<Integer>> stacks = new ArrayList<Stack<Integer>>();
        private final int capacity;
        
        public SetOfStacks(int capacity) {
            this.capacity = capacity;
        }
        
        public Integer push(Integer value) {
            Stack<Integer> stack = getCurrentStack();
            if (stack == null || stack.size() == capacity) {
                stack = new Stack<Integer>();
                stacks.add(stack);
            }
            return stack.push(value);
        }
        
        public Integer pop() {
            Stack<Integer> stack = getCurrentStack();
            if (stack.isEmpty()) {
                stacks.remove(stack);
                stack = getCurrentStack();
            }
            return stack.pop();
        }
        
        private Stack<Integer> getCurrentStack() {
            if (stacks.isEmpty()) {
                return null;
            } else {
                return stacks.get(stacks.size() - 1); 
            }
        }
        
    }
    
    /**
     * 3.4 implement Hanoy
     */
    public static class Tower {
        private Stack<Integer> tower = new Stack<Integer>();
        private final int index;
        
        public Tower(int index) {
            this.index = index;
        }
        
        public int index() {
            return index;
        }
        
        public void add(int d) {
            if (!tower.isEmpty() && tower.peek() < d) {
                throw new IllegalStateException();
            }
            tower.push(d);
        }
        
        public void moveTopTo(Tower t) {
            t.add(tower.pop());
        }
        
        public void moveDisks(int n, Tower destination, Tower buffer) {
            if (n > 0) {
                moveDisks(n-1, buffer, destination);
                moveTopTo(destination);
                buffer.moveDisks(n-1, destination, this);
            }
        }
    }
    /**
     * 3.5 Implement Queue using two stacks
     */
    
    public static class MyQueue {
        private Stack<Integer> inbound = new Stack<Integer>();
        private Stack<Integer> outbound = new Stack<Integer>();
        
        public Integer push(Integer el) {
            inbound.push(el);
            return el;
        }
        
        public Integer pop() {
            if (outbound.isEmpty()) {
                while (!inbound.isEmpty()) {
                    outbound.push(inbound.pop());
                }
            }
            return outbound.pop();
        }
        
        public Integer peek() {
            if (outbound.isEmpty()) {
                while (!inbound.isEmpty()) {
                    outbound.push(inbound.pop());
                }
            }
            return outbound.peek();
        }
        
        public int size() {
            return inbound.size() + outbound.size();
        }
        
    }
    
    /**
     * 3.6 Sort Stack using peek, pop, push, isEmpty. O(n^2) time no extra memory.
     */
    public static Stack<Integer> sort(Stack<Integer> source) {
        Stack<Integer> result = new Stack<Integer>();
        while (!source.isEmpty()) {
            Integer el = source.pop();
            while (!result.isEmpty() && result.peek() < el) {
                source.push(result.pop());
            }
            result.push(el);
        }
        return result;
    }
}