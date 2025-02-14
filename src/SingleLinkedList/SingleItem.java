package SingleLinkedList;

public class SingleItem {
    double data;
    SingleItem pointer;
    public SingleItem(double data, SingleItem pointer){
        this.data=data;
        this.pointer=pointer;
    }
    public SingleItem(){
        this.data=0;
        this.pointer=null;
    }
}
