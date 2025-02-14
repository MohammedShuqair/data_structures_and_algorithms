package Stack;

public class StackNode<T> {
    T data;
    StackNode<T> link;

    public StackNode(T data, StackNode<T> link) {
        this.data = data;
        this.link = link;
    }

    public StackNode() {
        data = null;
        link = null;
    }
}
