package disjointset;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>>{
    public T key;
    public Node<T> parent;
    public int rank;

    public Node(T key){
        this.key = key;
        parent = this;
        rank = 0;
    }

    public int compareTo(Node<T> node){
        return key.compareTo((node.key));
    }
}