package binarysearchtree;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>>{
    public T data;
    public Node<T> parent;
    public Node<T> left;
    public Node<T> right;

    public Node(T data, Node<T> parent){
        this.data = data;
        this.parent = parent;
    }

    public int compareTo(Node<T> o){
        if(o==null)
            throw new RuntimeException("BinarySearchTree<T>.Node<T>.compareTo(): recieve a null object, shouldn't happen in code");
        if(data!=null)
            return data.compareTo(o.data);
        if(o.data!=null)
            return -1*o.data.compareTo(data);
        else
            return 0;
    }
}