package basicdatastructure;

public class Queue<T>{
    private int head = 0;
    private int tail = 0;
    public Object[] oa;
    
    public Queue(){
        this(10);
    }

    public Queue(int i){
        oa = new Object[i+1];
    }

    public Queue(T[] ta){
        this(ta.length);
        for(T e : ta)
            enqueue(e);
    }

    public void enqueue(T e){
        if((tail+1)%oa.length == head)
            throw new RuntimeException("Queue.enqueue():Overflow");
        oa[tail] = e;
        tail=(tail+1)%oa.length;
    }

    @SuppressWarnings("unchecked")
    public T dequeue(){
        if(head == tail)
            throw new RuntimeException("Queue.dequeue():Underflow");
        Object ret = oa[head];
        head = (head+1)%oa.length;
        return(T)(ret);
    }

    public boolean isEmpty(){
        return head == tail;
    }

    public int size(){
        if(tail<head)
            return tail+oa.length-head;
        return tail-head;
    }
}