package Heep;

public class HeepTree {
    int[] array;
    int currentIndex = 0;

    HeepTree(int size) {
        array = new int[size];
    }

    public HeepTree(int size, int[] array) {
        this.array = new int[size];
        for (int i = 0; i < array.length; i++) {
            insert(array[i]);
        }
    }

    public static void main(String[] args) {
        int[] temp = {96, 90, 70, 80, 75, 42, 60, 17, 44, 10, 72, 14};
        HeepTree heepTree = new HeepTree(temp.length + 1, temp);
        heepTree.delete();
        heepTree.insert(96);

    }

    public void insert(int data) {

        array[currentIndex] = data;
        currentIndex++;
        heapify();


    }

    public int[] heapify() {
        int index = currentIndex - 1;
        int parent = (index) / 2;
        boolean isFinished = false;
        while (!isFinished && index > 0) {
            if (array[index] > array[parent]) {
                int temp = array[index];
                array[index] = array[parent];
                array[parent] = temp;
                index = parent;
                parent = (index) / 2;
            } else {
                isFinished = true;
            }
        }
        return array;
    }

    void delete() {
        if (array.length == 0) {
            System.out.println("Heep is Empty");
        } else {
            currentIndex--;
            array[0] = array[currentIndex];
            array[currentIndex] = 0;
            int parent = 0;
            int lastParent = currentIndex / 2;
            while (parent < lastParent) {
                int firstChild = (parent * 2) + 1;
                int secondChild = (parent * 2) + 2;
                int maxIndex = maxIndex(firstChild, secondChild);
                if (array[parent] < array[maxIndex]) {
                    int temp = array[parent];
                    array[parent] = array[maxIndex];
                    array[maxIndex] = temp;
                    parent = maxIndex;
                }
            }
        }

    }

    int maxIndex(int firstChild, int secondChild) {
        if (array[firstChild] > array[secondChild]) {
            return firstChild;
        }
        return secondChild;
    }
}
