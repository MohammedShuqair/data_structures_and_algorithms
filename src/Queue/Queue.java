package Queue;

public class Queue<T> {
    QueueNode<T> front;
    QueueNode<T> rare;

    public Queue() {

    }

    public Queue(T[] array) {
        for (int i = 0; i < array.length; i++) {
            this.enqueue(array[i]);
        }
    }

    public boolean isNotEmpty() {
        return front != null;
    }

    public boolean isEmpty() {
        return front == null;
    }


    public void enqueue(T data) {
        QueueNode<T> newNode = new QueueNode<T>(data, null);
        if (front == null) {
            front = rare = newNode;
        } else {
            rare.link = newNode;
            rare = newNode;
        }
    }

    public T dequeue() {
        if (front == null) return null;
        T data = front.data;
        front = front.link;
        if (front == null) rare = null;
        return data;
    }

    public void print() {
        QueueNode<T> temp = front;
        StringBuilder str = new StringBuilder();
        while (temp != null) {
            str.append(temp.data).append(" ");
            temp = temp.link;
        }
        System.out.println(str);
    }
}
