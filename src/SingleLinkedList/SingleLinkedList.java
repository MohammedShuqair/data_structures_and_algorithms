package SingleLinkedList;

public class SingleLinkedList {
    private SingleItem firstNode;
    private int length = 0;

    public static void main(String[] args) {
        SingleLinkedList sll = new SingleLinkedList();
        sll.recursiveInsert(1);
        sll.recursiveInsert(2);
        sll.recursiveInsert(3);
        sll.recursiveInsert(4);
    }

    public SingleItem getFirstNode() {
        return firstNode;
    }

    public int getLength() {
        return length;
    }

    public void print() {
        System.out.println(this);
    }

    public String toString() {
        StringBuilder s = new StringBuilder("content: { ");
        if (firstNode == null) {
            return "content: empty list";
        }
        SingleItem temp = firstNode;
        while (temp != null) {
            s.append(temp.pointer != null ? temp.data + " , " : temp.data + " }");
            temp = temp.pointer;
        }
        s.append("\nlength :").append(length);
        return s.toString();
    }

    public SingleItem search(int value) {
        SingleItem temp = firstNode;
        while (temp != null) {
            if (temp.data == value) {
                return temp;
            }
            temp = temp.pointer;
        }
        return null;
    }

    public void addSecondItem(double value) {
        if (firstNode == null) return;

        SingleItem second = new SingleItem(value, firstNode.pointer);
        firstNode.pointer = second;
        length++;
    }

    public SingleItem recursiveInsert(double value, SingleItem sll) {
        if (sll == null) {
            return new SingleItem(value, null);
        } else {
            sll.pointer = recursiveInsert(value, sll.pointer);
            return sll;
        }
    }

    public void recursiveInsert(double value) {
        firstNode = recursiveInsert(value, firstNode);
    }

    public void addLast(int value) {
        SingleItem last = new SingleItem(value, null);
        if (firstNode == null) {
            firstNode = last;
        } else {
            SingleItem temp = firstNode;
            while (temp.pointer != null) {
                temp = temp.pointer;
            }
            temp.pointer = last;
        }
        length++;
    }

    public void deleteLast() {
        if (firstNode != null) {
            SingleItem temp = firstNode;
            if (firstNode.pointer == null) {
                firstNode = null;
                length--;
                return;
            }
            while (temp.pointer.pointer != null) {
                temp = temp.pointer;
            }
            temp.pointer = null;
            length--;
        }

    }

    public void addFirst(double value) {
        SingleItem first = new SingleItem(value, firstNode);
        firstNode = first;
        length++;
    }

    public void deleteFirst() {
        if (firstNode != null) {
            firstNode = firstNode.pointer;
            length--;
        }
    }

    public void revers() {
        SingleItem temp = firstNode;
        SingleItem reversed = null;
        if (firstNode == null || firstNode.pointer == null) return;
        while (temp != null) {
            reversed = new SingleItem(temp.data, reversed);
            temp = temp.pointer;
        }
        firstNode = reversed;
    }

    public SingleItem concat(SingleItem tail, int length) {
        SingleItem temp = firstNode;

        if (firstNode == null) {
            firstNode = tail;
        } else {
            while (temp.pointer != null) {
                temp = temp.pointer;
            }
            temp.pointer = tail;
        }
        this.length += length;
        return temp;
    }
}
