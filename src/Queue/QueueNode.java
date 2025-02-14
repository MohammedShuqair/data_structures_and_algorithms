package Queue;

public class QueueNode <T>{
    T data;
    QueueNode<T> link;
    QueueNode(T data, QueueNode<T> link){
        this.data=data;
        this.link=link;
    }
    QueueNode(){
        data=null;
        link=null;
    }
}
