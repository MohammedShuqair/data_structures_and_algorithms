package SingleLinkedList;

public class SLLMain {
    public static void main(String[] args) {
        SingleLinkedList sll=new SingleLinkedList();
        sll.addLast(0);
        sll.addLast(1);
        sll.addLast(2);
        sll.addSecondItem(0.5);
        sll.print();

        /*System.out.println(":Delete Last");
        sll.deleteLast();
        sll.print();*/

//        System.out.println(":Delete First");
//        sll.deleteFirst();
//        sll.print();

        System.out.println(":Revers");
        sll.revers();
        sll.print();

//        if(sll.search(2)!=null){
//            System.out.println("found");
//        }

        System.out.println(":Add First");
        sll.addFirst(10);
        sll.print();

        SingleLinkedList sll2=new SingleLinkedList();
        sll2.addFirst(50);
        sll2.addFirst(100);
        sll2.addLast(200);

        System.out.println(":Concat");
        sll.concat(sll2.getFirstNode(),sll2.getLength());
        sll.print();

    }
}
