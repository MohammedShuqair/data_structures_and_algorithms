package Stack;

public class Stack<T> {
    StackNode<T> topNode;

    public static void main(String[] args) {
        Stack<Integer> inventory = new Stack<Integer>();
        inventory.push(1);
        inventory.push(2);
        inventory.push(3);
        inventory.print();
        System.out.println("Delete Item :" + inventory.pop());
        System.out.println("Item at head :" + inventory.peek());
    }

    public boolean isEmpty() {
        return topNode == null;
    }

    public void print() {
        if (topNode != null) {
            StackNode<T> temp = topNode;
            while (temp != null) {
                System.out.println(temp.data);
                temp = temp.link;
            }
        }
    }

    public void push(T data) {
        topNode = new StackNode<T>(data, topNode);
    }

    public T pop() {
        if (topNode == null) return null;
        T data = topNode.data;
        topNode = topNode.link;
        return data;
    }

    public T peek() {
        if (topNode == null) return null;
        return topNode.data;
    }
}
