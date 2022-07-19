package redblacktree;

public class Node<T extends Comparable<T>> extends binarysearchtree.Node<T>{
    public boolean black = false;
    public Node(T key, Node<T> parent, boolean black){
        super(key, parent);
        this.black = black;
    }
}