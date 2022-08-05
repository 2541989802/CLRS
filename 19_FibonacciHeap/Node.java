package fibonacciheap;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>>{
    public T data;
    public int degree;
    public Node<T> parent;
    public Node<T> child;
    public Node<T> prev;
    public Node<T> next;
    public boolean mark;

    public Node(T d){
        prev = this;
        next = this;
        data = d;
        degree = 0;
        mark = false;
        parent = null;
        child = null;
    }

    public int compareTo(Node<T> n){
        return data.compareTo(n.data);
    }

    public void addNext(Node<T> n){
        n.next = next;
        n.prev = this;
        next.prev = n;
        next = n;
    }

    public void selfRemove(){
        if(next!=this){
            prev.next = next;
            next.prev = prev;
        }
    }

    public void addChild(Node<T> n){
        if(child==null){
            child = n;
            n.next = n;
            n.prev = n;
        } else {
            n.parent = this;
            child.addNext(n);
        }
        n.mark=false;
        degree++;
    }

    public void removeChild(Node<T> n){
        if(n.parent!=this)
            return;
        n.parent=null;
        n.mark=false;
        this.mark=true;
        if(n.next==n)
            child=null;
        else{
            child=n.next;
            n.selfRemove();
        }
        degree--;
    }
}