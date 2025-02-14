package Algorithms;


import Queue.Queue;

public class TwoPassRadix {
    static Queue<Integer> concat(Queue<Integer> first, Queue<Integer> second) {
        if (first.isEmpty()) return second;
        if (second.isEmpty()) return first;
        while (second.isNotEmpty()) {
            first.enqueue(second.dequeue());
        }
        return first;
    }

    static Queue<Integer> sort(Integer[] array) {
        Queue<Integer> inputQueue = new Queue<Integer>(array);
        Queue<Integer>[] queueArray = new Queue[10];
        _initQueueArray(queueArray);
        while (inputQueue.isNotEmpty()) {
            int key = inputQueue.dequeue();
            int index = key % 10;
            queueArray[index].enqueue(key);
        }
        inputQueue = concateQueueArray(inputQueue, queueArray);
        _initQueueArray(queueArray);

        while (inputQueue.isNotEmpty()) {
            int key = inputQueue.dequeue();
            int index = key / 10;
            queueArray[index].enqueue(key);
        }
        inputQueue = concateQueueArray(inputQueue, queueArray);

        return inputQueue;
    }

    private static Queue<Integer> concateQueueArray(Queue<Integer> inputQueue, Queue<Integer>[] queueArray) {
        for (Queue<Integer> queue : queueArray) {
            inputQueue = concat(inputQueue, queue);
        }
        return inputQueue;
    }

    private static void _initQueueArray(Queue<Integer>[] queueArray) {
        for (int i = 0; i < queueArray.length; i++) {
            queueArray[i] = new Queue<Integer>();
        }
    }

    public static void main(String[] args) {
        sort(new Integer[]{50, 63, 3, 9, 77, 67, 20}).print();
    }
}
