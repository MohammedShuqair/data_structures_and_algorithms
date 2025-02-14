package DoubleLinkedLists;

public class DoubleItem {
     double info ;
    DoubleItem prev ; // SelfReferential Pointer1
    DoubleItem next ; // SelfReferential Pointer2
        // Constructors
        public DoubleItem ( )
        { this(0,null,null); }
        public DoubleItem(int info, DoubleItem prev , DoubleItem next)
        { this.info = info ;
            this.prev = prev ;
            this.next = next ;
        }

}
